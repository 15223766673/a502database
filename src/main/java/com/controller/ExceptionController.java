package com.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(RuntimeException.class)
    public String testHandler(Throwable ex, Model model)
    {
        System.out.println("发生错误");
        model.addAttribute("ex",ex);
        return "error";
    }
}
