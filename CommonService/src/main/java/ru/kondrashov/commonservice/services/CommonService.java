package ru.kondrashov.commonservice.services;

import ru.kondrashov.commonservice.controllers.dto.*;

import java.util.Collection;
import java.util.UUID;

public interface CommonService {

    PersonRequestDTO getPersonById(UUID uuid);

    Collection<PersonRequestDTO> getPeople();

    Collection<AccountRequestDTO> getAccountsByPerson(UUID id);

    Collection<BillRequestDTO> getBillsByAccountId(UUID personId, UUID accountId);



    void createPerson(PersonResponseDTO person) throws Exception;

    void createAccount(UUID personId, AccountResponseDTO account) throws Exception;

    void createBill(UUID personId, UUID accountId, BillResponseDTO bill) throws Exception;


    void updatePerson(UUID id, PersonResponseDTO personResponseDTO);

    void updateAccount(UUID personId, UUID accountId, AccountResponseDTO accountResponseDTO);

    void updateBill(UUID personId, UUID accountId, UUID billId, BillResponseDTO billResponseDTO);



    void deleteAccount(UUID personId, UUID accountId);

    void deletePerson(UUID id);

    void deleteBill(UUID personId, UUID accountId, UUID billId);

    Collection<FinancialTransactionRequestDTO> getFinancialTransactionsByBillId(UUID personId, UUID accountId, UUID billId);
}
