package cutdb.user.dao;

import cutdb.user.domain.User;
import java.util.List;

/**
 * Created by trq on 2016/6/16.
 */
public interface UserDao {
    public User insert(User user);
    public List<User> findAll();
}
