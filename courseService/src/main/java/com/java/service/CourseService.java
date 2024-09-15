package com.java.service;

import com.java.dto.CourseRequestDTO;
import com.java.dto.CourseResponseDTO;
import com.java.model.CourseEntity;
import com.java.repository.CourseRepository;
import com.java.util.CourseConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService{
	
	private CourseRepository courseRepository;

	@Autowired
	public CourseService (CourseRepository courseRepository) {
		this.courseRepository = courseRepository;
	}

    private List<CourseResponseDTO> coursesDb = new ArrayList<>();
    
    public CourseResponseDTO addCourse(CourseRequestDTO courseReqDTO){
    	//convert from Request DTO to Entity
    	CourseEntity courseEntity =  CourseConverter.convertReqToEntity(courseReqDTO);
    	
    	//Save the Course Object
        CourseEntity savedCourseEntity = courseRepository.save(courseEntity);
        
        //convert from Entity to Response DTO
        CourseResponseDTO courseResDTO = CourseConverter.convertEntityToRes(savedCourseEntity);
        courseResDTO.setCourseUniqueCode(UUID.randomUUID().toString());
        
        return courseResDTO;
    }

	public List<CourseResponseDTO> addCourses(List<CourseRequestDTO> courseReqDTOs){
		List<CourseResponseDTO> courseResponseDTOs = new ArrayList<>();
		courseReqDTOs.stream().forEach(courseReqDTO -> {
			//convert from Request DTO to Entity
			CourseEntity courseEntity =  CourseConverter.convertReqToEntity(courseReqDTO);

			//Save the Course Object
			CourseEntity savedCourseEntity = courseRepository.save(courseEntity);

			//convert from Entity to Response DTO
			CourseResponseDTO courseResDTO = CourseConverter.convertEntityToRes(savedCourseEntity);
			courseResDTO.setCourseUniqueCode(UUID.randomUUID().toString());
			courseResponseDTOs.add(courseResDTO);
		});
		return courseResponseDTOs;
	}

    public List<CourseResponseDTO> getAllCourses(){
    	List<CourseEntity> courses = courseRepository.findAll();
        return courses.stream().map(CourseConverter::convertEntityToRes).collect(Collectors.toList());
    }
    public CourseResponseDTO getCourseById(Integer courseId){
    	Optional<CourseEntity> courseEntity = courseRepository.findById(courseId);
    	CourseResponseDTO courseResDTO = null;
    	if(courseEntity.isPresent()) {
    		courseResDTO = CourseConverter.convertEntityToRes(courseEntity.get());
    	}
        return courseResDTO;
    }

    public void deleteCourseById(Integer courseId){
        courseRepository.deleteById(courseId);
    }
    public CourseResponseDTO updateCourse(Integer courseId, CourseRequestDTO updatedCourse){
    	Optional<CourseEntity> courseFromDb = courseRepository.findById(courseId);
    	CourseEntity courseEntity = null;
    	if(courseFromDb.isPresent()) {
    		courseEntity = courseFromDb.get();
    	}
    	courseEntity.setCourseName(updatedCourse.getCourseName());
    	courseEntity.setStartDate(updatedCourse.getStartDate());
//
		courseEntity.setCertificateAvailable(updatedCourse.isCertificateAvailable());
    	CourseEntity savedCourseEntity = courseRepository.save(courseEntity);
    	
        return CourseConverter.convertEntityToRes(savedCourseEntity);
    }

//    public List<Object> getCountBasedOnCourseType(){
//        @Component
//        class courseTypeCount implements Serializable {
//            String courseType;
//            long count;
//
//            public courseTypeCount(String getCourseType, Long count){
//                this.courseType = getCourseType;
//                this.count = count;
//            }
//        }
//
//        Map<String, Long> resultMap = new HashMap<>();
//        resultMap = coursesDb.stream()
//                .collect(Collectors.groupingBy(Course::getCourseType, Collectors.counting()));
//        List<Object> result = new ArrayList<>();
//        for(String resultCourse : resultMap.keySet()){
//            result.add(new courseTypeCount(resultCourse, resultMap.get(resultCourse)));
//        }
//        return result;
//    }
}
