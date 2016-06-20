package cutdb.main;

import cutdb.org.domain.Org;
import cutdb.org.service.OrgService;
import cutdb.user.domain.User;
import cutdb.user.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by ligson on 2016/6/20.
 */
public class Bootstrap {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-conf.xml");
        OrgService orgService = (OrgService) applicationContext.getBean("orgService");
        UserService userService = (UserService) applicationContext.getBean("userService");
        Org org = orgService.addOrg("org1");
        User user = userService.register("user1", "password", true, org);
    }
}
