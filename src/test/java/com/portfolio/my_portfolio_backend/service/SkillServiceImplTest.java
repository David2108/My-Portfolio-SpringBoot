package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.exception.ValidationException;
import com.portfolio.my_portfolio_backend.model.Skill;
import com.portfolio.my_portfolio_backend.repository.ISkillRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SkillServiceImplTest {

    /*
        - Crea un mock del repositorio
        - Se usa la interface
     */
    @Mock
    private ISkillRepository skillRepository;

    /*
        - Se crea el mock y se inyecta
        - Se usa la implementación
     */
    @InjectMocks
    private SkillService skillService;

    @Mock
    private Validator validator;

    @Test
    void testFindAllReturnsListOfSkills() {
        List<Skill> mockSkills = Arrays.asList(new Skill(), new Skill());
        // Indica que cuando se llame all servicio findAll devuelva la lista mockSkills
        when(skillRepository.findAll()).thenReturn(mockSkills);

        List<Skill> skills = skillService.findAll();

        assertNotNull(skills);
        assertEquals(2, skills.size());
        verify(skillRepository, times(1)).findAll();
    }

    @Test
    void testFindByIdReturnsSkillWhenFound(){
        Long id = 1L;
        Skill mockSkill = new Skill();
        when(skillRepository.findbyId(id)).thenReturn(Optional.of(mockSkill));

        Optional<Skill> skillOptional = skillService.findbyId(id);

        assertTrue(skillOptional.isPresent());
        assertEquals(mockSkill, skillOptional.get());
        verify(skillRepository, times(1)).findbyId(id);
    }

    @Test
    void testSaveSkillThrowsExceptionWhenInvalid(){
        Skill invalidSkill = new Skill();
        /*
            - Simula un error
         */
        doAnswer(invocation -> {
            BindingResult result = invocation.getArgument(1);
            result.rejectValue("name", "NotBlank", "El nombre no puede estar vación");
            return null;
        }).when(validator).validate(any(Skill.class), any(BindingResult.class));

        assertThrows(ValidationException.class, () -> skillService.save(invalidSkill),
                "Debe lanzarse una ValidationException si el objeto no es válido.");

        verify(skillRepository, never()).save(any(Skill.class));
    }

    @Test
    void testSaveSkillSavesValidSkill(){
        Skill validSkill = new Skill(null, "Java", 90, "fab fa-java", 1L);
        when(skillRepository.save(any(Skill.class))).thenReturn(validSkill);
        doNothing().when(validator).validate(any(Skill.class), any(BindingResult.class));

        Skill savedSkill = skillService.save(validSkill);

        assertNotNull(savedSkill);
        verify(skillRepository, times(1)).save(validSkill);
    }
}
