package com.nyasha.fitnessapp.repo;


import com.nyasha.fitnessapp.models.Account;

import java.util.Optional;

public interface UserAccountRepository extends BaseRepository<Account> {

    boolean existsByUserName(String name);

    boolean existsByFirstName(String name);

    Account getByUserName(String username);

    Optional<Account> findByUserName(String username);

    Optional<Account> findByFirstName(String firstName);

}
