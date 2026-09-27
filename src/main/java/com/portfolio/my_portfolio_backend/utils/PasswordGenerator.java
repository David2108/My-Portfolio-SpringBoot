package com.portfolio.my_portfolio_backend.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "1234";
        String encodePassword = passwordEncoder.encode(password);
        System.out.println(encodePassword);
    }
}
