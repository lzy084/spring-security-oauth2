package com.dd.order.service;

import com.dd.cloud.core.common.json.JsonResult;
import com.dd.cloud.core.common.redis.RedisLock;
import com.dd.order.dao.GoodsInfoMapper;
import com.dd.order.entity.GoodsInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/*****************************************************************
 * Author liuzhouyang
 * Date  2020-09-21
 ******************************************************************/
@Service
@Slf4j
public class GoodsInfoService {
    @Autowired
    private GoodsInfoMapper goodsInfoMapper;
    @Autowired
    StringRedisTemplate stringRedisTemplate;

    public GoodsInfo get(String id) {
        return goodsInfoMapper.get(Integer.parseInt(id));
    }
    public List<GoodsInfo> findAllList() {
        return goodsInfoMapper.findAllList();
    }

    public int insert(GoodsInfo goodsInfo) {
        return goodsInfoMapper.insert(goodsInfo);
    }

    public int update(GoodsInfo goodsInfo) {
        return goodsInfoMapper.update(goodsInfo);
    }

    public JsonResult reduceStore(int id, int reduceNum) {
        RedisLock redisLock = new RedisLock(stringRedisTemplate, "reduceStore", 5);
        boolean result;
        try {
            if (redisLock.getLock()) {
                log.debug("###################");
                log.debug("reduceNum="+reduceNum);
                log.debug("我进入了锁");
                int current=queryStore(id);
                if(current>=reduceNum){
                    result= goodsInfoMapper.reduceStore(id,current-reduceNum)>=0;
                    redisLock.unLock();
                    return JsonResult.create().success(result).message("操作成功");
                }
                else{
                    log.debug("###################");
                    log.debug("库存不够了");
                    redisLock.unLock();
                    return JsonResult.create().success(true).message("库存不够了");
                }

            }
            else{
                log.debug("###################");
                log.debug("正在等待获取锁.......................");
                return reduceStore(id,reduceNum);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return JsonResult.create().success(false).message(e.getLocalizedMessage());
        }
    }


    /**
     * 根据id获取当前商品的库存
     *
     * @param id
     * @return
     */
    public int queryStore(int id) {
        log.debug("#########################################################################");
        log.debug("开始查询库存");
        int storeNum = goodsInfoMapper.queryStoreById(id);
        return storeNum;
    }

    public int delete(String id) {
        return goodsInfoMapper.delete(Integer.parseInt(id));
    }

}
