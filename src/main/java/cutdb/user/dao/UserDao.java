package cutdb.user.dao;

import cutdb.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by trq on 2016/6/16.
 */
@Repository
public interface UserDao extends JpaRepository<User, String> {
    List<User> findAllByOrg(Long org);
}
