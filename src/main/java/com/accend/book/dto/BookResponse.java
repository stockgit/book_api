package com.accend.book.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Response a book in the system.
 * @author Satib
 * @version 1.0
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long id;

    private String title;

    private String author;

    private LocalDate publishedDate;
}