package cf.sessiontest.test;

import com.cf.sessiontest.model.User;
import com.cf.sessiontest.service.IUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath:spring-dao.xml")
public class TransactionTest {
    @Autowired
    private IUserService userService;

    @Test
    public void test(){
        User user = userService.queryUserById("6be8c36aaaf44926a1727a621e15d1c8");
        System.out.println("");
    }

    @Test
    public void update() throws Exception {
        User user = new User();
        user.setId("6be8c36aaaf44926a1727a621e15d1c8");
        user.setName("cf");
        int i = userService.updateUser(user);
        System.out.println(i);
    }
}
