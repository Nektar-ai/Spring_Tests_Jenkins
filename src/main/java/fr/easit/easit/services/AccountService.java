package fr.easit.easit.services;

import fr.easit.easit.models.Account;
import fr.easit.easit.repositories.IAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService implements IAccountService {

    @Qualifier("accountRepository")
    @Autowired
    private IAccountRepository repository;


    @Override
    public List<Account> findAll() {
        List<Account> accounts = repository.findAll();
        return accounts;
    }
}
