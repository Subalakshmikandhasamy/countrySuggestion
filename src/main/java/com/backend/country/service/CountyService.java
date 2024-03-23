package com.backend.country.service;

import com.backend.country.repository.CountyProjection;
import com.backend.country.repository.CountyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountyService {
  private CountyRepository countyRepository;
  @Autowired
  public void setCountyRepository(CountyRepository countyRepository) {
    this.countyRepository = countyRepository;
  }
  /**
   * Search for counties based on the provided name and state.
   * If the name and state are equal, perform a search by name containing and state containing.
   * Otherwise, perform a search by exact name and state.
   * Results are ordered by FIPS code in ascending order and limited to 5.
   *
   * @param name  The county name or fragment.
   * @param state The state name or fragment.
   * @return A list of county projections containing the search results.
   */
  public List<CountyProjection> searchCounties(String name, String state) {
    // Perform a search based on the provided name and state
    List<CountyProjection> searchResult = countyRepository.findByNameContainingIgnoreCaseAndStateContainingIgnoreCaseOrderByFipsAsc(name, state);

    // Limit the number of results to 5
    return searchResult.subList(0, Math.min(searchResult.size(), 5));
  }
}