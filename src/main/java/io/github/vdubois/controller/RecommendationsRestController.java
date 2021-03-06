package io.github.vdubois.controller;

import io.github.vdubois.model.Book;
import io.github.vdubois.model.Recommendation;
import io.github.vdubois.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by vdubois on 08/11/16.
 */
@RestController
public class RecommendationsRestController {

    private BookService bookService;

    public RecommendationsRestController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/books/{isbn}/recommendations")
    public ResponseEntity<List<Recommendation>> getRecommendationsForBookWithIsbn(@PathVariable(value = "isbn") String isbn) {
        Set<Book> recommendedBooks = new HashSet<>();
        Book selectedBook = bookService.findOneByIsbn(isbn);
        Stream.of(selectedBook.getName().split(" "))
                .forEach(bookWord -> recommendedBooks.addAll(bookService.findByNameContainingIgnoreCase(bookWord)));
        List<Recommendation> recommentations = recommendedBooks.stream().map(book -> {
            Recommendation recommendation = new Recommendation();
            recommendation.setBookName(book.getName());
            recommendation.setBookIsbn(book.getIsbn());
            return recommendation;
        }).collect(Collectors.toList());
        return new ResponseEntity<>(recommentations, HttpStatus.OK);
    }
}
