package ru.kondrashov.accountservice.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.UUID;

import static javax.persistence.TemporalType.TIMESTAMP;

@Entity
@Data
@Table(name = "accounts", schema = "public")
@EntityListeners(AuditingEntityListener.class)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String name;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date creationDate;

    @NotEmpty(message = "Account should not be without person")
    @Column(name="person_id")
    private UUID personId;
}
