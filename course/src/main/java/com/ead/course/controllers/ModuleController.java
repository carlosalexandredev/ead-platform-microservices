package com.ead.course.controllers;

import com.ead.course.dto.ModuleDTO;
import com.ead.course.models.CourseModel;
import com.ead.course.models.ModuleModel;
import com.ead.course.services.CourseService;
import com.ead.course.services.ModuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class ModuleController {

    @Autowired
    ModuleService moduleService;

    @Autowired
    CourseService courseService;

    @GetMapping("/courses/{courseId}/modules")
    public ResponseEntity<Object> findAllMouduleByCourse(
            @PathVariable(value = "courseId") UUID courseId){
        List<ModuleModel> ModuleModelList = moduleService.findAllByCourse(courseId);
        if(ModuleModelList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this course.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(ModuleModelList);
    }

    @GetMapping("/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> findByIdCourseModule(
            @PathVariable(value = "courseId") UUID courseId,
            @PathVariable(value = "moduleId") UUID moduleId){
        Optional<ModuleModel> moduleModel = moduleService.findModuleIntoCourse(courseId, moduleId);
        if(!moduleModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this course.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(moduleModel);
    }

    @PostMapping("/courses/{courseId}/modules")
    public ResponseEntity<Object> saveModule(
            @RequestBody @Validated ModuleDTO moduleDTO,
            @PathVariable(value = "courseId") UUID courseId){
        Optional<CourseModel> courseModel = courseService.findById(courseId);
        if(!courseModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found.");
        }
        var moduleModel = new ModuleModel();
        BeanUtils.copyProperties(moduleDTO, moduleModel);
        moduleModel.setCourse(courseModel.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(moduleService.save(moduleModel));
    }

    @PutMapping("/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> updateModule(
            @PathVariable(value = "courseId") UUID courseId,
            @PathVariable(value = "moduleId") UUID moduleId,
            @RequestBody @Validated ModuleDTO moduleDTO) {
        Optional<ModuleModel> moduleModelOptional = moduleService.findModuleIntoCourse(courseId, moduleId);
        if(!moduleModelOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course Not Found.");
        }
        var moduleModel = moduleModelOptional.get();
        moduleModel.setTitle(moduleDTO.getTitle());
        moduleModel.setDescription(moduleDTO.getDescription());
        return ResponseEntity.status(HttpStatus.OK).body(moduleService.save(moduleModel));
    }

    @DeleteMapping("/courses/{courseId}/modules/{moduleId}")
    public ResponseEntity<Object> deleteModule(
            @PathVariable(value = "courseId") UUID courseId,
            @PathVariable(value = "moduleId") UUID moduleId){
        Optional<ModuleModel> moduleModel = moduleService.findModuleIntoCourse(courseId, moduleId);
        if(!moduleModel.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Module not found for this course.");
        }
        moduleService.delete(moduleModel.get());
        return ResponseEntity.status(HttpStatus.OK).body("Module deleted sucessfully.");
    }
}
