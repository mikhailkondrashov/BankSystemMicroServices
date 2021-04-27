package ru.kondrashov.accountservice.controllers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDTO {

    @NotEmpty (message = "Name shouldn't be empty")
    @NotNull(message = "Name shouldn't be null")
    private String name;

    @NotNull(message = "Creation date shouldn't be null")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate creationDate;

    @NotNull(message = "Person Id shouldn't be null")
    private UUID personId;
}
