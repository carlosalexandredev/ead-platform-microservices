package com.ead.course.services.impl;

import com.ead.course.services.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ead.course.repositories.ModuleRepository;

@Service
public class ModuleServiceImpl implements ModuleService {
    @Autowired
    ModuleRepository moduleRepository;
}
