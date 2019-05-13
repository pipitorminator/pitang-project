package com.projetoPitang.projeto.Estagio.repository;

import com.projetoPitang.projeto.Estagio.Models.Movie;
import com.projetoPitang.projeto.Estagio.Models.TV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TvRepository extends JpaRepository<TV, Integer> {

    public abstract TV findByIdIn(Integer... ids);
}
