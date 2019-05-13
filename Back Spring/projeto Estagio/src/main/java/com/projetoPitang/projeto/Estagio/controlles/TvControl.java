package com.projetoPitang.projeto.Estagio.controlles;

import com.projetoPitang.projeto.Estagio.Models.Movie;
import com.projetoPitang.projeto.Estagio.Models.TV;
import com.projetoPitang.projeto.Estagio.repository.TvRepository;
import com.projetoPitang.projeto.Estagio.services.TvService;
import com.projetoPitang.projeto.Estagio.utils.ObjectDiscover;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/tv")
@CrossOrigin(origins = "http://localhost:4200")
public class TvControl {

    @Autowired
    private TvService tvService;

    @Autowired
    private TvRepository tvRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAll(Pageable pageable) {

        Page<TV> tvPage = tvRepository.findAll(pageable);

        return new ResponseEntity<>(tvPage, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}",method = RequestMethod.GET)
    public ResponseEntity<?> getById(@PathVariable Integer id) {

        TV tv = tvRepository.findByIdIn(id);

        return new ResponseEntity<>(tv, HttpStatus.OK);
    }

    @RequestMapping(path = "/discover", method = RequestMethod.GET)
    public ResponseEntity<?> getAllDiscover() {
        try {
            ObjectDiscover objectDiscover = tvService.getDiscover();

            return new ResponseEntity<>(objectDiscover, HttpStatus.OK);
        } catch (IOException e) {

            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<?> importTvFromApi(@PathVariable Integer id) {
        TV tv = tvService.getbyId(id);

        tvRepository.save(tv);

        return new ResponseEntity<>(tv, HttpStatus.CREATED);

    }

    @RequestMapping(path = "/povoar/{page}", method = RequestMethod.POST)
    public ResponseEntity<?> povoarBanco(@PathVariable String page) {
        TV tv;
        List<Integer> ids = tvService.getMovieIdList(page);

        for (int i = 0; i < ids.size(); i++) {
           tv = tvService.getbyId(ids.get(i));
            tvRepository.save(tv);
        }

        return new ResponseEntity<>(ids, HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTv(@PathVariable Integer id, @RequestBody TV tv) {

        tv.setId(id);

        tvRepository.save(tv);

        return new ResponseEntity<>(tv, HttpStatus.OK);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTv(@PathVariable Integer id) {
        tvRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
