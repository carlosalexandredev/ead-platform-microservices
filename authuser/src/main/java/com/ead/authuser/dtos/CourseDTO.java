package com.ead.authuser.dtos;

import com.ead.authuser.enuns.CourseLevel;
import com.ead.authuser.enuns.CourseStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CourseDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String description;
    private String imageUrl;
    @NotNull
    private CourseStatus courseStatus;
    @NotNull
    private UUID userInstructor;
    private CourseLevel courseLevel;
}
