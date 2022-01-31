package fr.easit.easit.services;

import fr.easit.easit.models.Account;
import fr.easit.easit.repositories.IAccountRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import org.slf4j.Logger;

@Service
public class AccountService implements IAccountService {

    Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    private IAccountRepository repository;


    @Override
    public List<Account> findAll() {
        List<Account> accounts = repository.findAll();
        for (Account a : accounts ) {
            log.info(a.toString());
        }
        return accounts;
    }

    public List<Account> findAllByAge(){
        List<Account> accounts = repository.findAllOrderByAge();
        for (Account a : accounts ) {
            log.info(a.toString());
        }
        return accounts;
    }
}
