package fr.easit.repositories;

import fr.easit.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Override
    public List<Client> findAll();

    @Override
    public Client getById(Integer integer);

}