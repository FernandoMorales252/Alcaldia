package com.SysGroup.Alcaldia.Controladores;

import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/")
public class homeController {
    @RequestMapping
    public String index() {
        return "home/Index";
    }

    @GetMapping("/login")
    public String mostrarLogin()  {
        return "home/formLogin";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request)  {
        SecurityContextLogoutHandler logoutHandler = new SecurityContextLogoutHandler();
        logoutHandler.logout(request, null, null);
        return "redirect:/";
    }

     @GetMapping("/access-denied")
    public String accessDenied() {
        return "access-denied"; // nombre del template Thymeleaf
    }

}
