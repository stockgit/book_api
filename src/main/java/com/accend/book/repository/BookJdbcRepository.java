package com.accend.book.repository;

import com.accend.book.model.BookEntity;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class BookJdbcRepository {
    private final JdbcTemplate jdbcTemplate;

    public BookJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int countAllRecords() {
        String sql = "SELECT COUNT(*) FROM books";
        return jdbcTemplate.queryForObject(sql, Integer.class);
    }

    public void batchInsert(List<BookEntity> entities) {
        String sql = "INSERT INTO books (title, author, published_date) VALUES (?, ?, ?)";
        jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setString(1, entities.get(i).getTitle());
                ps.setString(2, entities.get(i).getAuthor());
                ps.setDate(3, Date.valueOf(entities.get(i).getPublishedDate()));
            }

            @Override
            public int getBatchSize() {
                return entities.size();
            }
        });
    }
}
