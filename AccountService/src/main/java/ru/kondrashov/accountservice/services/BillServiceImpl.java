package ru.kondrashov.accountservice.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kondrashov.accountservice.entities.Adjustment;
import ru.kondrashov.accountservice.entities.Bill;
import ru.kondrashov.accountservice.entities.Payment;
import ru.kondrashov.accountservice.entities.Transfer;
import ru.kondrashov.accountservice.exceptions.BillNotFoundException;
import ru.kondrashov.accountservice.exceptions.NotEnoughMoneyException;
import ru.kondrashov.accountservice.repositories.BillsRepository;
import ru.kondrashov.accountservice.services.interfacies.*;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.UUID;

@Service
public class BillServiceImpl implements BillsService {

    private final BillsRepository billsRepository;
    private final PaymentsService paymentsService;
    private final AdjustmentsService adjustmentsService;
    private final TransferService transferService;
    private final FinancialCurrencyExchange currencyConversion;

    public BillServiceImpl(BillsRepository billsRepository, PaymentsService paymentsService, AdjustmentsService adjustmentsService, TransferService transferService, FinancialCurrencyExchange currencyConversion) {
        this.billsRepository = billsRepository;
        this.paymentsService = paymentsService;
        this.adjustmentsService = adjustmentsService;
        this.transferService = transferService;
        this.currencyConversion = currencyConversion;
    }

    @Override
    public Collection<Bill> getBills() {
        return billsRepository.findAll();
    }

    @Override
    public Bill getBill(UUID id) {
        return billsRepository.getBillById(id).orElseThrow(()->new BillNotFoundException("Bill with id = "+id+" not found."));
    }

    @Override
    public Collection<Bill> getBillsByAccountId(UUID accountID) {
        return billsRepository.getBillsByAccount_Id(accountID);
    }

    @Override
    public void save(Bill bill) {
        billsRepository.save(bill);
    }

    @Override
    public void delete(UUID id) {
        billsRepository.delete(getBill(id));
    }

    @Override
    public void update(UUID id, Bill newBill) {
        Bill oldBill = getBill(id);
        oldBill.setAmount(newBill.getAmount());
        oldBill.setCurrency(newBill.getCurrency());
        oldBill.setIsOverdraft(newBill.getIsOverdraft());
        oldBill.setAccount(newBill.getAccount());
        billsRepository.save(oldBill);
    }

    @Override
    public void commitAdjustment(UUID billId, Adjustment adjustment) {

        Bill bill = getBill(billId);

       increaseBillsAmount(bill,currencyConversion.exchange(adjustment.getCurrency(),bill.getCurrency(),adjustment.getAmount()));

        adjustmentsService.save(adjustment);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void commitPayment(UUID billId, Payment payment) {

        Bill bill = getBill(billId);

        decreaseBillsAmount(bill,currencyConversion.exchange(payment.getCurrency(),bill.getCurrency(),payment.getAmount()));

        paymentsService.save(payment);
    }


    @Override
    public void commitTransfer(UUID sourceBillId, UUID beneficiaryBillId, Transfer transfer){

        Bill sourceBill = getBill(sourceBillId);
        Bill beneficiaryBill = getBill(beneficiaryBillId);

        decreaseBillsAmount(sourceBill, currencyConversion.exchange(transfer.getCurrency(), sourceBill.getCurrency(),transfer.getAmount()));
        increaseBillsAmount(beneficiaryBill, currencyConversion.exchange(transfer.getCurrency(), beneficiaryBill.getCurrency(),transfer.getAmount()));

        transferService.save(transfer);
    }

    @Override
    public Collection<Payment> getPaymentsByBillId(UUID billId) {
        return paymentsService.getPaymentsByBillId(billId);
    }

    @Override
    public Collection<Adjustment> getAdjustmentsByBillId(UUID billId) {
        return adjustmentsService.getAdjustmentsByBillId(billId);
    }

    @Override
    public Collection<Transfer> getTransfersBySourceBillId(UUID billId) {
        return transferService.getTransfersBySourceBill_Id(billId);
    }

    @Override
    public Collection<Transfer> getTransfersByBeneficiaryBillId(UUID billId) {
        return transferService.getTransfersByBeneficiaryBill_Id(billId);
    }


    private void decreaseBillsAmount(Bill bill, BigDecimal amount){

        bill.setAmount(bill.getAmount().subtract(amount));

        if(!(bill.getIsOverdraft()) && bill.getAmount().compareTo(BigDecimal.ZERO) == -1 ){
            throw new NotEnoughMoneyException("Not enough money for payment on bill with ID: " + bill.getId());
        }

        billsRepository.save(bill);
    }

    private void increaseBillsAmount(Bill bill, BigDecimal amount){
        bill.setAmount(bill.getAmount().add(amount));
        billsRepository.save(bill);
    }

}
