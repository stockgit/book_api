package com.accend.book.controller;

import com.accend.book.dto.BookRequest;
import com.accend.book.exception.BookException;
import com.accend.book.exception.DataNotFound;
import com.accend.book.service.BookService;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * Book Controller
 *
 * @author Satib
 * @version 1.0
 */

@RestController
@RequestMapping("api/v1/books")
public class BookController {
    Logger logger = Logger.getLogger(BookController.class.getName());
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<?> getBooksByAuthor(
            @RequestParam(required = false, defaultValue = "") String author,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) throws BookException, DataNotFound {
        logger.info("Get Books By Author");
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(bookService.getBooksByAuthor(author, paging));
    }

    @PostMapping
    public ResponseEntity<?> saveBook(@RequestBody @Valid BookRequest bookRequest) throws BookException {
        logger.info("Save Book");
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.saveBook(bookRequest));
    }
}