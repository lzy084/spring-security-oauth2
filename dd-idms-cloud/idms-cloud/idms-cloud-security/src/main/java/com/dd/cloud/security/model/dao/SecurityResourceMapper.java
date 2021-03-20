package com.dd.cloud.security.model.dao;

import com.dd.cloud.security.model.entity.SecurityResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SecurityResourceMapper {
    List<SecurityResource> findAllResource();
}
