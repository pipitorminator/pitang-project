package com.projetoPitang.projeto.Estagio.services;

import com.projetoPitang.projeto.Estagio.Models.Cast;
import com.projetoPitang.projeto.Estagio.Models.Movie;
import com.projetoPitang.projeto.Estagio.Models.Person;
import com.projetoPitang.projeto.Estagio.dtos.CreditsDTO;
import com.projetoPitang.projeto.Estagio.repository.MovieRepository;
import com.projetoPitang.projeto.Estagio.services.base.BaseService;
import com.projetoPitang.projeto.Estagio.utils.ObjectDiscover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MovieService extends BaseService {

    @Autowired
    private MovieRepository movieRepository;

    private Movie movie;


    @Autowired
    public MovieService() {
        super();
    }

    public ObjectDiscover getDiscover() {
        String json = restTemplate.getForObject(this.URL + "/discover/movie?api_key=" + APIKEY, String.class);

        try {
            objectDiscover = objectMapper.readValue(json, ObjectDiscover.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return objectDiscover;
    }

    public Movie getbyId(Integer id) {
        movie = restTemplate.getForObject(this.URL + "/movie/" + id + "?api_key=" + APIKEY, Movie.class);
        CreditsDTO creditsDTO = restTemplate.getForObject(this.URL + "/movie/" + id + "/credits?api_key=" + this.APIKEY, CreditsDTO.class);
        movie.setCast(new ArrayList<>());
        List<Integer> idsCast = new ArrayList<>();
        List<Integer> idsCrew = new ArrayList<>();

        for (int i = 0; i < 15 && i < creditsDTO.getCast().size(); i++) {
            int person_id = creditsDTO.getCast().get(i).getId();
            Person person = restTemplate.getForObject(this.URL + "/person/" + person_id + "?api_key=" + this.APIKEY, Person.class);
            Cast cast = new Cast();

            person.setHeight(1.70);
            person.setActual_country(person.getPlace_of_birth());

            cast.setPerson(person);
            cast.setJob("actor");
            cast.setDepartment("actor");
            cast.setCharacter(creditsDTO.getCast().get(i).getCharacter());

            movie.getCast().add(cast);

            idsCast.add(person_id);

            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                return null;
            }

        }
        for (int i = 0; i < 10 && i < creditsDTO.getCrew().size(); i++) {

            int person_id = creditsDTO.getCrew().get(i).getId();

            if (idsCast.contains(person_id)) {
                //person being part of cast and crew
                for (int x = 0; x < movie.getCast().size(); x++) {
                    if (movie.getCast().get(x).getPerson().getId().equals(person_id)) {
                        movie.getCast().get(x).setJob(creditsDTO.getCrew().get(i).getJob() + " + Actor");
                        movie.getCast().get(x).setDepartment(creditsDTO.getCrew().get(i).getDepartment() + " + Actor");
                        break;
                    }

                }
            } else if (idsCrew.contains(person_id)) {
                //person appear twice in crew
                for (int x = 0; x < movie.getCast().size(); x++) {
                    if (movie.getCast().get(x).getPerson().getId().equals(person_id)) {
                        movie.getCast().get(x).setDepartment(creditsDTO.getCrew().get(i).getDepartment() + " + "
                                + movie.getCast().get(x).getDepartment());
                        movie.getCast().get(x).setJob(creditsDTO.getCrew().get(i).getJob() + " + " + movie.getCast().get(x).getJob());
                        break;
                    }
                }


            } else {
                Cast cast = new Cast();
                Person person = restTemplate.getForObject(this.URL + "/person/" + person_id + "?api_key=" + this.APIKEY, Person.class);

                person.setHeight(1.70);
                person.setActual_country(person.getPlace_of_birth());

                cast.setPerson(person);
                cast.setJob(creditsDTO.getCrew().get(i).getJob());
                cast.setDepartment(creditsDTO.getCrew().get(i).getDepartment());
                cast.setCharacter(null);
                movie.getCast().add(cast);

                idsCrew.add(person_id);

            }


            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                return null;
            }
        }

        return movie;

    }

    public List<Integer> getMovieIdList(String page) {
        HashMap json = restTemplate.getForObject(this.URL + "/discover/movie?page=" + page + "&api_key=" + APIKEY, HashMap.class);
        List<Integer> ids = new ArrayList<>();

        for (Object o : (ArrayList) json.get("results")) {
            HashMap hashMap = (HashMap) o;

            ids.add((Integer) hashMap.get("id"));

        }

        return ids;
    }

    public MovieRepository getMovieRepository() {
        return movieRepository;
    }

    public void setMovieRepository(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
}
