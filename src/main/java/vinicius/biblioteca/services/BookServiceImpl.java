package vinicius.biblioteca.services;

import jakarta.persistence.*;
import org.springframework.stereotype.Service;
import vinicius.biblioteca.entities.Book;
import vinicius.biblioteca.repository.BookRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class BookServiceImpl implements BookService{

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book searchBook(UUID bookId) {
        return bookRepository.findById(bookId)
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
    }

    @Override
    public void deleteBook(UUID bookId) {

        if (!bookRepository.existsById(bookId)) {
            throw new EntityNotFoundException("Livro com ID " + bookId + " não encontrado.");
        }
        bookRepository.deleteById(bookId);
    }

    @Override
    public Book updateBook(UUID bookId, Book book) {
        Optional<Book> optionalBook = bookRepository.findById(bookId);

        if (optionalBook.isPresent()) {
            Book existingBook = optionalBook.get();

            existingBook.setBook_name(book.getBook_name());
            existingBook.setGenre(book.getGenre());
            existingBook.setPublicationDate(book.getPublicationDate());

            return bookRepository.save(existingBook);
        } else {
            throw new EntityNotFoundException("Book not found with id: " + bookId);
        }
    }
}
