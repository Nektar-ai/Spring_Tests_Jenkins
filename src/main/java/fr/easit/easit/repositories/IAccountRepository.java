package fr.easit.easit.repositories;

import fr.easit.easit.models.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAccountRepository extends CrudRepository {
    List<Account> findAll();
    Account findById(long id);
}
