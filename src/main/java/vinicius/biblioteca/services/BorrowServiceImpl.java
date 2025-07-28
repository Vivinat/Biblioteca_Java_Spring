package vinicius.biblioteca.services;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import vinicius.biblioteca.entities.Book;
import vinicius.biblioteca.entities.Borrow;
import vinicius.biblioteca.entities.Client;
import vinicius.biblioteca.exceptions.BusinessException;
import vinicius.biblioteca.payloads.BorrowRequestDTO;
import vinicius.biblioteca.repository.BookRepository;
import vinicius.biblioteca.repository.BorrowRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class BorrowServiceImpl implements BorrowService{

    private final BorrowRepository borrowRepository;
    private final BookService bookService;
    private final ClientService clientService;

    public BorrowServiceImpl(BorrowRepository borrowRepository, BookService bookService, ClientService clientService) {
        this.borrowRepository = borrowRepository;
        this.bookService = bookService;
        this.clientService = clientService;
    }

    @Override
    public Borrow createBorrow(BorrowRequestDTO borrowRequestDTO) {
        Book book = bookService.searchBook(borrowRequestDTO.bookId());
        Client client = clientService.searchClient(borrowRequestDTO.clientId());

        if (!book.isBorrowed()){
            Borrow borrow = new Borrow();
            borrow.setBookId(book);
            borrow.setClientId(client);
            book.setBorrowed(true);
            bookService.updateBook(book.getBook_id(), book);
            borrow.setDate_borrowed(LocalDate.now());
            return borrowRepository.save(borrow);
        } throw new BusinessException("Livro indisponivel para emprestimo");
    }

    @Override
    public Borrow searchBorrow(Integer borrowId) {
        return borrowRepository.findById(borrowId).
                orElseThrow(() -> new EntityNotFoundException("Não encontrado"));
    }

    @Override
    public Borrow updateBorrow(Integer borrowId, boolean is_returned) {
        Optional<Borrow> optionalBorrow = borrowRepository.findById(borrowId);

        if (optionalBorrow.isPresent()) {
            Borrow existingBorrow = optionalBorrow.get();

            if(is_returned){
                Book book = bookService.searchBook(existingBorrow.getBookId().getBook_id());
                book.setBorrowed(false);
                bookService.updateBook(existingBorrow.getBookId().getBook_id(), book);
                existingBorrow.setReturned(true);
                existingBorrow.setDate_returned(LocalDate.now());
            }

            return borrowRepository.save(existingBorrow);
        } else {
            throw new EntityNotFoundException("Borrow not found with id: " + borrowId);
        }
    }

    @Override
    public void deleteBorrow(Integer borrowId) {
        if (!borrowRepository.existsById(borrowId)) {
            throw new EntityNotFoundException("Empréstimo com ID " + borrowId + " não encontrado.");
        }
        borrowRepository.deleteById(borrowId);
    }
}
