package vinicius.biblioteca.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import vinicius.biblioteca.entities.Client;
import vinicius.biblioteca.repository.ClientRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService{

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client searchClient(UUID clientId) {
        return clientRepository.findById(clientId).
                orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado"));
    }

    @Override
    public void deleteClient(UUID clientId) {
        if (!clientRepository.existsById(clientId)) {
            throw new EntityNotFoundException("Cliente com ID " + clientId + " não encontrado.");
        }
        clientRepository.deleteById(clientId);
    }

    @Override
    public Client updateClient(UUID clientId, Client client) {
        Optional<Client> optionalClient = clientRepository.findById(clientId);

        if (optionalClient.isPresent()) {
            Client existingClient = optionalClient.get();

            existingClient.setClient_name(client.getClient_name());
            return clientRepository.save(existingClient);
        } else {
            throw new EntityNotFoundException("Book not found with id: " + clientId);
        }
    }
}
