package fr.easit.repositories;

import fr.easit.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    //public List<Client> findAll();
    //public Client getById(Integer integer);

}