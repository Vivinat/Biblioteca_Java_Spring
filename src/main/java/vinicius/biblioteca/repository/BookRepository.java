package vinicius.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vinicius.biblioteca.entities.Book;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
}
