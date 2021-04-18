package ru.kondrashov.accountservice.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.kondrashov.accountservice.controllers.dto.*;
import ru.kondrashov.accountservice.services.interfacies.BillsService;
import ru.kondrashov.accountservice.utils.interfacies.AdjustmentMapping;
import ru.kondrashov.accountservice.utils.interfacies.BillsMapping;
import org.springframework.transaction.annotation.Transactional;
import ru.kondrashov.accountservice.utils.interfacies.PaymentsMapping;
import ru.kondrashov.accountservice.utils.interfacies.TransferMapping;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Controller which provides methods for operations with bills", tags = {"BILLS"})
public class BillsController {

    private final BillsService billsService;
    private final BillsMapping billsMapping;
    private final PaymentsMapping paymentsMapping;
    private final AdjustmentMapping adjustmentMapping;
    private final TransferMapping transferMapping;

    public BillsController(BillsService billsService, BillsMapping billsMapping, PaymentsMapping paymentsMapping, AdjustmentMapping adjustmentMapping, TransferMapping transferMapping) {
        this.billsService = billsService;
        this.billsMapping = billsMapping;
        this.paymentsMapping = paymentsMapping;
        this.adjustmentMapping = adjustmentMapping;
        this.transferMapping = transferMapping;
    }

    @GetMapping("/bills")
    @ApiOperation(value = "Get all bills", tags = { "BILLS" })
    @ResponseStatus(HttpStatus.OK)
    @ApiResponse(code = 200, message = "successful operation")
    public Collection<BillResponseDTO> getBills(){
        return billsService.getBills()
                .stream()
                .map(bill -> billsMapping.mapToBillResponseDTO(bill))
                .collect(Collectors.toList());
    }

    @GetMapping("/account/{accountId}/bills")
    @ApiOperation(value = "Get all bills of account", tags = { "BILLS" })
    @ResponseStatus(HttpStatus.OK)
    public Collection<BillResponseDTO> getBillsByAccountId(@PathVariable("accountId") UUID accountId){
        return billsService.getBillsByAccountId(accountId)
                .stream()
                .map(bill -> billsMapping.mapToBillResponseDTO(bill))
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/bills")
    @ApiOperation(value = "Save bill", tags = { "BILLS" })
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid BillRequestDTO billRequestDTO){
        billsService.save(billsMapping.mapToBill(billRequestDTO));
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/bills/{id}")
    @ApiOperation(value = "Update bill", tags = { "BILLS" })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid BillRequestDTO bill){
        billsService.update(id,billsMapping.mapToBill(bill));
    }

    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("/bills/{id}")
    @ApiOperation(value = "Delete bill", tags = { "BILLS" })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id){
        billsService.delete(id);
    }


    @PutMapping("/bills/{id}/payments")
    @ApiOperation(value = "Commit payment for bill", tags = { "BILLS" })
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void commitPayment(@PathVariable("id") UUID billId, @RequestBody @Valid PaymentRequestDTO paymentRequestDTO){
        billsService.commitPayment(billId,paymentsMapping.mapToPayment(paymentRequestDTO));
    }

    @GetMapping("/bills/{id}/payments")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all payments of bill", tags = { "BILLS" })
    public Collection<PaymentResponseDTO> getPaymentsByBillId(@PathVariable("id") UUID billId){
        return billsService.getPaymentsByBillId(billId).stream().map(payment -> paymentsMapping.mapToPaymentResponseDTO(payment)).collect(Collectors.toList());
    }

    @PutMapping("/bills/{id}/adjustments")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Commit adjustment for bill", tags = { "BILLS" })
    public void commitAdjustment(@PathVariable("id") UUID billId, @RequestBody @Valid AdjustmentRequestDTO adjustmentRequestDTO){
        billsService.commitAdjustment(billId,adjustmentMapping.mapToAdjustment(adjustmentRequestDTO));
    }

    @GetMapping("/bills/{id}/adjustments")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all adjustments of bill", tags = { "BILLS" })
    public Collection<AdjustmentResponseDTO> getAdjustmentsByBillId(@PathVariable("id") UUID billId){
        return billsService.getAdjustmentsByBillId(billId).stream().map(adjustment -> adjustmentMapping.mapToAdjustmentResponseDTO(adjustment)).collect(Collectors.toList());
    }

    @PutMapping("/bills/{sourceId}/transfer/{beneficiaryId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ApiOperation(value = "Commit adjustment for bill", tags = { "BILLS" })
    public void commitTransfer(@PathVariable("sourceId") UUID sourceId, @PathVariable("beneficiaryId") UUID beneficiaryId, @RequestBody @Valid TransferRequestDTO transferRequestDTO){
        billsService.commitTransfer(sourceId,beneficiaryId,transferMapping.mapToTransfer(transferRequestDTO));
    }

    @GetMapping("/bills/{id}/transactions")
    @ResponseStatus(HttpStatus.OK)
    @ApiOperation(value = "Get all financial transactions of bill", tags = { "BILLS" })
    public Collection<FinancialTransactionResponseDTO> getFinancialTransactionsByBillId(@PathVariable("id") UUID billId){

        Collection<FinancialTransactionResponseDTO> financialTransactionResponseDTOS = new ArrayList<>();

        financialTransactionResponseDTOS.addAll(billsService.
                getAdjustmentsByBillId(billId).
                stream().
                map(adjustment -> adjustmentMapping.mapToFinancialTransactionResponseDTO(adjustment)).
                collect(Collectors.toList()));

        financialTransactionResponseDTOS.
                addAll(
                        setAmountNegative(
                                billsService.
                                        getPaymentsByBillId(billId).
                                        stream().
                                        map(payment -> paymentsMapping.mapToFinancialTransactionResponseDTO(payment)).
                                        collect(Collectors.toList())
                        )
                );

        financialTransactionResponseDTOS.addAll(billsService.getTransfersByBeneficiaryBillId(billId).stream().map(transfer -> transferMapping.mapToFinancialTransactionResponseDTO(transfer)).collect(Collectors.toList()));
        financialTransactionResponseDTOS.addAll(setAmountNegative(billsService.getTransfersBySourceBillId(billId).stream().map(transfer -> transferMapping.mapToFinancialTransactionResponseDTO(transfer)).collect(Collectors.toList())));

        return financialTransactionResponseDTOS;
    }

    private Collection<FinancialTransactionResponseDTO> setAmountNegative(Collection<FinancialTransactionResponseDTO> financialTransactionResponseDTOS){

        financialTransactionResponseDTOS.forEach(financialTransactionResponseDTO -> financialTransactionResponseDTO.setAmount(financialTransactionResponseDTO.getAmount().negate()));

        return financialTransactionResponseDTOS;
    }
}
