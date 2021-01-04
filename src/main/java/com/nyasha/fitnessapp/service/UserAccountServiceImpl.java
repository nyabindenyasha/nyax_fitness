package com.nyasha.fitnessapp.service;

import com.minimum.local.InvalidRequestException;
import com.nyasha.fitnessapp.local.LoginRequest;
import com.nyasha.fitnessapp.local.enums.Role;
import com.nyasha.fitnessapp.models.UserAccount;
import com.nyasha.fitnessapp.repo.UserAccountRepository;
import lombok.val;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
class UserAccountServiceImpl extends BaseServiceImpl<UserAccount, UserAccount, UserAccount> implements UserAccountService {

    private final UserAccountRepository userAccountRepository;

    UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        super(userAccountRepository);
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    protected Class<UserAccount> getEntityClass() {
        return UserAccount.class;
    }

    @Override
    public UserAccount create(UserAccount request) {

        boolean detailsExist = userAccountRepository.existsByUsername(request.getUsername());

        if (detailsExist) {
            throw new InvalidRequestException("UserAccount already exist.");
        }

        UserAccount userAccount = UserAccount.fromCommand(request);

        return userAccountRepository.save(userAccount);
    }

    @Override
    public UserAccount update(UserAccount request) {

        boolean detailsExists = userAccountRepository.existsByUsername(request.getUsername());

        if (!detailsExists) {
            throw new InvalidRequestException("UserAccount not found");
        }

        UserAccount userAccount = findById(request.getId());

        userAccount.update(request);

        return userAccountRepository.save(userAccount);
    }

    @Override
    public void delete(Long id) {
        try {
            super.delete(id);
        } catch (ConstraintViolationException var3) {
            throw new InvalidRequestException("You can not delete this record is might be used by another record");
        }
    }


    @Override
    public UserAccount login(LoginRequest request) {
        UserAccount userAccount;
        if (!userAccountRepository.existsByUsername(request.getUserName())) {
            throw new InvalidRequestException("User does not exist");
        }
        userAccount = findByUsername(request.getUserName());

        if (!userAccount.getPassword().equals(request.getPassword())) {
            throw new InvalidRequestException("Wrong password");
        }
        return userAccount;
    }

    @Override
    public UserAccount updatePassword(LoginRequest request) {
        return null;
    }


    @Override
    public UserAccount findByUsername(String username) {
        return userAccountRepository.findByUsername(username).get();
        //      .orElseThrow(() -> new InvalidRequestException("User record was not found for the supplied username"));
    }

    @Override
    public UserAccount findByFirstName(String firstName) {
        return userAccountRepository.findByFirstName(firstName).get();
        //     .orElseThrow(() -> new InvalidRequestException("User record was not found for the supplied firstName"));
    }

    @Override
    public UserAccount findByUsernameOrFirstname(String username, String firstName) {

        boolean userByUsernameExists = userAccountRepository.existsByUsername(username);
        boolean userByFirstNameExists = userAccountRepository.existsByFirstName(firstName);

        if (userByUsernameExists) {
            val userByUsername = findByUsername(username);
            System.out.println(userByUsername);
            return userByUsername;
        } else if (userByFirstNameExists) {
            val userByFirstName = findByFirstName(firstName);
            System.out.println(userByFirstName);
            return userByFirstName;
        } else
            throw new InvalidRequestException("User record was not found for the supplied firstName or username");
    }

    @Override
    public boolean existsByUsername(String username) {
        return userAccountRepository.existsByUsername(username);
    }

    @Override
    public String logout() {
        return null;
    }

    @Override
    public Collection<UserAccount> findAllTeamAdmins() {
        Collection<UserAccount> userAccounts = userAccountRepository.findAll();
        Collection<UserAccount> lecturers = userAccounts.parallelStream().filter(account -> account.getRole() == Role.TEAM_ADMIN).collect(Collectors.toList());
        return lecturers;
    }
}
