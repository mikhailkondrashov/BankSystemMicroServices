package ru.kondrashov.commonservice.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.kondrashov.commonservice.controllers.dto.*;
import ru.kondrashov.commonservice.services.CommonService;

import javax.validation.Valid;
import java.util.Collection;
import java.util.Currency;
import java.util.UUID;

@Controller
@RequestMapping(value = "/common/v1", produces = MediaType.TEXT_HTML_VALUE)
public class CommonController {

    private final CommonService commonService;

    public CommonController(CommonService commonService) {
        this.commonService = commonService;
    }

    @GetMapping("/info/people")
    @ResponseStatus (HttpStatus.OK)
    public String getPeople(Model model){
        model.addAttribute("people",commonService.getPeople());
        return "people/people";
    }

    @GetMapping("/info/people/{id}")
    @ResponseStatus (HttpStatus.OK)
    public String getPerson(@PathVariable UUID id, Model model) {
        model.addAttribute("person", commonService.getPersonById(id));
        model.addAttribute("accounts", commonService.getAccountsByPerson(id));
        return "people/person";
    }

    @GetMapping("/info/people/{personId}/accounts/{accountId}")
    @ResponseStatus (HttpStatus.OK)
    public String getAccount(@PathVariable("personId") UUID personId, @PathVariable("accountId") UUID accountId, Model model) {
        model.addAttribute(
                "account",
                commonService.
                        getAccountsByPerson(personId)
                        .stream()
                        .filter(account -> account.getId().equals(accountId))
                        .findFirst().orElseThrow(() -> new RuntimeException("Person with id " + personId+" dont have account with id "+accountId))
        );
        model.addAttribute("bills",commonService.getBillsByAccountId(accountId));
        return "accounts/account";
    }

    @GetMapping("/info/accounts/{accountId}/bills/{billId}")
    @ResponseStatus (HttpStatus.OK)
    public String getBill(@PathVariable("accountId") UUID accountId, @PathVariable("billId") UUID billId, Model model) {
        model.addAttribute(
                "bill",
                commonService.
                        getBillsByAccountId(accountId)
                        .stream()
                        .filter(bill -> bill.getId().equals(billId))
                        .findFirst().orElseThrow(() -> new RuntimeException("Account with id " + accountId+" dont have bill with id "+billId))
        );
        model.addAttribute("personId", commonService.getAccountById(accountId).getPersonId());
        Collection<FinancialTransactionRequestDTO> financialTransactionRequestDTOS = commonService.getFinancialTransactionsByBillId(billId);
        model.addAttribute("transactions", financialTransactionRequestDTOS);

        return "bills/bill";
    }

    @GetMapping("/create/people/new")
    @ResponseStatus(HttpStatus.OK)
    public String newPerson(Model model){
        model.addAttribute("person", new PersonResponseDTO());
        return "people/new";
    }

    @GetMapping("/create/people/{personId}/accounts/new")
    @ResponseStatus(HttpStatus.OK)
    public String newAccount(@PathVariable("personId") UUID id, Model model){
        model.addAttribute("account", new AccountResponseDTO());
        model.addAttribute("id",id);
        return "accounts/new";
    }

    @GetMapping("/create/accounts/{id}/bills/new")
    @ResponseStatus(HttpStatus.OK)
    public String newBill(@PathVariable("id") UUID id, Model model){
        model.addAttribute("bill", new BillResponseDTO());
        model.addAttribute("currencies", Currency.getAvailableCurrencies());
        model.addAttribute("accountId", id);
        return "/bills/new";
    }

    @GetMapping("create/accounts/{accountId}/bills/{billId}/transfers/new")
    @ResponseStatus(HttpStatus.OK)
    public String newTransfer (@PathVariable("accountId") UUID accountId, @PathVariable("billId") UUID billId, Model model){
        model.addAttribute("accountId", accountId);
        model.addAttribute("billId",billId);
        model.addAttribute("transfer", new TransferResponseDTO());
        model.addAttribute("people", commonService.getPeople());
        //model.addAttribute("accounts", commonService.)
        return "/bills/transfers/new";
    }


