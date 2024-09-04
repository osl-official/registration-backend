package org.bot.oslregistrationbackend.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HomeController {
    @GetMapping("/")
    public void getHome(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger");
    }

    @GetMapping("/error")
    public String getError() {
        return "Something went wrong whoopsie!";
    }
}
