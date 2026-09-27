package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.dto.EducationDto;
import com.portfolio.my_portfolio_backend.mapper.EducationMapper;
import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.service.IEducationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/education")
@RequiredArgsConstructor
public class EducationController {

    private final IEducationService educationService;

    @GetMapping
    public String educations(Model model) {
        List<EducationDto> educations = educationService.findAll()
                .stream()
                .map(EducationMapper::toDto)
                .toList();
        model.addAttribute("educationList", educations);
        return "education/list-education";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("educationDto", new EducationDto());
        return "education/form/form-education";
    }

    @PostMapping("/save")
    public String saveEducation(@Valid @ModelAttribute("educationDto") EducationDto educationDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "education/form/form-education";
        }

        try{
            Education education = EducationMapper.toEntity(educationDto);
            educationService.save(education);
            redirectAttributes.addFlashAttribute("message", "Educación guardada con éxito");
            return "redirect:/education";
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Error al guardar educación: " + e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Education> educationOptional = educationService.findbyId(id);
        if(educationOptional.isPresent()){
            EducationDto education = educationOptional.stream().map(EducationMapper::toDto).findFirst().get();
            model.addAttribute("educationDto", education);
            return "education/form/form-education";
        }else {
            model.addAttribute("errorMessage", "Educacion no encontrada con ID: " +  id);
            return "redirect:/education";
        }
    }

    @GetMapping("/personal/{personalInfoId}")
    public String listEducationsByPersonalInfoId(@PathVariable Long personalInfoId, Model model) {
        List<EducationDto> educations = educationService.findByPesonalInfoId(personalInfoId)
                .stream()
                .map(EducationMapper::toDto)
                .toList();
        model.addAttribute("educationList", educations);
        return "education/list-education";
    }

    @PostMapping("/delete/{id}")
    public String deleteEducation(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try{
            educationService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Educación eliminada con éxito!");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la educación: " + e.getMessage());
        }
        return "redirect:/education";
    }

}
