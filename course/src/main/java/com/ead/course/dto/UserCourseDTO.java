package com.ead.course.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserCourseDTO {
    private UUID userID;
    private UUID courseId;
}
