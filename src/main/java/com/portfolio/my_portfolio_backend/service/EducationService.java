package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.repository.IEducationRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EducationService implements IEducationService{

    private final IEducationRepository educationRepository;

    @Override
    public Education save(Education education) {
        if(education.getStartDate() == null){
            throw new IllegalArgumentException("La fecha de incio de la educación no puede estar vacía.");
        }
        if(education.getEndDate() != null && education.getStartDate().isAfter(education.getEndDate())){
            throw new IllegalArgumentException("La fecha de inicio de la educación no puede ser posterior a la fecha fin");
        }
        return this.educationRepository.save(education);
    }

    @Override
    public Optional<Education> findbyId(Long id) {
        return this.educationRepository.findbyId(id);
    }

    @Override
    public List<Education> findAll() {
        return this.educationRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.educationRepository.deleteById(id);
    }

    @Override
    public List<Education> findByPesonalInfoId(Long personalInfoId) {
        return this.educationRepository.findByPesonalInfoId(personalInfoId);
    }

}
