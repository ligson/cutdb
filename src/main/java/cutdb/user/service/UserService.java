package cutdb.user.service;

import cutdb.org.domain.Org;
import cutdb.user.domain.User;

import java.util.List;

/**
 * Created by trq on 2016/6/16.
 */
public interface UserService {
    public List<User> list();

    public User register(String name, String password, Boolean sex, Org org);
}
