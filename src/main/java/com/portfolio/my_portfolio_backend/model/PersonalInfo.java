package com.portfolio.my_portfolio_backend.model;

import org.hibernate.validator.constraints.URL;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalInfo {
    private Long id;

    @NotBlank(message = "El nombre no puede estar vacio") // Valida que no sea nula y no tenga espacios en blanco
    private String firstName;
    @NotBlank(message = "El apellido no puede estar vacio")
    private String lastName;
    @NotBlank(message = "El titulo no puede estar vacio")
    private String title;
    @NotBlank(message = "La descripción del perfil no puede estar vacia")
    private String profileDescription;
    @NotBlank(message = "La imagen del perfil no puede estar vacia")
    private String profileImageUrl;
    @Min(value = 0, message = "Los años de experiencia no pueden ser negativos")
    private Integer yearsOfExperience;
    @Email(message = "El email no es valido")
    private String email;
    @NotBlank(message = "El telefono no puede estar vacio")
    private String phone;
    @URL(message = "Linkedin es una red obligatoria")
    private String linkedinUrl;
    @URL(message = "Github es una red obligatoria")
    private String githubUrl;
}
