package com.java.service;

import com.java.dto.Course;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService implements Serializable{

    private List<Course> coursesDb = new ArrayList<>();
    public Course addCourse(Course course){
        course.setCourseId(new Random().nextInt(32500));
        coursesDb.add(course);
        return course;
    }

    public List<Course> addCourses(List<Course> courses){
        for(Course course : courses){
            course.setCourseId(new Random().nextInt(32500));
            coursesDb.add(course);
        }
        return courses;
    }

    public List<Course> getAllCourses(){
        return coursesDb;
    }
    public Course getCourseById(Integer courseId){
        return coursesDb.stream()
                .filter(course -> course.getCourseId().equals(courseId))
                .findFirst().orElse(null);
    }

    public void deleteCourseById(Integer courseId){
        Course courseFromDb = getCourseById(courseId);
        coursesDb.remove(courseFromDb);
    }
    public Course updateCourse(Integer courseId, Course updatedCourse){
        Course courseFromDb = getCourseById(courseId);
        coursesDb.set(coursesDb.indexOf(courseFromDb),updatedCourse);
        return updatedCourse;
    }

    public List<Object> getCountBasedOnCourseType(){

        @Component
        class courseTypeCount implements Serializable {
            String courseType;
            long count;

            public courseTypeCount(String getCourseType, Long count){
                this.courseType = getCourseType;
                this.count = count;
            }
        }

        Map<String, Long> resultMap = new HashMap<>();
        resultMap = coursesDb.stream()
                .collect(Collectors.groupingBy(Course::getCourseType, Collectors.counting()));
        List<Object> result = new ArrayList<>();
        for(String resultCourse : resultMap.keySet()){
            result.add(new courseTypeCount(resultCourse, resultMap.get(resultCourse)));
        }
        return result;
    }
}
