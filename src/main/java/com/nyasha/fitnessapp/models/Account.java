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
public class Account implements Serializable {

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
    @Column(name = "user_name")
    private String userName;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Column(name = "password")
    private String password;

    @Column(name = "team_id")
    private int teamId;

    public Account() {
    }

    public Account(Integer id) {
        this.id = id;
    }

    public Account(Integer id, String firstName, String lastName, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public static Account fromCommand(Account request) {

        if (request == null) {
            return null;
        }

        Account account = new Account();
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
        account.setUserName(request.getUserName());
        account.setPassword(request.getPassword());
        account.setRole(request.getRole());
        account.setTeamId(request.teamId);
        return account;
    }

    public void update(Account updateRequest) {
        this.setFirstName(updateRequest.getFirstName());
        this.setLastName(updateRequest.getLastName());
        this.setUserName(updateRequest.getUserName());
        this.setRole(updateRequest.getRole());
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", role=" + role +
                '}';
    }
}
