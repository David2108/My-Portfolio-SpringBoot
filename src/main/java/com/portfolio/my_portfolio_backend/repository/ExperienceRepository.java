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

import com.portfolio.my_portfolio_backend.model.Experience;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ExperienceRepository implements IExperienceRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Experience> skillRowMapper = (res, numRow) -> {
        Experience experience = new Experience();
        experience.setId(res.getLong("id"));
        experience.setJobTitle(res.getString("name"));
        experience.setCompanyName(res.getString("level_percentage"));
        experience.setStartDate(res.getObject("icon_class", LocalDate.class));
        experience.setEndDate(res.getObject("icon_class", LocalDate.class));
        experience.setDescription(res.getString("icon_class"));
        experience.setPersonalInfoId(res.getLong("personal_info_id"));
        return experience;
    };

    @Override
    public Experience save(Experience experience) {
        if (experience.getId() == null) {
            String sql = """
                    insert into experiences (job_title, company_code, start_date, end_date, description, personal_info_id)
                    values(?, ?, ?, ?)
                    """;
            KeyHolder keyHolder = new GeneratedKeyHolder();
            this.jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, experience.getJobTitle());
                ps.setString(2, experience.getCompanyName());
                ps.setObject(3, experience.getStartDate());
                ps.setObject(4, experience.getEndDate());
                ps.setString(5, experience.getDescription());
                ps.setLong(6, experience.getPersonalInfoId());
                return ps;
            }, keyHolder);
            experience.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = """
                    update experiences
                    set job_title=?, company_code=?, start_date=?, end_date=?, description=?, personal_info_id=?
                    where id=?
                    """;
            this.jdbcTemplate.update(sql,
                experience.getJobTitle(),
                experience.getCompanyName(),
                experience.getStartDate(),
                experience.getEndDate(),
                experience.getDescription(),
                experience.getPersonalInfoId(),
                experience.getId());
        }
        return experience;
    }

    @Override
    public Optional<Experience> findbyId(Long id) {
        String sql = "select * from experiences where id=?";
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(sql, Objects.requireNonNull(skillRowMapper), id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Experience> findAll() {
        String sql = "select * from experiences";
        return this.jdbcTemplate.query(sql, Objects.requireNonNull(skillRowMapper));
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from experiences where id=?";
        this.jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Experience> findByPesonalInfoId(Long personalInfoId) {
        String sql = """
            select * from experiences where personal_info_id=?
        """;
        return this.jdbcTemplate.query(sql, Objects.requireNonNull(skillRowMapper), personalInfoId);
    }

}
