package com.metamorphosis.kafkajsonsolution.services;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Configuration
public class CourseService {
    @Bean
    public Supplier<schemas.Course> producer() {
        return () -> schemas.Course.newBuilder().setId(1).setName("djdj").setDescription("kdmsqk").build();
    }
    @Bean
    public Consumer<schemas.Course> consumer() {
        return message -> System.out.println("received " + message);
    }}
