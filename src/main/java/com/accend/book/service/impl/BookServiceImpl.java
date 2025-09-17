package com.accend.book.service.impl;

import com.accend.book.dto.BookRequest;
import com.accend.book.dto.BookResponse;
import com.accend.book.exception.BookException;
import com.accend.book.exception.DataNotFound;
import com.accend.book.model.BookEntity;
import com.accend.book.repository.BookRepository;
import com.accend.book.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Page<BookResponse> getBooksByAuthor(String author, Pageable paging) throws DataNotFound, BookException {
        Page<BookResponse> books;
        if (author == null || author.trim().equals("")) {
            books = bookRepository.findAllBooks(paging);
        } else {
            books = bookRepository.findByAuthor(author, paging);
        }
        if (books.isEmpty()) {
            throw new DataNotFound("Book not found");
        }
        return books;
    }
/*
    public Page<BookResponse> getBooksByAuthor(String author, Pageable pageable) {
        return bookRepository.findByAuthorContainingIgnoreCase(author, pageable)
                .map(book -> new BookResponse(book.getId(), book.getTitle(), book.getAuthor(), book.getPublishedDate()));
    }
*/

    public BookEntity saveBook(BookRequest bookRequest) throws BookException {
        if (bookRequest == null) {
            throw new BookException("Please Enter Valid Input");
        }
        LocalDate gregorianDate = bookRequest.getPublishedDate().minusYears(543);
        BookEntity bookEntity = new BookEntity();
        bookEntity.setTitle(bookRequest.getTitle());
        bookEntity.setAuthor(bookRequest.getAuthor());
        bookEntity.setPublishedDate(gregorianDate);
        return bookRepository.save(bookEntity);
    }
}