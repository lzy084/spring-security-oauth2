package com.dd.user.dao;

import com.dd.base.model.security.SecurityResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourceDao {
    List<SecurityResource> findAllResource();
}
