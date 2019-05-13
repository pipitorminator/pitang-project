package com.projetoPitang.projeto.Estagio.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.implementation.bind.annotation.IgnoreForBinding;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "tb_genres")
public class Genres {

    @Id
    private Integer id;

    @Column(name = "gen_cl_name")
    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movie;

    @JsonIgnore
    @ManyToMany(mappedBy = "genres")
    private Set<TV> tv;

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

    public Set<Movie> getMovie() {
        return movie;
    }

    public void setMovie(Set<Movie> movie) {
        this.movie = movie;
    }
}
