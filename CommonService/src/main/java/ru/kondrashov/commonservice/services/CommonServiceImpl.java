package ru.kondrashov.commonservice.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.kondrashov.commonservice.controllers.dto.*;

import java.util.*;

@Service
public class CommonServiceImpl implements CommonService{

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    @Value("${personService.URL}")
    private String personUrl;
    @Value("${accountsService.URL}")
    private String accountUrl;
    @Value("${peopleService.URL}")
    private String peopleUrl;
    @Value("${accountsByPersonIdService.URL}")
    private String accountByPersonIdUrl;
    @Value("${billsService.URL}")
    private String billsUrl;
    @Value("${billsByAccountIdService.URL}")
    private String billsByAccountIdUrl;



    public CommonServiceImpl(RestTemplate restTemplate, ObjectMapper objectMapper) {
        this.restTemplate = restTemplate;
        this.objectMapper = objectMapper;
    }

    @Override
    public PersonRequestDTO getPersonById(UUID uuid){
        return restTemplate.getForObject(String.format(personUrl,uuid), PersonRequestDTO.class);

    }

    @Override
    public AccountRequestDTO getAccountById(UUID uuid){
        return restTemplate.getForObject(accountUrl+"/"+uuid, AccountRequestDTO.class);
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
                        String.format(accountByPersonIdUrl,id),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<AccountRequestDTO>>() {}
                );
        return responseEntity.getBody();
    }

    @Override
    public Collection<BillRequestDTO> getBillsByAccountId(UUID id) {
        ResponseEntity<List<BillRequestDTO>> responseEntity =
                restTemplate.exchange(
                        String.format(billsByAccountIdUrl,id),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<BillRequestDTO>>() {}
                );

        return responseEntity.getBody();
    }




    @Override
    public void createPerson(PersonResponseDTO person) throws Exception {
        ResponseEntity<PersonResponseDTO> responseEntity =
                restTemplate.postForEntity(peopleUrl, person,PersonResponseDTO.class);
        if(responseEntity.getStatusCode() != HttpStatus.CREATED){
            throw new Exception(responseEntity.toString());
        }
    }

    @Override
    public void createAccount(AccountResponseDTO account) throws Exception {
        ResponseEntity<AccountResponseDTO> responseEntity =
                restTemplate.postForEntity(accountUrl, account, AccountResponseDTO.class);
        if(responseEntity.getStatusCode() != HttpStatus.CREATED){
            throw new Exception(responseEntity.toString());
        }
    }

    @Override
    public void createBill(BillResponseDTO bill) throws Exception {
        ResponseEntity<BillResponseDTO> responseEntity =
                restTemplate.postForEntity(billsUrl, bill, BillResponseDTO.class);
        if(responseEntity.getStatusCode() != HttpStatus.CREATED){
            throw new Exception(responseEntity.toString());
        }
    }



    @Override
    public void updatePerson(UUID id, PersonResponseDTO personResponseDTO){
        Map< String, UUID > params = new HashMap< String, UUID >();
        params.put("id", id);
        restTemplate.put(String.format(personUrl,id), personResponseDTO, params);
    }

    @Override
    public void updateAccount(UUID accountId, AccountResponseDTO accountResponseDTO) {
        Map< String, UUID > params = new HashMap< String, UUID >();
        params.put("id", accountId);
        restTemplate.put(accountUrl+"/"+accountId, accountResponseDTO, params);
    }


    @Override
    public void updateBill(UUID billId, BillResponseDTO billResponseDTO) {
        Map< String, UUID > params = new HashMap< String, UUID >();
        params.put("id", billId);
        restTemplate.put(billsUrl+"/"+billId, billResponseDTO, params);
    }



    @Override
    public void deletePerson(UUID id) {
        restTemplate.delete(String.format(personUrl,id));
    }

    @Override
    public void deleteAccount(UUID id) {
        restTemplate.delete(accountUrl+"/"+id);
    }

    @Override
    public void deleteBill(UUID id) {
        restTemplate.delete(billsUrl+"/"+id);
    }

    @Override
    public Collection<FinancialTransactionRequestDTO> getFinancialTransactionsByBillId(UUID billId) {
        ResponseEntity<List<FinancialTransactionRequestDTO>> responseEntity =
                restTemplate.exchange(
                        String.format(billsUrl+"/%s/transactions",billId),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<FinancialTransactionRequestDTO>>() {}
                );

        return responseEntity.getBody();
    }

}
