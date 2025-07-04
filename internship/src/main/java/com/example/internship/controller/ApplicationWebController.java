package com.example.internship.controller;

import com.example.internship.model.Application;
import com.example.internship.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ApplicationWebController {

    @Autowired
    private ApplicationRepository repository;

    @GetMapping("/")
    public String index(Model model) {
        List<Application> applications = repository.findAll();
        model.addAttribute("applications", applications);
        model.addAttribute("newApplication", new Application());
        return "index";
    }

    @PostMapping("/add")
    public String addApplication(@ModelAttribute Application application) {
        repository.save(application);
        return "redirect:/";
    }

    @PostMapping("/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/";
    }
}
