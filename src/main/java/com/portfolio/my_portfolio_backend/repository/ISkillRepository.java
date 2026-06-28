package com.portfolio.my_portfolio_backend.repository;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_portfolio_backend.model.Skill;

public interface ISkillRepository {
    Skill save(Skill skill);
    Optional<Skill> findbyId(Long id);
    List<Skill> findAll();
    void deleteById(Long id);
    List<Skill> findByPesonalInfoId(Long personalInfoId);
}
