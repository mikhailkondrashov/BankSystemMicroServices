package ru.kondrashov.commonservice.services;

import ru.kondrashov.commonservice.controllers.dto.*;

import java.util.Collection;
import java.util.UUID;

public interface CommonService {

    PersonRequestDTO getPersonById(UUID uuid);

    Collection<PersonRequestDTO> getPeople();

    AccountRequestDTO getAccountById(UUID uuid);

    Collection<AccountRequestDTO> getAccountsByPerson(UUID id);

    Collection<BillRequestDTO> getBillsByAccountId(UUID id);



    void createPerson(PersonResponseDTO person) throws Exception;

    void createAccount(AccountResponseDTO account) throws Exception;

    void createBill(BillResponseDTO bill) throws Exception;



    void updatePerson(UUID id, PersonResponseDTO personResponseDTO);

    void updateAccount(UUID accountId, AccountResponseDTO accountResponseDTO);

    void updateBill(UUID billId, BillResponseDTO billResponseDTO);



    void deleteAccount(UUID accountId);

    void deletePerson(UUID id);

    void deleteBill(UUID id);

    Collection<FinancialTransactionRequestDTO> getFinancialTransactionsByBillId(UUID billId);
}
