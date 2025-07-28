package vinicius.biblioteca.services;

import vinicius.biblioteca.entities.Book;

import java.util.UUID;

public interface BookService {

    Book createBook(Book book);

    Book searchBook(UUID bookId);

    void deleteBook(UUID bookId);

    Book updateBook(UUID bookId, Book book);

}
