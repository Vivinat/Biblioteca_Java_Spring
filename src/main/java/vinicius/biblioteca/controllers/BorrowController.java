package vinicius.biblioteca.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vinicius.biblioteca.entities.Borrow;
import vinicius.biblioteca.payloads.BorrowRequestDTO;
import vinicius.biblioteca.services.BorrowService;

import java.util.UUID;

@RestController
@RequestMapping("/api/borrows")
public class BorrowController {

    private final BorrowService borrowService;

    public BorrowController(BorrowService borrowService) {
        this.borrowService = borrowService;
    }

    @PostMapping
    public ResponseEntity<Borrow> createBorrow(@RequestBody BorrowRequestDTO borrow){
        Borrow createdBorrow = borrowService.createBorrow(borrow);
        return new ResponseEntity<>(createdBorrow, HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Borrow> searchBorrow(@PathVariable Integer id){
        Borrow borrow = borrowService.searchBorrow(id);
        return new ResponseEntity<>(borrow,HttpStatus.FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBorrow(@PathVariable Integer id){
        borrowService.deleteBorrow(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/return")
    public ResponseEntity<Borrow> updateBorrow(@PathVariable Integer id){
        Borrow updatedBorrow = borrowService.updateBorrow(id,true);
        return ResponseEntity.ok(updatedBorrow);
    }
}
