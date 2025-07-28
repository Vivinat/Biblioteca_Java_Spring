package vinicius.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vinicius.biblioteca.entities.Client;

import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {
}
