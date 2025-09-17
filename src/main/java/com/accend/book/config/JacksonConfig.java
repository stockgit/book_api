package com.accend.book.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.chrono.ThaiBuddhistChronology;
import java.time.format.DateTimeFormatter;

@Configuration
public class JacksonConfig {

    private final AppConfig appConfig;

    public JacksonConfig(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer customizer() {
        return builder -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(appConfig.getDataFormat()).withChronology(ThaiBuddhistChronology.INSTANCE);
            builder.serializers(new LocalDateSerializer(formatter));
            builder.deserializers(new LocalDateDeserializer(DateTimeFormatter.ofPattern(appConfig.getDataFormat())));
        };
    }
}
