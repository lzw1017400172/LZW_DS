package com.lzw.web;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by LZW on 2019/4/5.
 */
@RestController
public class HystrixController {

    private static final Logger logger = LogManager.getLogger(HystrixController.class);

    /**
     * 服务降级处理fallback
     */
    @RequestMapping("/hystrix_timeout")
    public Object hystrixTimeout(String param){
        logger.info(param + "服务短路，触发降级");
        String warning = param + "timeout";
        Map<String,Object> map = new HashMap<>();
        map.put("httpCode","-9999");
        map.put("msg", warning);
        return map;
    }
}
