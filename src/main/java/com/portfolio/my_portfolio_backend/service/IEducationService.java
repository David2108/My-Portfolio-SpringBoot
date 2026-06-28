package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_portfolio_backend.model.Education;

public interface IEducationService {
    Education save(Education education);
    Optional<Education> findbyId(Long id);
    List<Education> findAll();
    void deleteById(Long id);
    List<Education> findByPesonalInfoId(Long personalInfoId);
}
