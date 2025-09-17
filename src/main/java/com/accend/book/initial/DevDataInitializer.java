package com.accend.book.initial;

import com.accend.book.service.BatchService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev") // Only active when spring.profiles.active=dev
public class DevDataInitializer implements CommandLineRunner {
    private final BatchService batchService;

    public DevDataInitializer(BatchService batchService) {
        this.batchService = batchService;
    }

    @Override
    public void run(String... args) {
        if (batchService.countAllRecords() == 0) {
            batchService.insertExampleData();
        }
    }
}

