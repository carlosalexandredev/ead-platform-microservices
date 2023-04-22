package com.ead.authuser.service.impl;

import com.ead.authuser.service.UtilsService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UtilsServiceImpl implements UtilsService {

    private String RESQUEST_URI = "http://localhost:8082";

    public String createUrl(UUID userId, Pageable pageable){
     return RESQUEST_URI +  "/courses?userId=" + userId + "&page=" + pageable.getPageNumber() + "&size="
        + pageable.getPageSize() + "&sort=" + pageable.getSort().toString().replaceAll(": ", ",");
    }

}
