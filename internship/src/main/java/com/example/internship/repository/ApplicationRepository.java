package com.example.internship.repository;

import com.example.internship.model.Application;
import com.example.internship.model.Application.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Long> {
    List<Application> findByStatus(Status status);
}
