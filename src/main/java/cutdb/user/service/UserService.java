package cutdb.user.service;

import cutdb.user.domain.User;

import java.util.List;

/**
 * Created by trq on 2016/6/16.
 */
public interface UserService {
    List<User> list();

    List<User> list(String[] propNames);

    List<User> list(Integer org);

    User register(String name, String password, Boolean sex);

    User save(User user);
}
