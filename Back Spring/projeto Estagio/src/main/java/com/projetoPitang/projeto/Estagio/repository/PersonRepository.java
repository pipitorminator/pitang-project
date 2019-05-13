package com.projetoPitang.projeto.Estagio.repository;

import com.projetoPitang.projeto.Estagio.Models.Movie;
import com.projetoPitang.projeto.Estagio.Models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

    public abstract Person findByIdIn(Integer... ids);

    @Query(nativeQuery = true,name = "FindMovieByPersonId", value = "SELECT m.id from tb_cast c join tb_movie m on c.person_id=:id and c.movie_id = m.id")
    public abstract List<Integer> FindMovieByPersonId(@Param("id") Integer id);

    @Query(nativeQuery = true, value = "SELECT id FROM tb_cast where person_id=:id")
    public abstract List<Integer> findCastIdByPersonId(@Param("id") Integer id);

}
