package com.nyasha.fitnessapp.service;

import com.minimum.local.InvalidRequestException;
import com.nyasha.fitnessapp.local.LoginRequest;
import com.nyasha.fitnessapp.local.enums.Role;
import com.nyasha.fitnessapp.models.Account;
import com.nyasha.fitnessapp.repo.UserAccountRepository;
import lombok.val;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;


@Service
class UserAccountServiceImpl extends BaseServiceImpl<Account, Account, Account> implements UserService {

    private final UserAccountRepository userAccountRepository;

    UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        super(userAccountRepository);
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    protected Class<Account> getEntityClass() {
        return Account.class;
    }

    @Override
    public Account create(Account request) {

        boolean detailsExist = userAccountRepository.existsByUserName(request.getUserName());

        if (detailsExist) {
            throw new InvalidRequestException("UserAccount already exist.");
        }

        Account userAccount = Account.fromCommand(request);

        return userAccountRepository.save(userAccount);
    }

    @Override
    public Account update(Account request) {

        boolean detailsExists = userAccountRepository.existsByUserName(request.getUserName());

        if (!detailsExists) {
            throw new InvalidRequestException("UserAccount not found");
        }

        Account userAccount = findById(request.getId());

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
    public Account login(LoginRequest request) {
        Account account;
        if (!userAccountRepository.existsByUserName(request.getUserName())) {
            throw new InvalidRequestException("User does not exist");
        }
        account = findByUsername(request.getUserName());

        if (!account.getPassword().equals(request.getPassword())) {
            throw new InvalidRequestException("Wrong password");
        }
        return account;
    }

    @Override
    public Account updatePassword(LoginRequest request) {
        return null;
    }


    @Override
    public Account findByUsername(String username) {
        return userAccountRepository.findByUserName(username).get();
        //      .orElseThrow(() -> new InvalidRequestException("User record was not found for the supplied userName"));
    }

    @Override
    public Account findByFirstName(String firstName) {
        return userAccountRepository.findByFirstName(firstName).get();
        //     .orElseThrow(() -> new InvalidRequestException("User record was not found for the supplied firstName"));
    }

    @Override
    public Account findByUsernameOrFirstname(String username, String firstName) {

        boolean userByUsernameExists = userAccountRepository.existsByUserName(username);
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
            throw new InvalidRequestException("User record was not found for the supplied firstName or userName");
    }

    @Override
    public String logout() {
        return null;
    }

    @Override
    public Collection<Account> findAllTeamAdmins() {
        Collection<Account> accounts = userAccountRepository.findAll();
        Collection<Account> lecturers = accounts.parallelStream().filter(account -> account.getRole() == Role.TEAM_ADMIN).collect(Collectors.toList());
        return lecturers;
    }
}
