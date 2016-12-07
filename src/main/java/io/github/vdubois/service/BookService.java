package io.github.vdubois.service;

import io.github.vdubois.model.Book;
import io.github.vdubois.repository.BookRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by vdubois on 06/12/16.
 */
@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Cacheable(value = "booksByName", key = "#name")
    public Collection<Book> findByNameContainingIgnoreCase(String name) {
        return bookRepository.findByNameContainingIgnoreCase(name);
    }

    @Cacheable(value = "books", key = "#isbn")
    public Book findOneByIsbn(String isbn) {
        return bookRepository.findOneByIsbn(isbn);
    }
}
