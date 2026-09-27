package com.portfolio.my_portfolio_backend.mapper;

import com.portfolio.my_portfolio_backend.dto.ExperienceDto;
import com.portfolio.my_portfolio_backend.model.Experience;

public class ExperienceMapper {

    public static ExperienceDto toDto(Experience experience) {
        if (experience == null) {
            return null;
        }
        ExperienceDto experienceDto = new ExperienceDto();
        experienceDto.setId(experience.getId());
        experienceDto.setJobTitle(experience.getJobTitle());
        experienceDto.setCompanyName(experience.getCompanyName());
        experienceDto.setStartDate(experience.getStartDate());
        experienceDto.setEndDate(experience.getEndDate());
        experienceDto.setDescription(experience.getDescription());
        experienceDto.setPersonalInfoId(experience.getPersonalInfoId());
        return experienceDto;
    }

    public static Experience toEntity(ExperienceDto experienceDto) {
        if (experienceDto == null) {
            return null;
        }
        Experience experience = new Experience();
        experience.setId(experienceDto.getId());
        experience.setJobTitle(experienceDto.getJobTitle());
        experience.setCompanyName(experienceDto.getCompanyName());
        experience.setStartDate(experienceDto.getStartDate());
        experience.setEndDate(experienceDto.getEndDate());
        experience.setDescription(experienceDto.getDescription());
        experience.setPersonalInfoId(experienceDto.getPersonalInfoId());
        return experience;
    }
}
