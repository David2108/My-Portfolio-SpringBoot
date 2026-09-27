package com.portfolio.my_portfolio_backend.repository;

import com.portfolio.my_portfolio_backend.model.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface IUserRepository {
    Optional<User> findByUsername(String username);
}
