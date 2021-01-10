package com.nyasha.fitnessapp.models;

import com.nyasha.fitnessapp.local.enums.Role;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author GustTech
 */

@Data
@Entity
@Table(name = "account")
public class UserAccount implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "first_name")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "last_name")
    private String lastName;

    @Size(min = 1, max = 50)
    @Column(name = "username")
    private String username;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(name = "password")
    private String password;

    @Transient
    private Teams team;

    @Column(name = "team_id")
    private int teamId;

    public UserAccount() {
    }

    public UserAccount(Integer id) {
        this.id = id;
    }

    public UserAccount(Integer id, String firstName, String lastName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public static UserAccount fromCommand(UserAccount request) {

        if (request == null) {
            return null;
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setFirstName(request.getFirstName());
        userAccount.setLastName(request.getLastName());
        userAccount.setUsername(request.getUsername());
        userAccount.setPassword(request.getPassword());
        userAccount.setRole(request.getRole());
        userAccount.setTeamId(request.teamId);
        return userAccount;
    }

    public void update(UserAccount updateRequest) {
        this.setFirstName(updateRequest.getFirstName());
        this.setLastName(updateRequest.getLastName());
        this.setUsername(updateRequest.getUsername());
        this.setRole(updateRequest.getRole());
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", role=" + role +
                '}';
    }
}
