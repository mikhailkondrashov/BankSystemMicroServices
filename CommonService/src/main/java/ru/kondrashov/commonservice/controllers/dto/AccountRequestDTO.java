package ru.kondrashov.commonservice.controllers.dto;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
public class AccountRequestDTO {

    private UUID id;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    private UUID personId;

}
