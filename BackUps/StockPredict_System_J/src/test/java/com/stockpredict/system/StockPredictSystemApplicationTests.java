package com.stockpredict.system;

import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

@SpringBootTest
class StockPredictSystemApplicationTests {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void contextLoads() {
    }

/*
    @Test
    public void testGet_product1() {
        String url = "http://localhost:5000";
        //方式一：GET 方式获取 JSON 串数据
        String result = restTemplate.getForObject(url, String.class);
        System.out.println("get_product1返回结果：" + result);
        Assert.hasText(result, "get_product1返回结果为空");

        //方式二：GET 方式获取 JSON 数据映射后的 Product 实体对象
        Product product = restTemplate.getForObject(url, Product.class);
        System.out.println("get_product1返回结果：" + product);
        Assert.notNull(product, "get_product1返回结果为空");

        //方式三：GET 方式获取包含 Product 实体对象 的响应实体 ResponseEntity 对象,用 getBody() 获取
        ResponseEntity<Product> responseEntity = restTemplate.getForEntity(url, Product.class);
        System.out.println("get_product1返回结果：" + responseEntity);
        Assert.isTrue(responseEntity.getStatusCode().equals(HttpStatus.OK), "get_product1响应不成功");
    } */

}
