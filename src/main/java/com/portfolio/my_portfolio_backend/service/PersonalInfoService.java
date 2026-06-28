package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.repository.IPersonalInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalInfoService implements IPersonalInfoService{

    private final IPersonalInfoRepository personalInfoRepository;

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        return this.personalInfoRepository.save(personalInfo);
    }

    @Override
    public Optional<PersonalInfo> findbyId(Long id) {
        return this.personalInfoRepository.findbyId(id);
    }

    @Override
    public List<PersonalInfo> findAll() {
        return this.personalInfoRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        this.personalInfoRepository.deleteById(id);
    }

}
