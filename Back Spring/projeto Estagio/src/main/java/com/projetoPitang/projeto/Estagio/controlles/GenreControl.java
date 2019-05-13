package com.projetoPitang.projeto.Estagio.controlles;

import com.projetoPitang.projeto.Estagio.Models.Genres;
import com.projetoPitang.projeto.Estagio.repository.GenreRepository;
import com.projetoPitang.projeto.Estagio.repository.MovieRepository;
import com.projetoPitang.projeto.Estagio.services.GenreService;
import com.projetoPitang.projeto.Estagio.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/genres")
@CrossOrigin(origins = "http://localhost:4200")
public class GenreControl {

    @Autowired
    private GenreService genreService;

    @Autowired
    private GenreRepository genreRepository;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllGenres() {
        List<Genres> genresList = genreRepository.findAll();

        return new ResponseEntity<>(genresList, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> postAllGenres() {
        List<Genres> genresList = genreService.getMovieGenres();
        for (Genres genres : genresList) {
            genreRepository.save(genres);
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                return new ResponseEntity<>(e.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteGenre(@PathVariable Integer id) {

        genreRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

}
