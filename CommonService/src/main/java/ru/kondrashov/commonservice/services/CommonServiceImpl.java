package ru.kondrashov.commonservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.kondrashov.commonservice.controllers.dto.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{

    private final RestTemplate restTemplate;

    @Value("${people.URL}")
    private String peopleUrl;
    @Value("${person.URL}")
    private String personUrl;
    @Value("${accounts.URL}")
    private String accountsUrl;
    @Value("${account.URL}")
    private String accountUrl;
    @Value("${bills.URL}")
    private String billsUrl;
    @Value("${bill.URL}")
    private String billUrl;
    @Value("${transactions.URL}")
    private String transactionsUrl;

    @Override
    public PersonRequestDTO getPersonById(UUID uuid){
        return restTemplate.getForObject(String.format(personUrl,uuid), PersonRequestDTO.class);

    }

    @Override
    public Collection<PersonRequestDTO> getPeople() {
        ResponseEntity<List<PersonRequestDTO>> responseEntity =
                restTemplate.exchange(
                        peopleUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<PersonRequestDTO>>() {}
                );

        return responseEntity.getBody();
    }

    @Override
    public Collection<AccountRequestDTO> getAccountsByPerson(UUID id) {
        ResponseEntity<List<AccountRequestDTO>> responseEntity =
                restTemplate.exchange(
                        String.format(accountsUrl,id),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<AccountRequestDTO>>() {}
                );
        return responseEntity.getBody();
    }

    @Override
    public Collection<BillRequestDTO> getBillsByAccountId(UUID personId, UUID accountId) {
        ResponseEntity<List<BillRequestDTO>> responseEntity =
                restTemplate.exchange(
                        String.format(billsUrl,personId,accountId),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<BillRequestDTO>>() {}
                );

        return responseEntity.getBody();
    }

    @Override
    public Collection<FinancialTransactionRequestDTO> getFinancialTransactionsByBillId(UUID personId, UUID accountId, UUID billId) {
        ResponseEntity<List<FinancialTransactionRequestDTO>> responseEntity =
                restTemplate.exchange(
                        String.format(transactionsUrl,personId, accountId, billId),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<FinancialTransactionRequestDTO>>() {}
                );

        return responseEntity.getBody();
    }

    //_______________________________________________________________________________________________________//
    @Override
    public void createPerson(PersonResponseDTO person) throws Exception {
        ResponseEntity<PersonResponseDTO> responseEntity =
                restTemplate.postForEntity(peopleUrl, person,PersonResponseDTO.class);
        if(responseEntity.getStatusCode() != HttpStatus.CREATED){
            throw new Exception(responseEntity.toString());
        }
    }

    @Override
    public void createAccount(UUID personId, AccountResponseDTO account) throws Exception {
        ResponseEntity<AccountResponseDTO> responseEntity =
                restTemplate.postForEntity(String.format(accountsUrl, personId), account, AccountResponseDTO.class);
        if(responseEntity.getStatusCode() != HttpStatus.CREATED){
            throw new Exception(responseEntity.toString());
        }
    }

    @Override
    public void createBill(UUID personId, UUID accountId, BillResponseDTO bill) throws Exception {
        ResponseEntity<BillResponseDTO> responseEntity =
                restTemplate.postForEntity(String.format(billsUrl, personId, accountId), bill, BillResponseDTO.class);
        if(responseEntity.getStatusCode() != HttpStatus.CREATED){
            throw new Exception(responseEntity.toString());
        }
    }

    //_______________________________________________________________________________________________________//
    @Override
    public void updatePerson(UUID id, PersonResponseDTO personResponseDTO){
        Map< String, UUID > params = new HashMap<>();
        params.put("id", id);
        restTemplate.put(String.format(personUrl,id), personResponseDTO, params);
    }

    @Override
    public void updateAccount(UUID personId, UUID accountId, AccountResponseDTO accountResponseDTO) {
        Map< String, UUID > params = new HashMap<>();
        params.put("id", accountId);
        restTemplate.put(String.format(accountUrl, personId, accountId), accountResponseDTO, params);
    }


    @Override
    public void updateBill(UUID personId, UUID accountId, UUID billId, BillResponseDTO billResponseDTO) {
        Map< String, UUID > params = new HashMap<>();
        params.put("id", billId);
        restTemplate.put(String.format(billUrl, personId, accountId, billId), billResponseDTO, params);
    }

    //_______________________________________________________________________________________________________//
    @Override
    public void deletePerson(UUID id) {
        for (AccountRequestDTO account:getAccountsByPerson(id)) {
            deleteAccount(id,account.getId());
        }

        restTemplate.delete(String.format(personUrl,id));

    }

    @Override
    public void deleteAccount(UUID personId, UUID accountId) {
        restTemplate.delete(String.format(accountUrl, personId, accountId));
    }

    @Override
    public void deleteBill(UUID personId, UUID accountId, UUID billId) {
        restTemplate.delete(String.format(billUrl, personId, accountId, billId));
    }
}
