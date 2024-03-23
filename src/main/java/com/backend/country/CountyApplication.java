package com.backend.country;

import com.backend.country.model.CountySuggestion;
import com.backend.country.repository.CountyRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * The main application class for County API.
 */
@SpringBootApplication
public class CountyApplication {
  private static final Logger logger = LoggerFactory.getLogger(CountyApplication.class);

  @Autowired
  CountyRepository repository;
  /**
   * Main method to start the County API application.
   *
   * @param args The command-line arguments.
   */
  public static void main(String[] args) {
    SpringApplication.run(CountyApplication.class, args);
  }

  /**
   * Bean to load data from data.json file into the database.
   *
   * @return A CommandLineRunner to execute the data loading process.
   */
  @Bean
  public CommandLineRunner loadData(CountyRepository repository) {
    return args -> {
      // Load data from data.json and insert it into the database
      ObjectMapper mapper = new ObjectMapper();
      TypeReference<List<CountySuggestion>> typeReference = new TypeReference<List<CountySuggestion>>() {
      };
      InputStream inputStream = TypeReference.class.getResourceAsStream("/data.json");
      try {
        List<CountySuggestion> counties = mapper.readValue(inputStream, typeReference);
        repository.saveAll(counties);
        logger.info("Data Loaded Successfully");
      } catch (IOException e) {
        logger.info("Unable to load data: {} ", e.getMessage());
      }
    };
  }
}
