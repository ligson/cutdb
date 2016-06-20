package cutdb.org.service.impl;

import cutdb.org.dao.OrgDao;
import cutdb.org.domain.Org;
import cutdb.org.service.OrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by trq on 2016/6/20.
 */
@Component("orgService")
public class OrgServiceImpl implements OrgService {
    @Autowired
    private OrgDao orgDao;

    @Override
    public Org addOrg(String name) {
        Org org = new Org();
        org.setName(name);
        return orgDao.insert(org);
    }

    @Override
    public List<Org> query(Org org) {
        return orgDao.findAll();
    }
}
