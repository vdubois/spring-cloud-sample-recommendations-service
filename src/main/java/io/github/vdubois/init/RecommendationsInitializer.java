package io.github.vdubois.init;

import io.github.vdubois.model.Book;
import io.github.vdubois.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * Created by vdubois on 16/11/16.
 */
@Component
public class RecommendationsInitializer implements CommandLineRunner {

    private BookRepository bookRepository;

    @Autowired
    public RecommendationsInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... strings) throws Exception {
        Thread.currentThread().sleep((long) (Math.random() * 10000));
        Stream.of("Cloud Native Java,978-1449374648","Effective Java,978-1449374649","Head First Design Patterns,978-1449374640").map(
                recommendation -> {
                    Book bookRecommendation = new Book();
                    bookRecommendation.setName(recommendation.split(",")[0]);
                    bookRecommendation.setIsbn(recommendation.split(",")[1]);
                    return bookRecommendation;
                }
        ).forEach(bookRepository::save);
    }
}
