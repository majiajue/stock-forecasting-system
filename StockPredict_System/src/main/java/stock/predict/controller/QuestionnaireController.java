package stock.predict.controller;

import cn.hutool.core.collection.ListUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author wangzhewen
 * @time 2022/4/12 19:50
 * @description: TODO
 */
@RestController
@RequestMapping("/questionnaire")
public class QuestionnaireController {

    @GetMapping("/get")
    public List getQuestionnaire() throws IOException {
        JSONObject json;
        String path = "/questionnaire.json";
        InputStream config = getClass().getResourceAsStream(path);
        if (config == null) {
            throw new RuntimeException("读取文件失败");
        } else {
            json = JSON.parseObject(config, JSONObject.class);
        }
        return ListUtil.toList(json.getJSONArray("data"));
    }

}
