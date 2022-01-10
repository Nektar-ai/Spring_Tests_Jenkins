package fr.easit.easit.repositories;

import fr.easit.easit.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IAccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAll();
    Account findById(long id);
}
