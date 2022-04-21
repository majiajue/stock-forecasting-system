package stock.predict.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import stock.predict.dao.entity.Suggest;
import stock.predict.dao.mapper.SuggestMapper;
import stock.predict.service.ISuggestService;

/**
 * <p>
 * 用户建议 服务实现类
 * </p>
 *
 * @author wangzhewen
 * @since 2022-04-19
 */
@Service
public class SuggestServiceImpl extends ServiceImpl<SuggestMapper, Suggest> implements ISuggestService {

}
