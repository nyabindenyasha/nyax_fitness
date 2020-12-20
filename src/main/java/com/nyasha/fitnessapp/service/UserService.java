package com.nyasha.fitnessapp.service;


import com.nyasha.fitnessapp.local.LoginRequest;
import com.nyasha.fitnessapp.models.Account;

import java.util.Collection;

public interface UserService extends BaseService<Account, Account, Account> {

    Account login(LoginRequest request);

    Account updatePassword(LoginRequest request);

    Account findByUsername(String username);

    Account findByFirstName(String firstName);

    Account findByUsernameOrFirstname(String username, String firstName);

    String logout();

    Collection<Account> findAllTeamAdmins();

    //You can think of other functionalities we need on accounts management. Good nyt man.
}
