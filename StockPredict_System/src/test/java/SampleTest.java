import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import stock.predict.dao.entity.User;
import stock.predict.dao.mapper.UserMapper;

import java.util.List;

/**
 * @author wangzhewen
 * @time 2022/4/8 10:48
 * @description: TODO
 */
@Deprecated
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)//让测试运行于Spring测试环境
public class SampleTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assert.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }

}
