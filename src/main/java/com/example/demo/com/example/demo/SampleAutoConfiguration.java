package com.example.demo.com.example.demo;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.library.LibraryService;
import com.example.library.SubjectService;

@Configuration
@ConditionalOnClass(LibraryService.class)
public class SampleAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public LibraryService sampleService() {
        return new LibraryService();
    }
    
    @Bean
    @ConditionalOnMissingBean
    public SubjectService subjectService() {
        return new SubjectService();
    }

    
 
}
