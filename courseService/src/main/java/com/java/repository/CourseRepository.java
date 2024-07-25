package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.model.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer>{

}
