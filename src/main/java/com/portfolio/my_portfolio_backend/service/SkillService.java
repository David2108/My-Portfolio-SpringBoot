package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.repository.ISkillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillService implements ISkillService{

    private final ISkillRepository skillRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Skill> findAll() {
        return this.skillRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Skill> findbyId(Long id) {
        return this.skillRepository.findbyId(id);
    }

    @Override
    @Transactional
    public Skill save(Skill skill) {
        return this.skillRepository.save(skill);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.skillRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Skill> findByPesonalInfoId(Long personalInfoId) {
        return this.skillRepository.findByPesonalInfoId(personalInfoId);
    }

}
