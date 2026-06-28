package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.repository.IExperienceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExperienceService implements IExperienceService{

    private final IExperienceRepository experienceRepository;

    @Override
    public Experience save(Experience experience) {
        if(experience.getStartDate() == null){
            throw new IllegalArgumentException("La fecha de incio de la experiencia no puede estar vacía.");
        }
        if(experience.getEndDate() != null && experience.getStartDate().isAfter(experience.getEndDate())){
            throw new IllegalArgumentException("La fecha de inicio de la experiencia no puede ser posterior a la fecha fin");
        }
        if(experience.getJobTitle() == null || experience.getJobTitle().isBlank()){
            throw new IllegalArgumentException("El titulo de trabajo no puede estar vacio");
        }
        if(experience.getCompanyName() == null || experience.getCompanyName().isBlank()){
            throw new IllegalArgumentException("El nombre de la compañia no puede estar vacio");
        }
        return this.experienceRepository.save(experience);
    }

    @Override
    public Optional<Experience> findbyId(Long id) {
        return this.experienceRepository.findbyId(id);
    }

    @Override
    public List<Experience> findAll() {
        return this.experienceRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.experienceRepository.deleteById(id);
    }

    @Override
    public List<Experience> findByPesonalInfoId(Long personalInfoId) {
        return this.experienceRepository.findByPesonalInfoId(personalInfoId);
    }

}
