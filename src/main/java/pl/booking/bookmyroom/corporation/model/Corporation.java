package pl.booking.bookmyroom.corporation.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "corporations")
public class Corporation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String password;

    @Email
    @Column(unique = true)
    private String email;

    private boolean active;
    private String roles;
}
