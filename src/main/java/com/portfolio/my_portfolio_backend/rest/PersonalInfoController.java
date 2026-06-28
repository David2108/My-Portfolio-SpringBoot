package com.portfolio.my_portfolio_backend.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;
import com.portfolio.my_portfolio_backend.service.IPersonalInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/personal-info")
@RequiredArgsConstructor
public class PersonalInfoController {

    private final IPersonalInfoService personalInfoService;

    @GetMapping
    public List<PersonalInfo> findAll(){
        return personalInfoService.findAll();
    }

    @GetMapping("/{id}")
    public PersonalInfo findById(@PathVariable Long id){
        Optional<PersonalInfo> info = personalInfoService.findbyId(id);
        if(info.isPresent()){
            return info.get();
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Información personal no disponible en el ID: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<PersonalInfo> create(@RequestBody PersonalInfo personalInfo){
        PersonalInfo newPersonalInfo = this.personalInfoService.save(personalInfo);
        return new ResponseEntity<>(newPersonalInfo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonalInfo> create(@RequestBody PersonalInfo personalInfo, @PathVariable Long id){
        personalInfo.setId(id);
        PersonalInfo newPersonalInfo = this.personalInfoService.save(personalInfo);
        return new ResponseEntity<>(newPersonalInfo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteByiD(@PathVariable Long id){
        this.personalInfoService.deleteById(id);
    }

}
