package com.ead.course.controllers;

import com.ead.course.dto.CourseDTO;
import com.ead.course.models.CourseModel;
import com.ead.course.services.CourseService;
import com.ead.course.specifications.SpecificationsTemplate;
import com.ead.course.validation.CourserValidator;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/courses")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CourseController {
    @Autowired
    CourseService courseService;

    @Autowired
    CourserValidator courserValidator;

    @GetMapping
    public ResponseEntity<Page<CourseModel>> findAllCourses(
            SpecificationsTemplate.CourseSpec spec,
            @PageableDefault(page = 0, size = 10, sort = "courseId", direction = Sort.Direction.ASC) Pageable pageable,
            @RequestParam(required = false) UUID userId){
        Page<CourseModel> courseModels = null;
        if(userId != null){
            courseModels = courseService.findAll(SpecificationsTemplate.CourseUserId(userId).and(spec), pageable);
        } else {
            courseModels = courseService.findAll(spec, pageable);
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseModels);
    }

    @GetMapping("/{courseId}")
    public ResponseEntity<Object> findByIdCourse(@PathVariable(value = "courseId") UUID courseId){
        Optional<CourseModel> courseModel = courseService.findById(courseId);
        if(courseModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(courseModel);
    }

    @PostMapping
    public ResponseEntity<Object> saveCourse(@RequestBody CourseDTO courseDTO, Errors erros){
        courserValidator.validate(courseDTO, erros);
        if(erros.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erros.getAllErrors());
        }
        var courseModel = new CourseModel();
        BeanUtils.copyProperties(courseDTO, courseModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.save(courseModel));
    }

    @PutMapping("/{courseId}")
    public ResponseEntity<Object> updateCourse(
            @PathVariable(value = "courseId") UUID courseId,
            @RequestBody @Validated CourseDTO courseDTO){
        Optional<CourseModel> courseModelOptional = courseService.findById(courseId);
        if(!courseModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found.");
        }
        var courseModel = courseModelOptional.get();
        courseModel.setName(courseDTO.getName());
        courseModel.setDescription(courseDTO.getDescription());
        courseModel.setImageUrl(courseDTO.getImageUrl());
        courseModel.setCourseStatus(courseDTO.getCourseStatus());
        courseModel.setCourseLevel(courseDTO.getCourseLevel());
        return ResponseEntity.status(HttpStatus.OK).body(courseService.save(courseModel));
    }

    @DeleteMapping("/{courseId}")
    public ResponseEntity<Object> deleteCourse(@PathVariable(value = "courseId") UUID courseId){
        Optional<CourseModel> courseModel = courseService.findById(courseId);
        if(!courseModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found.");
        }
        courseService.delete(courseModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Course deleted sucessfully.");
    }
}
