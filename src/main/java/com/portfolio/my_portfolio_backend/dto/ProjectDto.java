package com.portfolio.my_portfolio_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDto {
    private Long id;
    @NotBlank(message = "El titulo no puede estar en blanco.")
    @Size(min = 2, max = 255, message = "El título debe tener entre 2 y 255 caractreres.")
    private String title;
    @NotBlank(message = "La descripción no puede estar en blanco.")
    @Size(min = 10, message = "La descripción debe tener al menos 10 caracteres.")
    private String description;
    // Se asigna en el controlador tras subir el archivo; no viene del formulario
    private String imageUrl;
    @NotBlank(message = "La URL del proyecto no tiene un formato válido.")
    private String projectUrl;
    @NotNull(message = "El ID de información personal no puede ser nulo.")
    @Min(value = 1, message = "El ID de información personal debe ser un número positivo.")
    private Long personalInfoId;
}
