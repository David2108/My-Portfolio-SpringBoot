package com.portfolio.my_portfolio_backend.repository;

import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.portfolio.my_portfolio_backend.model.Education;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class EducationRepository implements IEducationRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Education> skillRowMapper = (res, numRow) -> {
        Education education = new Education();
        education.setId(res.getLong("id"));
        education.setDegree(res.getString("degree"));
        education.setInstitution(res.getString("institution"));
        education.setStartDate(res.getObject("start_date", LocalDate.class));
        education.setEndDate(res.getObject("end_date", LocalDate.class));
        education.setDescription(res.getString("description"));
        education.setPersonalInfoId(res.getLong("personal_info_id"));
        return education;
    };

    @Override
    public Education save(Education education) {
        if (education.getId() == null) {
            String sql = """
                    insert into educations (degree, institution, start_date, end_date, description, personal_info_id)
                    values(?, ?, ?, ?)
                    """;
            KeyHolder keyHolder = new GeneratedKeyHolder();
            this.jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, education.getDegree());
                ps.setString(2, education.getInstitution());
                ps.setObject(3, education.getStartDate());
                ps.setObject(4, education.getEndDate());
                ps.setString(5, education.getDescription());
                ps.setLong(6, education.getPersonalInfoId());
                return ps;
            }, keyHolder);
            education.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = """
                    update educations
                    set degree=?, institution=?, start_date=?, end_date=?, description=?, personal_info_id=?
                    where id=?
                    """;
            this.jdbcTemplate.update(sql,
                    education.getDegree(),
                    education.getInstitution(),
                    education.getStartDate(),
                    education.getEndDate(),
                    education.getDescription(),
                    education.getPersonalInfoId(),
                    education.getId());
        }
        return education;
    }

    @Override
    public Optional<Education> findbyId(Long id) {
        String sql = "select * from educations where id=?";
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(sql, skillRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Education> findAll() {
        String sql = "select * from educations";
        return this.jdbcTemplate.query(sql, skillRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from educations where id=?";
        this.jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Education> findByPesonalInfoId(Long personalInfoId) {
        String sql = """
            select * from educations where personal_info_id=?
        """;
        return this.jdbcTemplate.query(sql, skillRowMapper, personalInfoId);
    }

}
