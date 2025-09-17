package com.accend.book.repository;

import com.accend.book.dto.BookResponse;
import com.accend.book.model.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookRepository extends JpaRepository<BookEntity, Long> {
    @Query("SELECT new com.accend.book.dto.BookResponse(b.id, b.title, b.author, b.publishedDate) FROM BookEntity b WHERE LOWER(b.author) LIKE LOWER(CONCAT('%', :author, '%'))")
    Page<BookResponse> findByAuthor(@Param("author") String author, Pageable paging);

    @Query("SELECT new com.accend.book.dto.BookResponse(b.id, b.title, b.author, b.publishedDate) FROM BookEntity b")
    Page<BookResponse> findAllBooks(Pageable paging);

    //EXPLAIN select id,title,author,published_date from books where lower(author) like lower('%satib%');

    //Page<BookResponse> findByAuthorContainingIgnoreCase(String author, Pageable paging);
}