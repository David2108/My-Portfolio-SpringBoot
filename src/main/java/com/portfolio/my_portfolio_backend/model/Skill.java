package com.portfolio.my_portfolio_backend.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Skill {
    private Long id;

    @NotBlank(message = "El nombre de la habilidad no puede estar vacio")
    private String name;
    
    @NotNull(message = "El porcentaje no puede ser nulo")
    @Min(value = 0, message="El porcentaje debe ser mayor o igual a 0")
    @Max(value = 100, message = "El porcentaje debe ser menor o igual a 100")
    private Integer levelPercentage;
    
    @NotBlank(message = "La clase del icono no puede estar vacia")
    private String iconClass;
    
    private Long personalInfoId;
}
