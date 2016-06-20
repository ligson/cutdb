package cutdb.org.dao.impl;

import cutdb.org.dao.OrgDao;
import cutdb.org.domain.Org;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by trq on 2016/6/20.
 */
@Repository("orgDao")
public class OrgDaoImpl implements OrgDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Org insert(Org org) {
        sessionFactory.getCurrentSession().save(org);
        return org;
    }

    @Override
    public List<Org> findAll() {
        Query query = sessionFactory.getCurrentSession().createQuery("from Org");
        return query.list();
    }
}
