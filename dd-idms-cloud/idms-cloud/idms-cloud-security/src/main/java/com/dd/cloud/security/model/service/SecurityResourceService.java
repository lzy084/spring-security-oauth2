package com.dd.cloud.security.model.service;


import com.dd.cloud.security.model.dao.SecurityResourceMapper;
import com.dd.cloud.security.model.entity.SecurityResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SecurityResourceService {
    @Autowired
    SecurityResourceMapper resourceMapper;
    public List<SecurityResource> findAllResource(){
        return resourceMapper.findAllResource();
    }
}
