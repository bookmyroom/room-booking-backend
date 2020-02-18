package pl.booking.bookmyroom.user.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.Email;


@Entity
@Table(name = "users")
@Getter @Setter

public class User extends MyUserDetails {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Email
    @Column(unique = true)
    private String username;
    private String password;
    private boolean active;
    private String roles;

    public User(User user) {
        super(user);
    }
    public User() {
        super();
    }

}
