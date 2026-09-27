package com.portfolio.my_portfolio_backend.controller;

import com.portfolio.my_portfolio_backend.dto.ExperienceDto;
import com.portfolio.my_portfolio_backend.mapper.ExperienceMapper;
import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.service.IExperienceService;
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
@RequestMapping("/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final IExperienceService experienceService;

    @GetMapping
    public String experiences(Model model) {
        List<ExperienceDto> experiences = experienceService.findAll()
                .stream()
                .map(ExperienceMapper::toDto)
                .toList();
        model.addAttribute("experienceList", experiences);
        return "experience/list-experience";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        ExperienceDto experienceDto = new ExperienceDto();
        experienceDto.setStartDate(LocalDate.now());
        model.addAttribute("experienceDto", experienceDto);
        return "experience/form/form-experience";
    }

    @PostMapping("/save")
    public String saveExperience(@Valid @ModelAttribute("experienceDto") ExperienceDto experienceDto, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "experiences/form/form-experience";
        }

        try{
            Experience experience = ExperienceMapper.toEntity(experienceDto);
            experienceService.save(experience);
            redirectAttributes.addFlashAttribute("message", "Experiencia guardada con éxito");
            return "redirect:/experience";
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Error al guardar la experience laboral: " + e.getMessage());
            return "error-page";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Experience> experienceOptional = experienceService.findbyId(id);
        if(experienceOptional.isPresent()){
            ExperienceDto experience = experienceOptional.stream().map(ExperienceMapper::toDto).findFirst().get();
            model.addAttribute("experienceDto", experience);
            return "experience/form/form-experience";
        }else {
            model.addAttribute("errorMessage", "Experiencia laboral no encontrada con ID: " +  id);
            return "redirect:/experience";
        }
    }

    @GetMapping("/personal/{personalInfoId}")
    public String listExperiencesByPersonalInfoId(@PathVariable Long personalInfoId, Model model) {
        List<ExperienceDto> experiences = experienceService.findByPesonalInfoId(personalInfoId)
                .stream()
                .map(ExperienceMapper::toDto)
                .toList();
        model.addAttribute("experienceList", experiences);
        return "experience/list-experience";
    }

    @PostMapping("/delete/{id}")
    public String deleteExperience(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        try{
            experienceService.deleteById(id);
            redirectAttributes.addFlashAttribute("message", "Experiencia laboral eliminada con éxito!");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("error", "Error al eliminar la experiencia laboral: " + e.getMessage());
        }
        return "redirect:/experience";
    }

}
