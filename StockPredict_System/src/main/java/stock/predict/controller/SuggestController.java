package stock.predict.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import stock.predict.common.domain.ResResult;
import stock.predict.dao.entity.Suggest;
import stock.predict.service.ISuggestService;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户建议 前端控制器
 * </p>
 *
 * @author wangzhewen
 * @since 2022-04-19
 */
@RestController
@RequestMapping("/suggest")
public class SuggestController {

    @Resource
    ISuggestService iSuggestService;

    @PostMapping("/insert")
    public ResResult insert(@RequestBody Suggest suggest){
        suggest.setCreateTime(LocalDateTime.now());
        iSuggestService.save(suggest);
        return ResResult.success();
    }

}
