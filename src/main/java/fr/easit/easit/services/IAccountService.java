package fr.easit.easit.services;

import fr.easit.easit.models.Account;

import java.util.List;

public interface IAccountService {
    List<Account> findAll();
}
