package com.kodilla.imdbmovie.utellyService.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UtellyResponse {

    @JsonProperty("collection")
    private Locations locations;
}
