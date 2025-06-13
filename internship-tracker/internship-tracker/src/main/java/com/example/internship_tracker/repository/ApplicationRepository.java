package com.example.internship_tracker.repository;
import com.example.internship_tracker.model.Application;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ApplicationRepository extends JpaRepository<Application, Long>{
}
