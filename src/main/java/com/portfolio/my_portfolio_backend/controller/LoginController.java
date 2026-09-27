package com.portfolio.my_portfolio_backend.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout,
                                Model model,
                                Authentication authentication) {
        if(authentication != null && authentication.isAuthenticated()) {
            return "redirect:/education";
        }
        if(error != null) {
            model.addAttribute("error", "Usuario o contraseña incorrectos.");
        }
        if(logout != null) {
            model.addAttribute("message", "Has cerrado sesión correctamente.");
        }
        return "auth/form-login";
    }
}
