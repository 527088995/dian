package com.stylefeng.guns.core.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;

/**
 * redis分布式锁的实现
 * https://blog.csdn.net/yeyazhishang/article/details/80760955
 */
public class RedisLockUtil {
    private static org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger("RedisLockUtil");


    @Autowired
    private StringRedisTemplate redisTemplate;
    /**
     * 加锁
     *
     * @param key
     * @param value 当前时间+超时时间
     * @return
     */
    public boolean lock(String key, String value) {
        //相当于setnx命令
        if (redisTemplate.opsForValue().setIfAbsent(key, value)) {
            return true;
        }
        //下面的这段代码是判断之前加的锁是否超时，是的话就更新，一定要加这段代码
        //不然就有可能出现死锁。
        String currentValue = redisTemplate.opsForValue().get(key);
        //如果锁过期
        if (!StringUtils.isEmpty(currentValue)
                && Long.parseLong(currentValue) < System.currentTimeMillis()) {
            //获取上一个锁的时间，这段代码的判断是防止多线程进入这里，只会有一个线程拿到锁
            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
            if (!StringUtils.isEmpty(oldValue)
                    && oldValue.equals(currentValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解锁
     *
     * @param key
     * @param value
     */
    public void unLock(String key, String value) {
        try {
            String currentValue = redisTemplate.opsForValue().get(key);
            if (!StringUtils.isEmpty(currentValue)
                    && currentValue.equals(value)) {
                redisTemplate.opsForValue().getOperations().delete(key);
            }
        } catch (Exception e) {
            log.error("【redis分布式锁】 解锁异常，{}", e);
        }
    }
    private static final int TIME_OUT = 1 * 1000;
    /**
     * 模拟秒杀活动
     * @param productId
     */
    public void orderProductKill(String productId) {

        //加锁，保证下面的代码单线程的访问
        long time = System.currentTimeMillis() + TIME_OUT;
        if (!this.lock(productId, String.valueOf(time))) {
            throw new RuntimeException( "下单失败");
        }

        //1.查询该商品库存，为0则活动结束
        int stockNum = 100;
        if (stockNum == 0) {
            throw new RuntimeException("活动结束");
        } else {
            //2.下单(模拟不同用户openid不同)
            //orders.put(KeyUtil.getUniqueKey(), productId);
            //3.减库存
            stockNum = stockNum - 1;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           // stock.put(productId, stockNum);
        }
        //解锁
        this.unLock(productId, String.valueOf(time));
    }

}
