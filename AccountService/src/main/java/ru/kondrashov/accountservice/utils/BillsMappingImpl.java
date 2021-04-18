package ru.kondrashov.accountservice.utils;

import org.springframework.stereotype.Component;
import ru.kondrashov.accountservice.controllers.dto.BillRequestDTO;
import ru.kondrashov.accountservice.controllers.dto.BillResponseDTO;
import ru.kondrashov.accountservice.entities.Bill;
import ru.kondrashov.accountservice.utils.interfacies.BillsMapping;

@Component
public class BillsMappingImpl implements BillsMapping {

    @Override
    public Bill mapToBill(BillRequestDTO billRequestDTO) {

        return new Bill(billRequestDTO.getCurrency(),billRequestDTO.getAmount(),billRequestDTO.getIsOverdraft(),billRequestDTO.getAccount());
    }

    @Override
    public BillResponseDTO mapToBillResponseDTO(Bill bill) {

        return new BillResponseDTO(bill.getId(),bill.getCurrency(),bill.getAmount(),bill.getIsOverdraft(),bill.getAccount());
    }
}
