package com.stockpredict.system;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

/**
 * @author WangZheWen
 */
@SpringBootApplication
public class StockPredictSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockPredictSystemApplication.class, args);
    }

}
