package com.accend.book.dto;

import com.accend.book.utils.ThaiPastOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
/**
 * Request a book in the system.
 * @author Satib
 * @version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    private Long id;

    @NotEmpty(message = "Title cannot be Empty")
    private String title;

    @NotEmpty(message = "Author cannot be Empty")
    private String author;

    @NotNull(message = "Published Date cannot be null")
    @ThaiPastOrPresent
    private LocalDate publishedDate;

}