package com.example.internship.controller;

import com.example.internship.model.Application;
import com.example.internship.model.Application.Status;
import com.example.internship.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/applications")
public class ApplicationController {

    @Autowired
    private ApplicationRepository repository;

    @GetMapping
    public List<Application> getAllApplications() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Application getApplicationById(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @GetMapping("/status/approved")
    public List<Application> getApprovedApplications() {
        return repository.findByStatus(Status.APPROVED);
    }

    @PostMapping
    public Application createApplication(@RequestBody Application application) {
        return repository.save(application);
    }

    @PutMapping("/{id}")
    public Application updateApplication(@PathVariable Long id, @RequestBody Application updatedApp) {
        Optional<Application> existing = repository.findById(id);
        if (existing.isPresent()) {
            Application app = existing.get();
            app.setStudentName(updatedApp.getStudentName());
            app.setCompany(updatedApp.getCompany());
            app.setRole(updatedApp.getRole());
            app.setStatus(updatedApp.getStatus());
            return repository.save(app);
        } else {
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public void deleteApplication(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
