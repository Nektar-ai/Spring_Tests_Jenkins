package fr.easit.services;

import fr.easit.models.Client;

import java.util.List;

public interface ClientService {
    public abstract List<Client> findAll();
    public abstract Client getById(Integer id);
}
