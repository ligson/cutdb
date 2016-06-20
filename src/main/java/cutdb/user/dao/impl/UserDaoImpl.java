package cutdb.user.dao.impl;

import cutdb.user.dao.UserDao;
import cutdb.user.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by trq on 2016/6/20.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User insert(User user) {
        sessionFactory.getCurrentSession().save(user);
        return user;
    }

    @Override
    public List<User> findAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.list();
    }
}
