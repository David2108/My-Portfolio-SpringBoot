package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.dto.ExperienceDto;
import com.portfolio.my_portfolio_backend.dto.PersonalInfoDto;
import com.portfolio.my_portfolio_backend.mapper.PersonalInfoMapper;
import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.service.IPersonalInfoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/personal-info")
@RequiredArgsConstructor
public class PersonalInfoController {

    private final IPersonalInfoService personalInfoService;

    // Dado que suele haber una única PersonalInfo, a menudo se usa un ID fijo (ej. 1L)
    // o se redirige directamente al formulario de edición si se accede a "/personal-info"
    private static final Long DEFAULT_PERSONAL_INFO_ID = 1L; // Asumimos un ID por defecto

    @GetMapping
    public String viewPersonalInfo(Model model) {
        return "redirect:/personal-info/edit/" + DEFAULT_PERSONAL_INFO_ID;
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Optional<PersonalInfo> personalInfoOptional = personalInfoService.findbyId(id);
        if (personalInfoOptional.isPresent()) {
            PersonalInfoDto personalInfoDto = PersonalInfoMapper.toDto(personalInfoOptional.get());
            model.addAttribute("personalInfoDto", personalInfoDto);
            return "personalinfo/form-personal-info";
        } else {
            // Si no se encuentra, se podría ofrecer crear una nueva o mostrar un error
            // Para el propósito del portfolio, asumimos que siempre debería existir una
            // y si no, se crea una en blanco para rellenar.
            model.addAttribute("personalInfoDto", new PersonalInfoDto());
            redirectAttributes.addFlashAttribute("error", "No se encontró información personal. Por favor, crea una nueva.");
            return "personalinfo/form-personal-info"; // O redirigir a un formulario de creación explícito
        }
    }

    @PostMapping("/save")
    public String savePersonalInfo(@Valid @ModelAttribute("personalInfoDto") PersonalInfoDto personalInfoDto,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "personalinfo/form-personal-info";
        }

        try {
            PersonalInfo personalInfo = PersonalInfoMapper.toEntity(personalInfoDto);
            personalInfoService.save(personalInfo);
            redirectAttributes.addFlashAttribute("message", "Información personal guardada con éxito!");
            return "redirect:/personal-info/edit/" + personalInfo.getId(); // Redirige a la edición de la misma info
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error al guardar la información personal: " + e.getMessage());
            // Si hubo un error, volvemos al formulario para que el usuario pueda corregir
            // Y mantenemos el DTO en el modelo para que los datos ingresados no se pierdan.
            return "personalinfo/form-personal-info";
        }
    }

    // Si quieres un endpoint para crear la primera PersonalInfo, podrías tener:
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("personalInfoDto", new PersonalInfoDto());
        return "personalinfo/form-personal-info";
    }

}
