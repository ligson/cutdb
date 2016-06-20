package cutdb.user.controller;

import cutdb.user.domain.User;
import cutdb.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by trq on 2016/6/16.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!";
    }

    @RequestMapping("/list.json")
    @ResponseBody
    List<User> list() {
        return userService.list();
    }

    @RequestMapping("/register.do")
    @ResponseBody
    User register(@RequestParam String name, @RequestParam String password, @RequestParam(required = false, defaultValue = "true") Boolean sex) {
        //return userService.register(name, password, sex);
        return null;
    }
}
