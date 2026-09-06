package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.repository.SkillRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// Cada que se ejecuta un test la base de datos regresa a su estado inicial
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class SkillServiceTest {

    @Autowired
    private SkillService skillService;

    @Autowired
    private SkillRepository skillRepository;

    @Test
    void testSaveValidSkill(){
        Skill validSkill = new Skill(null, "Java", 90, "fab fa-java", 1L);
        Skill savedSkill = skillService.save(validSkill);

        assertNotNull(savedSkill.getId(), "El objeto guardado debe tener un ID asignado");
        assertNotNull(skillRepository.findbyId(savedSkill.getId()).orElse(null), "El objeto guardado debe existir en la base de datos");
    }

    @Test
    void testSaveInvalidSkill(){
        Skill invalidSkill = new Skill(null, "", 90, "fab fa-java", 1L);

        assertThrows(ValidationException.class, () -> skillService.save(invalidSkill),
                "Debe lanzarse una ValidationException cuando el nombre de la skill está vacío");
    }

}