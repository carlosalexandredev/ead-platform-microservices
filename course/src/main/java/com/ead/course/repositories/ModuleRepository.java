package com.ead.course.repositories;

import com.ead.course.models.ModuleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface ModuleRepository extends JpaRepository<ModuleModel,UUID> {
}
