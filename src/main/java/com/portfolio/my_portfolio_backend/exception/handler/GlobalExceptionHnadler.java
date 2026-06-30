package com.portfolio.my_portfolio_backend.exception.handler;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.portfolio.my_portfolio_backend.exception.ValidationException;

/*
    - Se usa para manejo de excepciones de forma global en todos las clases con @controller
*/
@ControllerAdvice
public class GlobalExceptionHnadler {
    
    /*
        - Cuando ocurre una ValizationException se ejecuta este método
     */
    @ExceptionHandler(ValidationException.class)
    public String handleValidationException(ValidationException ex, Model model){
        model.addAttribute("errors", ex.getBindingResult().getAllErrors());
        model.addAttribute("message", "Se encontraron errores de validación");
        return "error/validation";
    }
}
