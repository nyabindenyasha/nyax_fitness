package com.nyasha.fitnessapp.service;


import com.nyasha.fitnessapp.local.LoginRequest;
import com.nyasha.fitnessapp.models.UserAccount;

import java.util.Collection;

public interface UserAccountService extends BaseService<UserAccount, UserAccount, UserAccount> {

    UserAccount login(LoginRequest request);

    UserAccount updatePassword(LoginRequest request);

    UserAccount findByUsername(String username);

    UserAccount findByFirstName(String firstName);

    UserAccount findByUsernameOrFirstname(String username, String firstName);

    boolean existsByUsername(String username);

    String logout();

    Collection<UserAccount> findAllTeamAdmins();

    //You can think of other functionalities we need on accounts management. Good nyt man.
}
