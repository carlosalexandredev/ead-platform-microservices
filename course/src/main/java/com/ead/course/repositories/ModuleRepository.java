package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel,UUID> {
    @Query(value = "SELECT * FROM TB_MODULES WHERE COURSE_COURSE_ID = :courseId", nativeQuery = true)
    List<ModuleModel> findAllModulesIntoCourse(@Param("courseId") UUID courseId);

    @Query(value = "SELECT * FROM TB_MODULES WHERE MODULE_ID = :moduleId AND COURSE_COURSE_ID = :courseId", nativeQuery = true)
    Optional<ModuleModel> findModuleIntoCourse(@Param("courseId") UUID courseId, @Param("moduleId") UUID moduleId);
}
