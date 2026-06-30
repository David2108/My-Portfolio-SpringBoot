package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.repository.IExperienceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExperienceService implements IExperienceService{

    private final IExperienceRepository experienceRepository;
    private final Validator validator;

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findAll() {
        return this.experienceRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Experience> findbyId(Long id) {
        return this.experienceRepository.findbyId(id);
    }

    @Override
    @Transactional
    public Experience save(Experience experience) {
        BindingResult result = new BeanPropertyBindingResult(experience, "experience");
        this.validator.validate(experience, result);
        if(result.hasErrors()){
            throw new ValidationException(result);
        }
        return this.experienceRepository.save(experience);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.experienceRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experience> findByPesonalInfoId(Long personalInfoId) {
        return this.experienceRepository.findByPesonalInfoId(personalInfoId);
    }

}
