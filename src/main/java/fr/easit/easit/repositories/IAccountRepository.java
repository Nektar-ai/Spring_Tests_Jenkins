package fr.easit.easit.repositories;

import fr.easit.easit.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IAccountRepository extends JpaRepository<Account, Long> {
    List<Account> findAll();

    Account findById(long id);

    @Query(value = "SELECT *  FROM accounts ORDER BY CAST (info ->> 'age' AS INTEGER) DESC", nativeQuery = true)
    List<Account> findAllOrderByAge();
}
