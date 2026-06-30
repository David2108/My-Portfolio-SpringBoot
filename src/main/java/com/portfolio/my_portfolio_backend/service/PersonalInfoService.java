package com.portfolio.my_portfolio_backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.repository.IPersonalInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PersonalInfoService implements IPersonalInfoService{

    private final IPersonalInfoRepository personalInfoRepository;
    private final Validator validator;

    @Override
    @Transactional
    public PersonalInfo save(PersonalInfo personalInfo) {
        // Este objeto va almacenar los resultados de la valicación del objeto personalInfo
        BindingResult result = new BeanPropertyBindingResult(personalInfo, "personalInfo");
        // El método validate valida el objeto personalInfo y almacena los resultados en result
        validator.validate(personalInfo, result);
        if(result.hasErrors()){
            throw new ValidationException(result);
        }
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
