package com.ead.authuser.service.impl;

import com.ead.authuser.repositories.UserCourseRepository;
import com.ead.authuser.service.UserCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCouseServiceImpl implements UserCourseService {

    @Autowired
    UserCourseRepository userCourseRepository;

}