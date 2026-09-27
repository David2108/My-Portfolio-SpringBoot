package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.repository.IPersonalInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalInfoServiceImpl implements IPersonalInfoService{

    private final IPersonalInfoRepository personalInfoRepository;

    @Override
    @Transactional
    public PersonalInfo save(PersonalInfo personalInfo) {
        return this.personalInfoRepository.save(personalInfo);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PersonalInfo> findbyId(Long id) {
        return this.personalInfoRepository.findbyId(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PersonalInfo> findAll() {
        return this.personalInfoRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        this.personalInfoRepository.deleteById(id);
    }

}
