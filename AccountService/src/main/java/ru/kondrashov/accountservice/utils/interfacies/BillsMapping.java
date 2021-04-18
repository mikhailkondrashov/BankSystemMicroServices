package ru.kondrashov.accountservice.utils.interfacies;

import ru.kondrashov.accountservice.controllers.dto.BillRequestDTO;
import ru.kondrashov.accountservice.controllers.dto.BillResponseDTO;
import ru.kondrashov.accountservice.entities.Bill;

public interface BillsMapping {

    Bill mapToBill(BillRequestDTO billRequestDTO);
    BillResponseDTO mapToBillResponseDTO(Bill bill);
}
