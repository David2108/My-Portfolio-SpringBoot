package com.portfolio.my_portfolio_backend.service;

import com.portfolio.my_portfolio_backend.model.Project;

import java.util.List;

public interface IProjectService {
    Project save(Project project);
    List<Project> findAll();
}
