package com.stockpredict.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * @author wangzhewen
 * @time 2022/2/23 10:48
 * @description: TODO
 */
@Component
public class modelConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
