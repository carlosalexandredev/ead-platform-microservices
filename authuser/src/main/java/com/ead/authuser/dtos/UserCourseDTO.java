package com.ead.authuser.dtos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class UserCourseDTO {
    private UUID userID;
    @NotNull
    private UUID courseId;
}
