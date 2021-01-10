package com.nyasha.fitnessapp.service;

import com.nyasha.fitnessapp.models.UserAccount;
import com.nyasha.fitnessapp.repo.UserAccountRepository;
import org.springframework.stereotype.Service;

/**
 * @author Nyabinde Nyasha
 * @created 1/10/2021
 * @project fitness-be
 */

@Service
public class UserAccountServiceHelperImpl extends BaseServiceImpl<UserAccount, UserAccount, UserAccount> implements UserAccountServiceHelper {

    private final UserAccountRepository userAccountRepository;

    UserAccountServiceHelperImpl(UserAccountRepository userAccountRepository, AthleteService athleteService) {
        super(userAccountRepository);
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    protected Class<UserAccount> getEntityClass() {
        return UserAccount.class;
    }

    @Override
    public UserAccount create(UserAccount createDto) {
        return null;
    }

    @Override
    public UserAccount update(UserAccount updateDto) {
        return null;
    }
}
