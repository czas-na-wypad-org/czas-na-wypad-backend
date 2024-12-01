package sggw.wzim.czasnawypad.db.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "USER")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;
    @Column(name = "LOGIN", nullable = false, unique = true)
    private String login;
    @Column(name = "PASSWORD", nullable = false)
    private String password;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "SURNAME", nullable = false)
    private String surname;
    @Email
    @Column(name = "EMAIL", nullable = false)
    private String email;
    @Column(name = "PHOTO", nullable = false)
    private String photo;
    @Column(name = "ROLES", nullable = false)
    private String roles;

    public void updateFrom(User source) {
        password = source.password;
        name = source.name;
        surname = source.surname;
        email = source.email;
        photo = source.photo;
    }
}
