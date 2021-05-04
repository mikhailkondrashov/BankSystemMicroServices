package ru.kondrashov.commonservice.services;

import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.kondrashov.commonservice.controllers.dto.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements CommonService{

    private final RestTemplate restTemplate;
    private final Logger logger;

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
        logger.info("Send GET request to "+peopleUrl);
        ResponseEntity<List<PersonRequestDTO>> responseEntity =
                restTemplate.exchange(
                        peopleUrl,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<PersonRequestDTO>>() {}
                );
        logger.info("Got response with status code "+ responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    @Override
    public Collection<AccountRequestDTO> getAccountsByPerson(UUID id) {
        logger.info("Send GET request to "+String.format(accountsUrl,id));
        ResponseEntity<List<AccountRequestDTO>> responseEntity =
                restTemplate.exchange(
                        String.format(accountsUrl,id),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<AccountRequestDTO>>() {}
                );
        logger.info("Got response with status code "+ responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    @Override
    public Collection<BillRequestDTO> getBillsByAccountId(UUID personId, UUID accountId) {
        logger.info("Send GET request to "+String.format(billsUrl,personId,accountId));
        ResponseEntity<List<BillRequestDTO>> responseEntity =
                restTemplate.exchange(
                        String.format(billsUrl,personId,accountId),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<BillRequestDTO>>() {}
                );
        logger.info("Got response with status code "+ responseEntity.getStatusCode());
        return responseEntity.getBody();
    }

    @Override
    public Collection<FinancialTransactionRequestDTO> getFinancialTransactionsByBillId(UUID personId, UUID accountId, UUID billId) {
        logger.info("Send GET request to "+String.format(transactionsUrl,personId, accountId, billId));
        ResponseEntity<List<FinancialTransactionRequestDTO>> responseEntity =
                restTemplate.exchange(
                        String.format(transactionsUrl,personId, accountId, billId),
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<List<FinancialTransactionRequestDTO>>() {}
                );
        logger.info("Got response with status code "+ responseEntity.getStatusCode());
        return responseEntity.getBody();
    }


    @Override
    public void createPerson(PersonResponseDTO person) throws Exception {
        logger.info("Send POST request to "+peopleUrl);
        ResponseEntity<PersonResponseDTO> responseEntity =
                restTemplate.postForEntity(peopleUrl, person,PersonResponseDTO.class);
        if(responseEntity.getStatusCode() != HttpStatus.CREATED){
            logger.error(responseEntity.toString());
            throw new Exception(responseEntity.toString());
        }
        logger.info("Got response with status code "+ responseEntity.getStatusCode());
    }

    @Override
    public void createAccount(UUID personId, AccountResponseDTO account) throws Exception {
        logger.info("Send POST request to "+String.format(accountsUrl, personId));
        ResponseEntity<AccountResponseDTO> responseEntity =
                restTemplate.postForEntity(String.format(accountsUrl, personId), account, AccountResponseDTO.class);
        if(responseEntity.getStatusCode() != HttpStatus.CREATED){
            logger.error(responseEntity.toString());
            throw new Exception(responseEntity.toString());
        }
        logger.info("Got response with status code "+ responseEntity.getStatusCode());
    }

    @Override
    public void createBill(UUID personId, UUID accountId, BillResponseDTO bill) throws Exception {
        logger.info("Send POST request to "+String.format(billsUrl, personId, accountId));
        ResponseEntity<BillResponseDTO> responseEntity =
                restTemplate.postForEntity(String.format(billsUrl, personId, accountId), bill, BillResponseDTO.class);
        if(responseEntity.getStatusCode() != HttpStatus.CREATED){
            logger.error(responseEntity.toString());
            throw new Exception(responseEntity.toString());
        }
        logger.info("Got response with status code "+ responseEntity.getStatusCode());
    }


    @Override
    public void updatePerson(UUID id, PersonResponseDTO personResponseDTO){
        logger.info("Send PUT request to "+String.format(personUrl,id));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("id", Collections.singletonList(id.toString()));
        HttpEntity<PersonResponseDTO> personRequestDTOHttpEntity = new HttpEntity<>(personResponseDTO, params);
        ResponseEntity<PersonResponseDTO> responseEntity = restTemplate.exchange(String.format(personUrl,id),HttpMethod.PUT, personRequestDTOHttpEntity, PersonResponseDTO.class);
        logger.info("Got response with status code "+responseEntity.getStatusCode());
    }

    @Override
    public void updateAccount(UUID personId, UUID accountId, AccountResponseDTO accountResponseDTO) {
        logger.info("Send PUT request to "+String.format(accountUrl, personId, accountId));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("id", Collections.singletonList(accountId.toString()));
        HttpEntity<AccountResponseDTO> accountResponseDTOHttpEntity = new HttpEntity<>(accountResponseDTO, params);
        ResponseEntity<AccountResponseDTO> responseEntity = restTemplate.exchange(String.format(accountUrl, personId, accountId),HttpMethod.PUT, accountResponseDTOHttpEntity, AccountResponseDTO.class);
        logger.info("Got response with status code "+responseEntity.getStatusCode());
    }


    @Override
    public void updateBill(UUID personId, UUID accountId, UUID billId, BillResponseDTO billResponseDTO) {
        logger.info("Send PUT request to "+String.format(billUrl, personId, accountId, billId));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("id", Collections.singletonList(billId.toString()));
        HttpEntity<BillResponseDTO> billResponseDTOHttpEntity = new HttpEntity<>(billResponseDTO, params);
        ResponseEntity<BillResponseDTO> responseEntity = restTemplate.exchange(String.format(billUrl, personId, accountId, billId), HttpMethod.PUT, billResponseDTOHttpEntity, BillResponseDTO.class);
        logger.info("Got response with status code "+responseEntity.getStatusCode());
    }


    @Override
    public void deletePerson(UUID id) {
        logger.info("Send DELETE request to "+String.format(personUrl,id));
        for (AccountRequestDTO account:getAccountsByPerson(id)) {
            deleteAccount(id,account.getId());
        }

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("id", Collections.singletonList(id.toString()));
        ResponseEntity<Object> responseEntity = restTemplate.exchange(String.format(personUrl,id), HttpMethod.DELETE, null, Object.class);
        logger.info("Got response with status code "+responseEntity.getStatusCode());
    }

    @Override
    public void deleteAccount(UUID personId, UUID accountId) {
        logger.info("Send DELETE request to "+String.format(accountUrl, personId, accountId));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("id", Collections.singletonList(accountId.toString()));
        ResponseEntity<Object> responseEntity = restTemplate.exchange(String.format(accountUrl, personId, accountId), HttpMethod.DELETE, null, Object.class);
        logger.info("Got response with status code "+responseEntity.getStatusCode());
    }

    @Override
    public void deleteBill(UUID personId, UUID accountId, UUID billId) {
        logger.info("Send DELETE request to "+String.format(billUrl, personId, accountId, billId));

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.put("id", Collections.singletonList(accountId.toString()));
        ResponseEntity<Object> responseEntity = restTemplate.exchange(String.format(billUrl, personId, accountId, billId), HttpMethod.DELETE, null, Object.class);
        logger.info("Got response with status code "+responseEntity.getStatusCode());
    }
}
