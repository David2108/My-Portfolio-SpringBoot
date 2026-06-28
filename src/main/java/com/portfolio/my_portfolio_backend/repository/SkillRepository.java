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

import com.portfolio.my_portfolio_backend.model.Skill;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class SkillRepository implements ISkillRepository {

    private final JdbcTemplate jdbcTemplate;
    private final RowMapper<Skill> skillRowMapper = (res, numRow) -> {
        Skill skill = new Skill();
        skill.setId(res.getLong("id"));
        skill.setName(res.getString("name"));
        skill.setLevelPercentage(res.getObject("level_percentage", Integer.class));
        skill.setIconClass(res.getString("icon_class"));
        skill.setPersonalInfoId(res.getLong("personal_info_id"));
        return skill;
    };

    @Override
    public Skill save(Skill skill) {
        if (skill.getId() == null) {
            String sql = """
                    insert into skills (name, level_percentage, icon_class, personal_info_id)
                    values(?, ?, ?, ?)
                    """;
            KeyHolder keyHolder = new GeneratedKeyHolder();
            this.jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, skill.getName());
                ps.setInt(2, skill.getLevelPercentage());
                ps.setString(3, skill.getIconClass());
                ps.setLong(4, skill.getPersonalInfoId());
                return ps;
            }, keyHolder);
            skill.setId(Objects.requireNonNull(keyHolder.getKey()).longValue());
        } else {
            String sql = """
                    update skills
                    set name=?, level_percentage=?, icon_class=?, personal_info_id=?
                    where id=?
                    """;
            this.jdbcTemplate.update(sql,
                    skill.getName(),
                    skill.getLevelPercentage(),
                    skill.getIconClass(),
                    skill.getPersonalInfoId(),
                    skill.getId());
        }
        return skill;
    }

    @Override
    public Optional<Skill> findbyId(Long id) {
        String sql = "select * from skills where id=?";
        try {
            return Optional.ofNullable(this.jdbcTemplate.queryForObject(sql, skillRowMapper, id));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Skill> findAll() {
        String sql = "select * from skills";
        return this.jdbcTemplate.query(sql, skillRowMapper);
    }

    @Override
    public void deleteById(Long id) {
        String sql = "delete from skills where id=?";
        this.jdbcTemplate.update(sql, id);
    }

    @Override
    public List<Skill> findByPesonalInfoId(Long personalInfoId) {
        String sql = """
            select * from skill where personal_info_id=?
        """;
        return this.jdbcTemplate.query(sql, skillRowMapper, personalInfoId);
    }

}
