package com.portfolio.my_portfolio_backend.repository;

import java.util.List;
import java.util.Optional;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;

public interface IPersonalInfoRepository {
    PersonalInfo save(PersonalInfo personalInfo);
    Optional<PersonalInfo> findbyId(Long id);
    List<PersonalInfo> findAll();
    void deleteById(Long id);   
}
