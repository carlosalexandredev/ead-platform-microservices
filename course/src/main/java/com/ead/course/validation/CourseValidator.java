package com.ead.course.validation;

import com.ead.course.dto.CourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.UUID;


@Component
public class CourseValidator implements Validator {

    @Autowired
    @Qualifier("defaultValidator")
    Validator validator;

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }

    @Override
    public void validate(Object o, Errors errors) {
        CourseDTO courseDTO = (CourseDTO) o;
        validator.validate(courseDTO, errors);
        if(!errors.hasErrors()) {
            validateUserInstructor(courseDTO.getUserInstructor(), errors);
        }
    }

    private void validateUserInstructor(UUID userInstructor, Errors errors){
//        ResponseEntity<UserDTO> responseUserInstructor;
//        try {
//            responseUserInstructor = authUserClient.getOneUserById(userInstructor);
//            if(responseUserInstructor.getBody().getUserType().equals(UserType.STUDENT)){
//                errors.rejectValue("userInstructor","UserInstructorError","User must be INSTRUCTOR or ADMIN.");
//            }
//        } catch (HttpStatusCodeException e) {
//            if(e.getStatusCode().equals(HttpStatus.NOT_FOUND)){
//                errors.rejectValue("userInstructor","UserInstructorError","Instructor not found.");
//            }
//        }
    }
}
