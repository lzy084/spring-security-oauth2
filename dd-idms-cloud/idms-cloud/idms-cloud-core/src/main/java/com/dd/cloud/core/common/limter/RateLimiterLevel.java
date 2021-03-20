package com.dd.cloud.core.common.limter;

import com.dd.cloud.core.common.entity.RateLimiter;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author liuzy
 * @title: SysRateLimiter
 * @projectName idms-cloud
 * @description: TODO 限流等级配置
 * @date 2020/5/4 19:40
 */
@Data
public class RateLimiterLevel implements Serializable {
    private List<RateLimiter> levels;
}
