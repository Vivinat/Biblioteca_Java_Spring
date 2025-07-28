package vinicius.biblioteca.services;

import vinicius.biblioteca.entities.Client;

import java.util.UUID;

public interface ClientService {

    Client createClient (Client client);

    Client searchClient (UUID clientId);

    void deleteClient (UUID clientId);

    Client updateClient (UUID clientId, Client client);


}
