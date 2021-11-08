package com.psp.validator.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Vardas yra privalomas")
    private String vardas;

    @NotNull(message = "Pavarde yra privaloma")
    private String pavarde;

    @NotNull(message = "Telefono numeris yra privalomas")
    private String telNr;

    @NotNull(message = "El pastas yra privalomas")
    private String email;

    @NotNull(message = "Adresas yra privalomas")
    private String adresas;

    @NotNull(message = "Slaptazodis yra privalomas")
    private String slaptazodis;
}
