package cutdb.user.service.impl;

import cutdb.org.domain.Org;
import cutdb.user.dao.UserDao;
import cutdb.user.domain.User;
import cutdb.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by trq on 2016/6/16.
 */
@Component("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> list() {
        List<User> users = new ArrayList<>();
        userDao.findAll().forEach(user1 -> users.add(user1));
        return users;
    }

    @Override
    public List<User> list(String[] propNames) {
        return userDao.list(propNames);
    }

    @Override
    public User register(String name, String password, Boolean sex, Org org) {
        User user = new User();
        user.setName(name);
        user.setCreateDate(new Date());
        user.setSex(sex);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        user.setOrg(org);
        userDao.insert(user);
        return user;
    }
}
