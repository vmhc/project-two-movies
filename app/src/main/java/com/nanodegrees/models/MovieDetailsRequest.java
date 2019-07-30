package com.nanodegrees.models;

import java.util.List;

public class MovieDetailsRequest {

    private Boolean adult;
    private String backdrop_path;
    private BelongsToCollection belongs_to_collection;
    private Double budget;
    private List<Genre> genres;
    private String homepage;
    private Integer id;
    private String imdb_id;
    private String original_language;
    private String original_title;
    private String overview;
    private Float popularity;
    private String poster_path;
    private List<ProductionCompanies> production_companies;
    private List<ProductionCountries> production_countries;
    private String release_date;
    private Double revenue;
    private Integer runtime;
    private List<SpokenLanguages> spoken_languages;
    private String status;
    private String tagline;
    private String title;
    private Boolean video;
    private Float vote_average;
    private Integer vote_count;

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public Float getVote_average() {
        return vote_average;
    }
}
