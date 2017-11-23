package com.osmanco.springakka.common.util;

import com.osmanco.springakka.cassandra.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisUtil {

    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate redisTemplate;

    public void saveObject(String key, Object object) {
        redisTemplate.opsForValue().set(key, object);
        Customer savedCustomer = (Customer) redisTemplate.opsForValue().get(key);
        System.out.println("Saved Customer:" + savedCustomer);
    }

    public Object getObject(String key) {
        return redisTemplate.opsForValue().get(key);
    }
}
