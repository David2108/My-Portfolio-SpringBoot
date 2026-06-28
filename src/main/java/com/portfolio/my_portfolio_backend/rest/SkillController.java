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

import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.service.ISkillService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/skills")
@RequiredArgsConstructor
public class SkillController {

    private final ISkillService skillService;

    @GetMapping
    public List<Skill> findAll() {
        return skillService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Skill> findById(@PathVariable Long id) {
        Optional<Skill> info = skillService.findbyId(id);
        if (info.isPresent()) {
            return new ResponseEntity<>(info.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Skill> create(@RequestBody Skill skill) {
        Skill newSkill = this.skillService.save(skill);
        return new ResponseEntity<>(newSkill, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Skill> create(@RequestBody Skill skill, @PathVariable Long id) {
        skill.setId(id);
        Skill newSkill = this.skillService.save(skill);
        return new ResponseEntity<>(newSkill, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void deleteByiD(@PathVariable Long id) {
        this.skillService.deleteById(id);
    }

}
