package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.repository.ISkillRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SkillService implements ISkillService{

    private final ISkillRepository skillRepository;

    @Override
    public Skill save(Skill skill) {
        if(skill.getLevelPercentage() < 0 || skill.getLevelPercentage() > 100){
            throw new IllegalArgumentException("El porcentaje es inconrrecto debe estar entre 0 y 100");
        }
        return this.skillRepository.save(skill);
    }

    @Override
    public Optional<Skill> findbyId(Long id) {
        return this.skillRepository.findbyId(id);
    }

    @Override
    public List<Skill> findAll() {
        return this.skillRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.skillRepository.deleteById(id);
    }

    @Override
    public List<Skill> findByPesonalInfoId(Long personalInfoId) {
        return this.skillRepository.findByPesonalInfoId(personalInfoId);
    }

}
