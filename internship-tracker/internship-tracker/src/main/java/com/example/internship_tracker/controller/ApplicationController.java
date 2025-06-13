package com.example.internship_tracker.controller;

import com.example.internship_tracker.model.Application;
import com.example.internship_tracker.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationRepository repository;

    // ✅ List all applications
    @GetMapping
    public String getAllApplications(Model model) {
        model.addAttribute("applications", repository.findAll());
        return "application-list"; // returns application-list.html
    }

    // ✅ Show form to add a new application
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("application", new Application());
        return "application-form"; // returns application-form.html
    }

    // ✅ Save new application
    @PostMapping
    public String createApplication(@ModelAttribute Application application) {
        repository.save(application);
        return "redirect:/applications";
    }

    // ✅ Show edit form
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Application> app = repository.findById(id);
        if (app.isPresent()) {
            model.addAttribute("application", app.get());
            return "application-form"; // reuse form for editing
        }
        return "redirect:/applications";
    }

    // ✅ Update application
    @PostMapping("/update/{id}")
    public String updateApplication(@PathVariable Long id, @ModelAttribute Application updatedApp) {
        repository.findById(id).ifPresent(app -> {
            app.setCompanyName(updatedApp.getCompanyName());
            app.setPosition(updatedApp.getPosition());
            app.setStatus(updatedApp.getStatus());
            app.setAppliedDate(updatedApp.getAppliedDate());
            repository.save(app);
        });
        return "redirect:/applications";
    }

    // ✅ Delete application
    @GetMapping("/delete/{id}")
    public String deleteApplication(@PathVariable Long id) {
        repository.deleteById(id);
        return "redirect:/applications";
    }
}
