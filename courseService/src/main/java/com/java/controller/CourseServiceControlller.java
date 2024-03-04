package com.java.controller;

import com.java.dto.Course;
import com.java.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/course")
public class CourseServiceControlller {

    private CourseService courseService;

    @Autowired
    private CourseServiceControlller(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping
    public ResponseEntity<Course> createCourse(@RequestBody Course newCourse){
        Course course = courseService.addCourse(newCourse);
        return new ResponseEntity<>(course, HttpStatus.CREATED);
    }

    @PostMapping("create")
    public ResponseEntity<List<Course>> createCourses(@RequestBody List<Course> newCourses){
        List<Course> courses = courseService.addCourses(newCourses);
        return new ResponseEntity<>(courses, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses(){
        List<Course> courses =  courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable Integer courseId){
        Course course = courseService.getCourseById(courseId);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Course> updateCourse(@PathVariable Integer courseId, @RequestBody Course updatedCourse){
        Course course = courseService.updateCourse(courseId, updatedCourse);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Integer courseId){
        courseService.deleteCourseById(courseId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

    @GetMapping("/typeAndCount")
    public ResponseEntity<?> getCoursesTypeAndCount(){
        List<Object> result = new ArrayList<>();
        result =  courseService.getCountBasedOnCourseType();
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
