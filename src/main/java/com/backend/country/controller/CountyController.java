package com.backend.country.controller;

import com.backend.country.repository.CountyProjection;
import com.backend.country.service.CountyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/counties")
public class CountyController {

  private CountyService countyService;
  @Autowired
  public void setCountyService(CountyService countyService) {
    this.countyService = countyService;
  }
  private static final Logger logger = LoggerFactory.getLogger(CountyController.class);
  /**
   * Endpoint to suggest counties based on the provided query string.
   *
   * @param q The query string containing the county name and/or state fragment.
   * @return ResponseEntity containing a list of suggested counties.
   */
  @GetMapping(value = "/suggest")
  public @ResponseBody ResponseEntity<List<CountyProjection>> search(@RequestParam @NonNull String q) {
    // Extract county name and state from the query string
    String extractedName = (q.contains(",")) ? q.substring(0, q.indexOf(',')).trim() : q.trim();
    String extractedState = (q.contains(",")) ? q.substring(q.indexOf(',') + 1).trim() : q.trim();

    // Log the user-provided query and extracted name/state
    logger.info(" The user provided query is {} and the extracted name is {} and the extracted state is {} ", q, extractedName, extractedState);

    // Search for counties based on the provided name and state
    List<CountyProjection> suggestedCounties = countyService.searchCounties(extractedName, extractedState);

    // Log the suggested counties
    logger.info(" The Suggested Counties is {} ", suggestedCounties);

    // Return the suggested counties in a ResponseEntity with HTTP status OK
    return ResponseEntity.status(HttpStatus.OK).body(suggestedCounties);
  }
}
