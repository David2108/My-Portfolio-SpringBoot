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

import com.portfolio.my_portfolio_backend.model.Education;
import com.portfolio.my_portfolio_backend.service.IEducationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/education")
@RequiredArgsConstructor
public class EducationController {

    private final IEducationService educationService;

    @GetMapping
    public List<Education> findAll(){
        return educationService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Education> findById(@PathVariable Long id){
        Optional<Education> info = educationService.findbyId(id);
        if(info.isPresent()){
            return new ResponseEntity<>(info.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Education> create(@RequestBody Education personalInfo){
        Education newPersonalInfo = this.educationService.save(personalInfo);
        return new ResponseEntity<>(newPersonalInfo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Education> create(@RequestBody Education personalInfo, @PathVariable Long id){
        personalInfo.setId(id);
        Education newPersonalInfo = this.educationService.save(personalInfo);
        return new ResponseEntity<>(newPersonalInfo, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteByiD(@PathVariable Long id){
        this.educationService.deleteById(id);
    }

}
