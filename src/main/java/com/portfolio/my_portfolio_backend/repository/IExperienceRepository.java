package com.portfolio.my_portfolio_backend.repository;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_portfolio_backend.model.Experience;

public interface IExperienceRepository {
    Experience save(Experience experience);
    Optional<Experience> findbyId(Long id);
    List<Experience> findAll();
    void deleteById(Long id);
    List<Experience> findByPesonalInfoId(Long personalInfoId);
}
