package io.github.vdubois.repository;

import io.github.vdubois.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.Set;

/**
 * Created by vdubois on 16/11/16.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query("select b from Book b where b.name like %:bookName%")
    Set<Book> getBooksRecommendationsForBookWithName(@Param(value = "bookName") String bookName);

    Collection<Book> findByNameContainingIgnoreCase(String name);

    Book findOneByIsbn(String isbn);
}
