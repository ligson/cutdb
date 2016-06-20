package cutdb.org.dao;

import cutdb.org.domain.Org;

import java.util.List;

/**
 * Created by trq on 2016/6/20.
 */
public interface OrgDao {
    Org insert(Org org);

    List<Org> findAll();
}
