package com.backend.country.repository;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Projection interface for CountySuggestion entity, specifying the properties to be included in the JSON response.
 */
@JsonPropertyOrder({"fips", "state", "name"})
public interface CountyProjection {
    /**
     * Get the FIPS code of the county.
     *
     * @return The FIPS code.
     */
    String getFips();

    /**
     * Get the state abbreviation of the county.
     *
     * @return The state abbreviation.
     */
    String getState();

    /**
     * Get the name of the county.
     *
     * @return The county name.
     */
    String getName();
}
