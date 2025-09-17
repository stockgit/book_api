/*
 *
 *  * Copyright © 2025 Satib Software Co., Ltd.
 *  * All rights reserved.
 *  *
 *  * This source code is the property of Satib Software and may not be
 *  * reproduced or distributed without written permission.
 *
 *
 */

package com.accend.book.service;

import com.accend.book.model.BookEntity;
import com.accend.book.repository.BookJdbcRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

@Service
public class BatchService {

    private final BookJdbcRepository bookRepository;

    Logger logger = Logger.getLogger(BatchService.class.getName());

    private final int totalRecords = 100_000;
    private final int batchSize = 10_000;
    private final int threadPoolSize = 15;

    public BatchService(BookJdbcRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public int countAllRecords() {
        return bookRepository.countAllRecords();
    }

    public void insertExampleData() {
        logger.info("Start batch insert.");
        ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
        for (int i = 0; i < totalRecords; i += batchSize) {
            int start = i;
            int end = Math.min(i + batchSize, totalRecords);
            executor.submit(() -> {
                List<BookEntity> batch = new ArrayList<>();
                for (int j = start + 1; j <= end; j++) {
                    BookEntity book = new BookEntity();
                    book.setAuthor("Author " + j);
                    book.setTitle("Title " + j);
                    book.setPublishedDate(LocalDate.now());
                    batch.add(book);
                }
                bookRepository.batchInsert(batch);
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.info("Batch insert interrupted");
        }
        logger.info("Complete batch inserted.");
    }
}
