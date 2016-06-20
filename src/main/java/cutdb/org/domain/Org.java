package cutdb.org.domain;

import cutdb.user.domain.User;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by trq on 2016/6/20.
 */
@Entity
@Table(name = "t_org")
public class Org {
    @Id
    @GenericGenerator(name = "systemUUID", strategy = "uuid")
    @GeneratedValue(generator = "systemUUID")
    private String id;
    @Column(nullable = false, unique = true)
    private String name;
    @OneToMany(mappedBy = "org", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column
    private Set<User> users = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
