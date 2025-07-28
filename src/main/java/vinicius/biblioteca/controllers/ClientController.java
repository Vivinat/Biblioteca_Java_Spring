package vinicius.biblioteca.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vinicius.biblioteca.entities.Book;
import vinicius.biblioteca.entities.Client;
import vinicius.biblioteca.services.BookService;
import vinicius.biblioteca.services.ClientService;

import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        Client createdClient = clientService.createClient(client);
        return new ResponseEntity<>(createdClient, HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id){
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> searchBook(@PathVariable UUID id){
        Client client = clientService.searchClient(id);
        return new ResponseEntity<>(client,HttpStatus.FOUND);
    }

    @PutMapping("{id}")
    public ResponseEntity<Client> updateClient(@RequestBody Client client, @PathVariable UUID id){
        Client updatedClient = clientService.updateClient(id, client);
        return ResponseEntity.ok(updatedClient);
    }

}
