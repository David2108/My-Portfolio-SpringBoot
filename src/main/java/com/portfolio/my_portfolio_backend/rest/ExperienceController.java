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

import com.portfolio.my_portfolio_backend.model.Experience;
import com.portfolio.my_portfolio_backend.service.IExperienceService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/experience")
@RequiredArgsConstructor
public class ExperienceController {

    private final IExperienceService experienceService;

    @GetMapping
    public List<Experience> findAll(){
        return experienceService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Experience> findById(@PathVariable Long id){
        Optional<Experience> info = experienceService.findbyId(id);
        if(info.isPresent()){
            return new ResponseEntity<>(info.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Experience> create(@RequestBody Experience experience){
        Experience newExperience = this.experienceService.save(experience);
        return new ResponseEntity<>(newExperience, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Experience> create(@RequestBody Experience experience, @PathVariable Long id){
        experience.setId(id);
        Experience newExperience = this.experienceService.save(experience);
        return new ResponseEntity<>(newExperience, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteByiD(@PathVariable Long id){
        this.experienceService.deleteById(id);
    }

}
