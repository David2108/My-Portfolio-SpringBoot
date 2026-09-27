package com.portfolio.my_portfolio_backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EducationDto {
    private Long id;

    @NotBlank(message = "El grado no puede estar vacio")
    private String degree;

    @NotBlank(message = "La institución no puede estar vació")
    private String institution;

    @NotNull(message = "La fecha de inicio no puede ser nulo")
    @PastOrPresent(message = "La fecha de inicio debe ser mayor o igual a la fecha actual")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    
    @PastOrPresent(message = "La fecha de fin debe ser mayor o igual a la fecha actual")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @NotBlank(message = "La descripción no puede estar vacia")
    private String description;

    @NotNull(message = "El ID de información personal es obligatorio.")
    private Long personalInfoId;
}
