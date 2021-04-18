package ru.kondrashov.accountservice.controllers;

import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import ru.kondrashov.accountservice.controllers.dto.AccountRequestDTO;
import ru.kondrashov.accountservice.controllers.dto.AccountResponseDTO;
import ru.kondrashov.accountservice.services.interfacies.AccountsService;
import ru.kondrashov.accountservice.utils.interfacies.AccountMapping;

import javax.validation.Valid;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping( value = "/v1", produces = MediaType.APPLICATION_JSON_VALUE)
@Api(description = "Controller which provides methods for operations with accounts", tags = {"ACCOUNTS"})
public class AccountsController {

    private final AccountsService accountsService;
    private final AccountMapping accountMapping;

    public AccountsController(AccountsService accountsService, AccountMapping accountMapping) {
        this.accountsService = accountsService;
        this.accountMapping = accountMapping;
    }

    @GetMapping("/accounts")
    @ResponseStatus(HttpStatus.OK)
    public Collection<AccountResponseDTO> getAccounts(){
        return accountsService
                .getAccounts()
                .stream()
                .map(account -> accountMapping.mapToAccountResponseDTO(account))
                .collect(Collectors.toList());
    }

    @GetMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountResponseDTO getAccount(@PathVariable("id") UUID id){
        return accountMapping.mapToAccountResponseDTO(accountsService.getAccount(id));
    }

    @GetMapping("/person/{id}/accounts")
    @ResponseStatus(HttpStatus.OK)
    public Collection<AccountResponseDTO> getAccountsByPersonId(@PathVariable("id") UUID id){
        return accountsService
                .getAccountsByPersonId(id)
                .stream()
                .map(account -> accountMapping.mapToAccountResponseDTO(account))
                .collect(Collectors.toList());
    }

    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid AccountRequestDTO account){
        accountsService.save(accountMapping.mapToAccount(account));
    }

    @Transactional(rollbackFor = Exception.class)
    @PutMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void update(@PathVariable("id") UUID id, @RequestBody @Valid AccountRequestDTO account){
        accountsService.update(id,accountMapping.mapToAccount(account));
    }

    @Transactional(rollbackFor = Exception.class)
    @DeleteMapping("/accounts/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") UUID id){
        accountsService.delete(id);
    }
}
