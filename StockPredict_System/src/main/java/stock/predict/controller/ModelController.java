package stock.predict.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import stock.predict.common.domain.ResResult;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangzhewen
 * @time 2022/4/11 17:50
 * @description: TODO
 */
@RestController
public class ModelController {

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/data")
    public ResResult<Object> getForObject(String data, Integer predictDate) {

        Map<String, Object> params = new HashMap<>(2);
        params.put("data", data);
        params.put("predictDate", predictDate);

        String url = "http://127.0.0.1:5000/";
        String result = restTemplate.postForObject(url, params, String.class);

        return ResResult.success(JSON.parseObject(result));
    }

}