    @PostMapping("/create/people")
    @ResponseStatus(HttpStatus.FOUND)
    public String createPerson(@ModelAttribute("person") @Valid PersonResponseDTO person, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "people/new";
        }
        try {
            commonService.createPerson(person);
            return "redirect:/common/v1/info/people";
        } catch (Exception e) {
            return "people/new";
        }
    }

    @PostMapping("/create/people/{id}/accounts")
    @ResponseStatus(HttpStatus.FOUND)
    public String createAccount(@ModelAttribute("account") @Valid AccountResponseDTO account, BindingResult bindingResult, @PathVariable("id") UUID id){
        if(bindingResult.hasErrors()){
            return "accounts/new";
        }
        try {
            account.setPersonId(id);
            commonService.createAccount(account);
            return "redirect:/common/v1/info/people/"+id;
        } catch (Exception e) {
            return "accounts/new";
        }
    }

    @PostMapping("create/accounts/{id}/bills")
    @ResponseStatus(HttpStatus.FOUND)
    public String createBill(@ModelAttribute("bill") @Valid BillResponseDTO bill, BindingResult bindingResult, @PathVariable("id") UUID id){
        if(bindingResult.hasErrors()){
            return "/bills/new";
        }
        AccountRequestDTO accountRequestDTO = commonService.getAccountById(id);
        bill.setAccount(accountRequestDTO);
        try {
            commonService.createBill(bill);
        } catch (Exception e) {
            return "/bills/new";
        }
        return String.format("redirect:/common/v1/info/people/%s/accounts/%s",accountRequestDTO.getPersonId(),accountRequestDTO.getId());
    }




    @GetMapping("/update/people/{id}/edit")
    @ResponseStatus(HttpStatus.OK)
    public String editPerson(@PathVariable("id") UUID id, Model model){
        model.addAttribute("person", commonService.getPersonById(id));
        model.addAttribute("id",id);
        return "people/edit";
    }

    @GetMapping("/update/people/{personId}/accounts/{accountId}/edit")
    @ResponseStatus(HttpStatus.OK)
    public String editAccount(@PathVariable("personId") UUID personId, @PathVariable("accountId") UUID accountId, Model model){
        model.addAttribute("account", commonService.getAccountsByPerson(personId).stream().filter(account->account.getId().equals(accountId)).findFirst().get());
        model.addAttribute("id",accountId);
        return "accounts/edit";
    }

    @GetMapping("/update/accounts/{accountId}/bills/{billId}/edit")
    @ResponseStatus(HttpStatus.OK)
    public String editBill(@PathVariable("billId") UUID billId, @PathVariable("accountId") UUID accountId, Model model){
        model.addAttribute("bill", commonService.getBillsByAccountId(accountId).stream().filter(bill->bill.getId().equals(billId)).findFirst().get());
        model.addAttribute("currencies", Currency.getAvailableCurrencies());
        return "bills/edit";
    }



    @PutMapping("/update/people/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public String updatePerson(@PathVariable UUID id, @ModelAttribute("person") @Valid PersonResponseDTO personResponseDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "people/edit";
        }
        commonService.updatePerson(id, personResponseDTO);
        return "redirect:/common/v1/info/people/"+id;

    }

    @PutMapping("/update/people/{personId}/accounts/{accountId}")
    @ResponseStatus(HttpStatus.FOUND)
    public String updateAccount(@PathVariable("personId") UUID personId, @PathVariable("accountId") UUID accountId, @ModelAttribute("account") @Valid AccountResponseDTO accountResponseDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "accounts/edit";
        }
        commonService.updateAccount(accountId, accountResponseDTO);
        return "redirect:/common/v1/info/people/"+personId;

    }

    @PutMapping("/update/accounts/{accountId}/bills/{billId}")
    @ResponseStatus(HttpStatus.FOUND)
    public String updateBill(@PathVariable("billId") UUID billId, @PathVariable("accountId") UUID accountId, @ModelAttribute("bill") @Valid BillResponseDTO billResponseDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return "bills/edit";
        }

        billResponseDTO.setAccount(commonService.getAccountById(accountId));
        commonService.updateBill(billId, billResponseDTO);
        return String.format("redirect:/common/v1/info/people/%s/accounts/%s", commonService.getAccountById(accountId).getPersonId(),accountId);

    }



    @DeleteMapping("/delete/people/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public String deletePerson(@PathVariable UUID id){
        commonService.deletePerson(id);
        return "redirect:/common/v1/info/people";
    }

    @DeleteMapping("/delete/people/{personId}/accounts/{accountId}")
    @ResponseStatus(HttpStatus.FOUND)
    public String deleteAccount(@PathVariable("personId") UUID personId, @PathVariable("accountId") UUID accountId){
        commonService.deleteAccount(accountId);
        return "redirect:/common/v1/info/people/"+personId;
    }

    @DeleteMapping("/delete/accounts/{accountId}/bills/{billId}")
    @ResponseStatus(HttpStatus.FOUND)
    public String deleteBill(@PathVariable("billId") UUID billId, @PathVariable("accountId") UUID accountId){
        commonService.deleteBill(billId);

        return String.format("redirect:/common/v1/info/people/%s/accounts/%s", commonService.getAccountById(accountId).getPersonId(), accountId);
    }
}
