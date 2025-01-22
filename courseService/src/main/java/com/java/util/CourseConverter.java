package com.java.util;

import com.java.dto.CourseRequestDTO;
import com.java.dto.CourseResponseDTO;
import com.java.model.CourseEntity;

public class CourseConverter {
	
	public static CourseEntity convertReqToEntity(CourseRequestDTO courseReq) {
		CourseEntity courseEntity = new CourseEntity();
		courseEntity.setCourseId(courseReq.getCourseId());
		courseEntity.setCourseName(courseReq.getCourseName());
		courseEntity.setCourseType(courseReq.getCourseType());
		courseEntity.setDescription(courseReq.getDescription());
		courseEntity.setDuration(courseReq.getDuration());
		courseEntity.setFee(courseReq.getFee());
		courseEntity.setStartDate(courseReq.getStartDate());
		courseEntity.setTraineeName(courseReq.getTraineeName());
		courseEntity.setCertificateAvailable(courseReq.getIsCertificateAvailable());
		return courseEntity;
	}
	
	public static CourseResponseDTO convertEntityToRes(CourseEntity courseEntity) {
		CourseResponseDTO courseRes = new CourseResponseDTO();
		courseRes.setCourseId(courseEntity.getCourseId());
		courseRes.setCourseName(courseEntity.getCourseName());
		courseRes.setCourseType(courseEntity.getCourseType());
		courseRes.setDescription(courseEntity.getDescription());
		courseRes.setDuration(courseEntity.getDuration());
		courseRes.setFee(courseEntity.getFee());
		courseRes.setStartDate(courseEntity.getStartDate());
		courseRes.setTraineeName(courseEntity.getTraineeName());
		courseRes.setIsCertificateAvailable(courseEntity.isCertificateAvailable());
		return courseRes;
	}

}
