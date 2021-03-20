package com.dd.admin.service;

import com.dd.admin.dao.TenantInfoMapper;
import com.dd.model.user.TenantInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
* Author liuzhouyang
* Date  2020-10-11
******************************************************************/
@Service
public class TenantInfoService {
    @Autowired
    private TenantInfoMapper tenantInfoMapper;

    public TenantInfo get(Long id){
        return tenantInfoMapper.get(id);
    }


    public List<TenantInfo> findAllList() {
        return tenantInfoMapper.findAllList();
    }

    public int insert(TenantInfo tenantInfo) {
        return tenantInfoMapper.insert(tenantInfo);
    }

    public int update(TenantInfo tenantInfo) {
        return tenantInfoMapper.update(tenantInfo);
    }

    public int delete(Long id) {
        return tenantInfoMapper.delete(id);
    }

}
