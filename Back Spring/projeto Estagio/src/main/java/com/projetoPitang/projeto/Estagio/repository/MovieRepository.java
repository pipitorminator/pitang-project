package com.projetoPitang.projeto.Estagio.repository;

import com.projetoPitang.projeto.Estagio.Models.Cast;
import com.projetoPitang.projeto.Estagio.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    public abstract Movie findByIdIn(Integer... ids);

    public abstract List<Movie> findByTitleStartingWithIgnoreCase(String title);


}
