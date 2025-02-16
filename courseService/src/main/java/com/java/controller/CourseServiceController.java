package com.java.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.CourseRequestDTO;
import com.java.dto.CourseResponseDTO;
import com.java.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseServiceController {

    private CourseService courseService;

    @Autowired
    private CourseServiceController(CourseService courseService){
        this.courseService = courseService;
    }

    @PostMapping(path = "/create")
    public ResponseEntity<CourseResponseDTO> createCourse(@RequestBody CourseRequestDTO courseReqDTO){
    	CourseResponseDTO courseResDTO = courseService.addCourse(courseReqDTO);
        return new ResponseEntity<>(courseResDTO, HttpStatus.CREATED);
    }

    @PostMapping("/createBatch")
    public ResponseEntity<List<CourseResponseDTO>> createCourses(@RequestBody List<CourseRequestDTO> courseReqDTOs){
        List<CourseResponseDTO> courseResDTOs = courseService.addCourses(courseReqDTOs);
        return new ResponseEntity<>(courseResDTOs, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CourseResponseDTO>> getAllCourses(){
        List<CourseResponseDTO> courses =  courseService.getAllCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<CourseResponseDTO> getCourseById(@PathVariable Integer courseId){
    	CourseResponseDTO course = courseService.getCourseById(courseId);
        if(course!=null){
            return new ResponseEntity<>(course, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(course, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<CourseResponseDTO> updateCourse(@PathVariable Integer courseId, @RequestBody CourseRequestDTO updatedCourse){
    	CourseResponseDTO course = courseService.updateCourse(courseId, updatedCourse);
        return new ResponseEntity<>(course, HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<?> deleteCourseById(@PathVariable Integer courseId){
        courseService.deleteCourseById(courseId);
        return new ResponseEntity<>("", HttpStatus.NO_CONTENT);
    }

//    @GetMapping("/typeAndCount")
//    public ResponseEntity<?> getCoursesTypeAndCount(){
//        List<Object> result = new ArrayList<>();
//        result =  courseService.getCountBasedOnCourseType();
//        return new ResponseEntity<>(result, HttpStatus.OK);
//    }

}
