package com.projetoPitang.projeto.Estagio.controlles;

import com.projetoPitang.projeto.Estagio.Models.Movie;
import com.projetoPitang.projeto.Estagio.Models.Person;
import com.projetoPitang.projeto.Estagio.repository.PersonRepository;
import com.projetoPitang.projeto.Estagio.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonControl {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MovieService movieService;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(Pageable pageable) {

        Page<Person> personPage = personRepository.findAll(pageable);

        return new ResponseEntity<>(personPage, HttpStatus.OK);


    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {

        Person person = personRepository.findByIdIn(id);

        return new ResponseEntity<>(person, HttpStatus.OK);

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteById(@PathVariable Integer id) {

        personRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @RequestMapping(path = "/part{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getParticipatingMovies(@PathVariable Integer id){

        List<Integer> ids = personRepository.FindMovieByPersonId(id);

        List<Movie> movies = new ArrayList<>();

        for(Integer i: ids){
            movies.add(movieService.getMovieRepository().findByIdIn(i));
        }


        return new ResponseEntity<>(movies, HttpStatus.OK);

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updatePerson(@PathVariable Integer id, @RequestBody Person person) {

        person.setId(id);

        personRepository.save(person);

        return new ResponseEntity<>(person, HttpStatus.OK);
    }


}
