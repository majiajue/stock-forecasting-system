package com.stockpredict.system.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangzhewen
 * @time 2022/2/23 10:19
 * @description: TODO
 */

@RestController
public class ModelConnect {

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @GetMapping("/data")
    public String getForObject() {
        String url = "http://127.0.0.1:5000/";
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

}
