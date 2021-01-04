package com.nyasha.fitnessapp.repo;


import com.nyasha.fitnessapp.models.UserAccount;

import java.util.Optional;

public interface UserAccountRepository extends BaseRepository<UserAccount> {

    boolean existsByUsername(String name);

    boolean existsByFirstName(String name);

    UserAccount getByUsername(String username);

    Optional<UserAccount> findByUsername(String username);

    Optional<UserAccount> findByFirstName(String firstName);

}
