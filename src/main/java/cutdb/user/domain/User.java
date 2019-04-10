package cutdb.user.domain;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by trq on 2016/6/16.
 */
@Entity
@Table(name = "tt_user")
@Data
public class User implements Serializable {
    @Id
    @GenericGenerator(name = "jpa-uuid", strategy = "org.hibernate.id.UUIDGenerator")
    @GeneratedValue(generator = "jpa-uuid")
    private String id;
    @Column(unique = true, nullable = false)
    private String name;
    @Column(nullable = false)
    private String password;
    @Column
    private Date createDate;
    @Column
    private Boolean sex;
    @Column
    private Long org;
}
