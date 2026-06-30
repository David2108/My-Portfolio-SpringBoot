package com.portfolio.my_portfolio_backend.model;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Experience {
    private Long id;

    @NotBlank(message = "El titulo de trabajo no puede estar vacio")
    private String jobTitle;

    @NotBlank(message = "El nombre de la compañia no puede estar vació")
    private String companyName;

    @NotNull(message = "La fecha de inicio no puede ser vacia")
    @PastOrPresent(message = "La fecha de inicio debe ser menor o igual a la fecha actual")
    private LocalDate startDate;

    @PastOrPresent(message = "La fecha de fin debe ser menor o igual a la fecha actual")
    private LocalDate endDate;

    @NotBlank(message = "La descripción no puede estar vacia")
    private String description;

    private Long personalInfoId;
}
