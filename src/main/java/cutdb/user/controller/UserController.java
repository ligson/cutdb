package cutdb.user.controller;

import cutdb.user.domain.User;
import cutdb.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by trq on 2016/6/16.
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/")
    String home() {
        return "Hello World!";
    }

    @GetMapping("/list.json")
    List<User> list() {
        return userService.list();
    }

    @GetMapping("/listByOrg.json")
    List<User> listByOrg(@RequestParam Integer org) {
        return userService.list(org);
    }

    @PostMapping("/register.do")
    User register(@RequestBody User user) {
        //return userService.register(name, password, sex);
        return userService.save(user);
    }
}
