package com.accend.book;

import com.accend.book.dto.BookRequest;
import com.accend.book.model.BookEntity;
import com.accend.book.repository.BookRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ActiveProfiles("test")
public class BookEntityApiIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    void setup() {
        bookRepository.deleteAll();
    }

    @Test
    void testSaveBook_withValidData_shouldReturn200() throws Exception {
        BookRequest request = new BookRequest();
        request.setTitle("Clean Code");
        request.setAuthor("Robert C. Martin");
        request.setPublishedDate(LocalDate.of(2024, 8, 1));

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title").value("Clean Code"))
                .andExpect(jsonPath("$.author").value("Robert C. Martin"));
    }

    @Test
    void testGetBooksByAuthor_shouldReturnMatchingBooks() throws Exception {
        BookEntity bookEntity1 = new BookEntity(null, "Book A", "Author X", LocalDate.of(2025, 1, 1));
        BookEntity bookEntity2 = new BookEntity(null, "Book B", "Author X", LocalDate.of(2025, 1, 1));
        BookEntity bookEntity3 = new BookEntity(null, "Book C", "Author Y", LocalDate.of(2025, 1, 1));
        bookRepository.saveAll(List.of(bookEntity1, bookEntity2, bookEntity3));

        mockMvc.perform(get("/api/v1/books")
                        .param("author", "Author X"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content").isArray());

    }

    @Test
    void testGetBooksByAuthor_shouldReturnNotMatchingBooks() throws Exception {
        BookEntity bookEntity1 = new BookEntity(null, "Book A", "Author X", LocalDate.of(2025, 1, 1));
        BookEntity bookEntity2 = new BookEntity(null, "Book B", "Author X", LocalDate.of(2025, 1, 1));
        BookEntity bookEntity3 = new BookEntity(null, "Book C", "Author Y", LocalDate.of(2025, 1, 1));
        bookRepository.saveAll(List.of(bookEntity1, bookEntity2, bookEntity3));

        mockMvc.perform(get("/api/v1/books")
                        .param("author", "Author S"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveBook_withValidateFutureDate_shouldReturn400() throws Exception {
        BookRequest request = new BookRequest();
        request.setTitle("Book R");
        request.setAuthor("Author R");
        request.setPublishedDate(LocalDate.now().plusDays(1));

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveBook_withValidateDateLess1000_shouldReturn400() throws Exception {
        BookRequest request = new BookRequest();
        request.setTitle("Book R");
        request.setAuthor("Author R");
        request.setPublishedDate(LocalDate.now().minusYears(2000));

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveBook_withInvalidTitle_shouldReturn400() throws Exception {
        BookRequest request = new BookRequest();
        request.setTitle("");
        request.setAuthor("Author Z");
        request.setPublishedDate(LocalDate.of(2025, 1, 1));

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testSaveBook_withInvalidAuthor_shouldReturn400() throws Exception {
        BookRequest request = new BookRequest();
        request.setTitle("Book Z");
        request.setAuthor("");
        request.setPublishedDate(LocalDate.of(2025, 1, 1));

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }
}