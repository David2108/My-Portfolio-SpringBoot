package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.repository.IExperienceRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExperienceServiceImpl implements IExperienceService{

    private final IExperienceRepository experienceRepository;

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
