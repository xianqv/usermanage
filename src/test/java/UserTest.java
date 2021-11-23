import com.usermanage.dao.UserDao;
import com.usermanage.dao.impl.UserDaoImpl;
import com.usermanage.domain.UserBean;
import org.junit.jupiter.api.Test;

import java.util.List;
public class UserTest {
    @Test
    public void userList(){
        UserDao userDao=new UserDaoImpl();

        //System.out.println( userDao.findByUsernameAndPassword("zhangsan","Password01!"));
        System.out.println(userDao.findByUsernameAndPassword("zhang22san","password01!"));
    }
}
