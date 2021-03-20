package com.dd.user.service;

import com.dd.user.dao.ResourceDao;
import com.dd.user.entity.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceService {
    @Autowired
    ResourceDao resourceDao;
    public List<Resource> findAllResource(){
        return resourceDao.findAllResource();
    }
}
