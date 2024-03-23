package com.backend.country.repository;

import com.backend.country.model.CountySuggestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountyRepository extends JpaRepository<CountySuggestion, Long> {

  /**
   * Search for counties by name containing and state containing, ordered by FIPS code in ascending order.
   * Results are prioritized based on exact matches of name and state.
   *
   * @param name  The county name or fragment.
   * @param state The state name or fragment.
   * @return A list of county projections containing the search results.
   */
  @Query("SELECT c FROM CountySuggestion c " +
          "WHERE lower(c.name) = lower(:name) AND lower(c.state) = lower(:state) " +
          " OR lower(c.name) = lower(:name) " +
          " OR lower(c.state) = lower(:state) " +
          " OR lower(c.name) LIKE lower(concat('%', :name, '%')) " +
          " OR lower(c.state) LIKE lower(concat('%', :state, '%')) " +
          "ORDER BY CASE " +
          " WHEN lower(c.name) = lower(:name) AND lower(c.state) = lower(:state) THEN 0 " +
          " WHEN lower(c.name) = lower(:name) THEN 1 " +
          " WHEN lower(c.state) = lower(:state) THEN 2 " +
          " ELSE 3 " +
          "END, c.fips ASC")
  List<CountyProjection> findByNameContainingIgnoreCaseAndStateContainingIgnoreCaseOrderByFipsAsc(String name, String state);

  /**
   * Search for counties by exact name and state, or partial matches of name and state,
   * ordered by FIPS code in ascending order.
   *
   * @param name  The county name or fragment.
   * @param state The state name or fragment.
   * @return A list of county projections containing the search results.
   */
  @Query("SELECT c FROM CountySuggestion c " +
          "WHERE lower(c.name) = lower(:name) AND lower(c.state) = lower(:state) " +
          " OR lower(c.name) = lower(:name) AND lower(c.state) LIKE lower(concat('%', :state, '%')) " +
          " OR lower(c.state) = lower(:state) AND lower(c.name) LIKE lower(concat('%', :name, '%')) " +
          " OR lower(c.name) LIKE lower(concat('%', :name, '%')) AND lower(c.state) LIKE lower(concat('%', :state, '%')) " +
          "ORDER BY CASE " +
          " WHEN lower(c.name) = lower(:name) AND lower(c.state) = lower(:state) THEN 0 " +
          " WHEN lower(c.name) = lower(:name) THEN 1 " +
          " WHEN lower(c.state) = lower(:state) THEN 2 " +
          " ELSE 3 " +
          "END, c.fips ASC")
  List<CountyProjection> findByNameAndStateContainingIgnoreCaseOrderByFipsAsc(String name, String state);

}
