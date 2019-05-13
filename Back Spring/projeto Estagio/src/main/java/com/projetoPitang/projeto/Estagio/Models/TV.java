package com.projetoPitang.projeto.Estagio.Models;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "tb_tv")
public class TV {


    @Id
    private Integer id;

    @Column(name = "cl_name")
    private String name;

    @Column(name = "cl_original_name")
    private String original_name;

    @Column(name = "cl_overview", length = 1000)
    private String overview;

    @Column(name = "cl_original_language")
    private String original_language;


    @Column(name = "cl_first_air_date")
    private String first_air_date;

    @Column(name = "cl_number_of_seasons")
    private Integer number_of_seasons;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "tv_genre",
            joinColumns = @JoinColumn(name = "tv_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genres> genres;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "tv_id")
    private List<Cast> cast;

    @Column(name = "cl_origin_country")
    private String originCountry;

    @Column(name = "cl_poster_path")
    private String poster_path;

    @Column(name = "cl_popularity")
    private Double popularity;

    @Column(name = "cl_backdrop_path")
    private String backdrop_path;

    @Column(name = "cl_episode_run_time")
    private Integer episode_runtime;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TV tv = (TV) o;
        return Objects.equals(id, tv.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public Integer getNumber_of_seasons() {
        return number_of_seasons;
    }

    public void setNumber_of_seasons(Integer number_of_seasons) {
        this.number_of_seasons = number_of_seasons;
    }

    public List<Genres> getGenres() {
        return genres;
    }

    public void setGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> casts) {
        this.cast = casts;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public Double getPopularity() {
        return popularity;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public Integer getEpisode_runtime() {
        return episode_runtime;
    }

    public void setEpisode_runtime(Integer episode_runtime) {
        this.episode_runtime = episode_runtime;
    }
}
