package com.portfolio.my_portfolio_backend.repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.portfolio.my_portfolio_backend.model.PersonalInfo;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class PersonalInfoRepository implements IPersonalInfoRepository {

    private final JdbcTemplate jdbcTemplate;
    /*
     * RowMapper
     * - Lee una fila de la base de datos y la transforma en un objeto java
     */
    private final RowMapper<PersonalInfo> personalInfoRowMapper = (res, numRow) -> {
        PersonalInfo info = new PersonalInfo();
        info.setId(res.getLong("id"));
        info.setFirstName(res.getString("first_name"));
        info.setLastName(res.getString("last_name"));
        info.setTitle(res.getString("title"));
        info.setProfileDescription(res.getString("profile_description"));
        info.setProfileImageUrl(res.getString("profile_image_url"));
        info.setYearsOfExperience(res.getObject("years_of_experience", Integer.class)); // Usar getObject para nulos
        info.setEmail(res.getString("email"));
        info.setPhone(res.getString("phone"));
        info.setLinkedinUrl(res.getString("linkedin_url"));
        info.setGithubUrl(res.getString("github_url"));
        return info;
    };

    @Override
    public PersonalInfo save(PersonalInfo personalInfo) {
        if (personalInfo.getId() == null) {
            String sql = """
                    insert into personal_info (first_name, last_name, title, profile_description, profile_image_url, years_of_experience, email, phone, linkedin_url, github_url)
                    values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                                        """;
            KeyHolder keyHolder = new GeneratedKeyHolder();
            this.jdbcTemplate.update(connection -> {
                // Prepara la ejecución de sql y debe retornar el campo id del nuevo registro
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, personalInfo.getFirstName());
                ps.setString(2, personalInfo.getLastName());
                ps.setString(3, personalInfo.getTitle());
                ps.setString(4, personalInfo.getProfileDescription());
                ps.setString(5, personalInfo.getProfileImageUrl());

                if (personalInfo.getYearsOfExperience() != null) {
                    ps.setInt(6, personalInfo.getYearsOfExperience());
                } else {
                    ps.setNull(6, java.sql.Types.INTEGER);
                }

                ps.setString(7, personalInfo.getEmail());
                ps.setString(8, personalInfo.getPhone());
                ps.setString(9, personalInfo.getLinkedinUrl());
                ps.setString(10, personalInfo.getGithubUrl());
                return ps;
            }, keyHolder);
            personalInfo.setId(Objects.requireNonNull(keyHolder.getKey(), "No se obtuvo el id generado").longValue());
        } else {

            String sql = """
                    update personal_info
                    set first_name=?, last_name=?, title=?, profile_description=?, profile_image_url=?, years_of_experience=?, email=?, phone=?, linkedin_url=?, github_url=?
                    where id=?
                    """;

            this.jdbcTemplate.update(sql,
                    personalInfo.getFirstName(),
                    personalInfo.getLastName(),
                    personalInfo.getTitle(),
                    personalInfo.getProfileDescription(),
                    personalInfo.getProfileImageUrl(),
                    personalInfo.getYearsOfExperience(),
                    personalInfo.getEmail(),
                    personalInfo.getPhone(),
                    personalInfo.getLinkedinUrl(),
                    personalInfo.getGithubUrl(),
                    personalInfo.getId());
        }

        return personalInfo;
    }

    // @Override
    // public Optional<PersonalInfo> findbyId(Long id) {
    // String sql = "select * from personal_info where id=? ";
    // this.jdbcTemplate.query(sql, id, personalInfoRowMapper);
    // return Optional.of(null));
    // }

    @Override
    public Optional<PersonalInfo> findbyId(Long id) {
        String sql = "select * from personal_info where id=? ";
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(sql, personalInfoRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<PersonalInfo> findAll() {
        String sql = """
                select * from personal_info
                """;
        return this.jdbcTemplate.query(sql, personalInfoRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from personal_info where id=?";
        this.jdbcTemplate.update(sql, id);
    }

}
