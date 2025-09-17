package com.accend.book.service;

import com.accend.book.dto.BookRequest;
import com.accend.book.dto.BookResponse;
import com.accend.book.exception.BookException;
import com.accend.book.exception.DataNotFound;
import com.accend.book.model.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    Page<BookResponse> getBooksByAuthor(String author, Pageable paging) throws DataNotFound, BookException;

    BookEntity saveBook(BookRequest bookRequest) throws BookException;

    //Page<BookResponse> getBooksByAuthor(String author, Pageable pageable);
}
