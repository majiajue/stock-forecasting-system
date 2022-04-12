package com.stockpredict.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author wangzhewen
 * @time 2022/2/23 10:19
 * @description: TODO
 */

@Deprecated
@RestController
public class ModelConnect {

    @Resource
    private RestTemplate restTemplate;

    @PostMapping("/data")
    public List<Double> getForObject(@Validated String stock) {
        // 调用接口获得预测结果
        String url = "http://127.0.0.1:5000/";
        HttpEntity<String> request = new HttpEntity<>(stock);
        String result = restTemplate.postForObject(url, request, String.class);
        // 对结果进行格式化处理
        List<String> res_temp = Arrays.asList(result.split(","));
        List<Double> res = res_temp.stream().map(data -> {
            Double temp = Double.valueOf(data.trim());
            return temp;
        }).collect(Collectors.toList());
        System.out.println(res);
        return res;
    }

}
