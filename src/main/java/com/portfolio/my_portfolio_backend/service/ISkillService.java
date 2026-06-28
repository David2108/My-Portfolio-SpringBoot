package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_portfolio_backend.model.Skill;

public interface ISkillService {
    Skill save(Skill skill);
    Optional<Skill> findbyId(Long id);
    List<Skill> findAll();
    void deleteById(Long id);
    List<Skill> findByPesonalInfoId(Long personalInfoId);
}
