package vinicius.biblioteca.services;
import vinicius.biblioteca.entities.*;
import vinicius.biblioteca.payloads.BorrowRequestDTO;

import java.util.Optional;
import java.util.UUID;

public interface BorrowService {

    Borrow createBorrow (BorrowRequestDTO borrow);

    Borrow searchBorrow (Integer borrowId);

    Borrow updateBorrow (Integer borrowId, boolean is_returned);

    void deleteBorrow (Integer borrowId);

}
