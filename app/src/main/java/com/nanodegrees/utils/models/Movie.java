package com.nanodegrees.utils.models;

public class Movie {

    private Integer vote_count;
    private Integer id;
    private Boolean video;
    private Float vote_average;
    private String title;
    private Float popularity;
    private String poster_path;
    private String original_language;
    private String original_title;

    private Integer[] genre_ids;

    private String backdrop_path;
    private Boolean adult;
    private String overview;
    private String release_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoster_path() {
        return poster_path;
    }
}
