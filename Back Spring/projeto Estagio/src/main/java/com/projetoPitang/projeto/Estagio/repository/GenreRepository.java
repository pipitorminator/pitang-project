package com.projetoPitang.projeto.Estagio.repository;

import com.projetoPitang.projeto.Estagio.Models.Genres;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genres, Integer> {

}
