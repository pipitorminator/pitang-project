package com.projetoPitang.projeto.Estagio.controlles;


import com.projetoPitang.projeto.Estagio.Models.Movie;
import com.projetoPitang.projeto.Estagio.repository.MovieRepository;
import com.projetoPitang.projeto.Estagio.services.GenreService;
import com.projetoPitang.projeto.Estagio.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/movies")
@CrossOrigin(origins = "http://localhost:4200")
public class MovieControl {


    @Autowired
    private MovieService movieService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(Pageable pageable) {
        Page<Movie> moviePage = movieService.getMovieRepository().findAll(pageable);

        return new ResponseEntity<>(moviePage, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {

        Movie movie = movieService.getMovieRepository().findByIdIn(id);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateMovie(@PathVariable Integer id, @RequestBody Movie movie) {

        movie.setId(id);

        movieService.getMovieRepository().save(movie);

        return new ResponseEntity<>(movie, HttpStatus.OK);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> importMovieFromApi(@PathVariable Integer id) {
        Movie movie = movieService.getbyId(id);

        movieService.getMovieRepository().save(movie);

        return new ResponseEntity<>(movie, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/povoar/{page}", method = RequestMethod.POST)
    public ResponseEntity<?> povoarBanco(@PathVariable String page) {
        Movie movie;
        List<Integer> ids = movieService.getMovieIdList(page);

        for (int i = 0; i < ids.size(); i++) {
            movie = movieService.getbyId(ids.get(i));
            movieService.getMovieRepository().save(movie);
        }

        return new ResponseEntity<>(ids, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteMovie(@PathVariable Integer id) {
        movieService.getMovieRepository().deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    public MovieService getMovieService() {
        return movieService;
    }

    public void setMovieService(MovieService movieService) {
        this.movieService = movieService;
    }
}
