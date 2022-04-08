package stock.predict;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author wangzhewen
 * @time 2022/4/8 10:33
 * @description: TODO
 */

@SpringBootApplication
@MapperScan("stock.predict.dao.mapper")
public class SystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(SystemApplication.class, args);
    }

}
