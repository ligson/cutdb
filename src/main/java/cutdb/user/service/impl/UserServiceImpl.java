package cutdb.user.service.impl;

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

    private final UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> list() {
        return new ArrayList<>(userDao.findAll());
    }

    @Override
    public List<User> list(String[] propNames) {
        return userDao.findAll();
    }

    @Override
    public List<User> list(Integer org) {
        return userDao.findAllByOrg(org.longValue());
    }

    @Override
    public User register(String name, String password, Boolean sex) {
        User user = new User();
        user.setName(name);
        user.setCreateDate(new Date());
        user.setSex(sex);
        user.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userDao.save(user);
        return user;
    }

    @Override
    public User save(User user) {
        return userDao.save(user);
    }
}
