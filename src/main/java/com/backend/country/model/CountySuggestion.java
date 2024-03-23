package com.backend.country.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Entity class representing a county suggestion.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CountySuggestion {

  /**
   * The FIPS code of the county.
   */
  @Id
  private String fips;

  /**
   * The state abbreviation of the county.
   */
  private String state;

  /**
   * The name of the county.
   */
  private String name;

}
