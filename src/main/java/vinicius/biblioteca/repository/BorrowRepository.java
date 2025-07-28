package vinicius.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vinicius.biblioteca.entities.Borrow;

import java.util.UUID;

public interface BorrowRepository extends JpaRepository<Borrow, Integer> {
}
