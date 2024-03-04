package com.java.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Course {

    private Integer courseId;
    private String courseName;
    private String description;
    private String traineeName;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date startDate;
    private Double fee;
    private String duration;
    private String courseType;
    private boolean isCertificateAvailable;

}
