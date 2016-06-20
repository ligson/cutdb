package cutdb.org.service;

import cutdb.org.domain.Org;
import cutdb.user.domain.User;

import java.util.List;

/**
 * Created by trq on 2016/6/20.
 */
public interface OrgService {
    Org addOrg(String name);

    List<Org> query(Org org);
}
