package cutdb.user.dao.impl;

import cutdb.user.dao.UserDao;
import cutdb.user.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by trq on 2016/6/20.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {
    private static Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
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
        return (List<User>) query.list();
    }

    @Override
    public List<User> list(String[] propNames) {
        String hql = "select ";
        for (String propName : propNames) {
            hql += propName + ",";
        }
        hql = hql.substring(0, hql.length() - 1)+" from User";
        logger.info("-----------------------------------------------------hql:{}",hql);
        Query query = sessionFactory.getCurrentSession().createQuery(hql);
        query.
        List<User> users = new ArrayList<>();
        List list = query.list();
        return (List<User>) query.list();
    }
}
