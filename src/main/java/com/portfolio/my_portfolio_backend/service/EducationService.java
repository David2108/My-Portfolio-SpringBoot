package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.repository.IEducationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EducationService implements IEducationService{

    private final IEducationRepository educationRepository;
    private final Validator validator;

    @Override
    @Transactional(readOnly = true)
    public Optional<Education> findbyId(Long id) {
        return this.educationRepository.findbyId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findAll() {
        return this.educationRepository.findAll();
    }

    @Override
    @Transactional
    public Education save(Education education) {
        BindingResult result = new BeanPropertyBindingResult(education, "education");
        this.validator.validate(education, result);
        if(result.hasErrors()){
            throw new ValidationException(result);
        }
        return this.educationRepository.save(education);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.educationRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> findByPesonalInfoId(Long personalInfoId) {
        return this.educationRepository.findByPesonalInfoId(personalInfoId);
    }

}
