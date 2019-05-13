package com.projetoPitang.projeto.Estagio.services;

import com.projetoPitang.projeto.Estagio.Models.Cast;
import com.projetoPitang.projeto.Estagio.Models.Person;
import com.projetoPitang.projeto.Estagio.Models.TV;
import com.projetoPitang.projeto.Estagio.dtos.CreditsDTO;
import com.projetoPitang.projeto.Estagio.dtos.TvDTO;
import com.projetoPitang.projeto.Estagio.services.base.BaseService;
import com.projetoPitang.projeto.Estagio.utils.ObjectDiscover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TvService extends BaseService {

    private TV tv;

    @Autowired
    public TvService() {
        super();
    }

    public ObjectDiscover getDiscover() throws IOException {
        String json = restTemplate.getForObject(this.URL + "/discover/tv?api_key=" + APIKEY, String.class);
        objectDiscover = objectMapper.readValue(json, ObjectDiscover.class);

        return objectDiscover;
    }

    public TV getbyId(Integer id) {
        TvDTO tvDTO = restTemplate.getForObject(this.URL + "tv/" + id + "?api_key=" + this.APIKEY, TvDTO.class);
        CreditsDTO creditsDTO = restTemplate.getForObject(this.URL + "/tv/" + id + "/credits?api_key=" + this.APIKEY, CreditsDTO.class);


        List<Integer> idsCast = new ArrayList<>();
        List<Integer> idsCrew = new ArrayList<>();

        tv = new TV();
        tv.setId(tvDTO.getId());
        tv.setBackdrop_path(tvDTO.getBackdrop_path());
        tv.setFirst_air_date(tvDTO.getFirst_air_date());
        tv.setGenres(tvDTO.getGenres());
        tv.setName(tvDTO.getName());
        tv.setNumber_of_seasons(tvDTO.getNumber_of_seasons());
        tv.setOriginal_language(tvDTO.getOriginal_language());
        tv.setOriginal_name(tvDTO.getOriginal_name());
        tv.setOverview(tvDTO.getOverview());
        tv.setPopularity(tvDTO.getPopularity());
        tv.setPoster_path(tvDTO.getPoster_path());
        tv.setCast(new ArrayList<>());
        if (tvDTO.getEpisode_run_time().size() > 0) {
            tv.setEpisode_runtime(tvDTO.getEpisode_run_time().get(0));
        }
        if (tvDTO.getOrigin_country().size() > 0) {
            tv.setOriginCountry(tvDTO.getOrigin_country().get(0));
        }

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

            tv.getCast().add(cast);

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
                for (int x = 0; x < tv.getCast().size(); x++) {
                    if (tv.getCast().get(x).getPerson().getId().equals(person_id)) {
                        tv.getCast().get(x).setJob(creditsDTO.getCrew().get(i).getJob() + " + Actor");
                        tv.getCast().get(x).setDepartment(creditsDTO.getCrew().get(i).getDepartment() + " + Actor");
                        break;
                    }

                }
            } else if (idsCrew.contains(person_id)) {
                //person appear twice in crew
                for (int x = 0; x < tv.getCast().size(); x++) {
                    if (tv.getCast().get(x).getPerson().getId().equals(person_id)) {
                        tv.getCast().get(x).setDepartment(creditsDTO.getCrew().get(i).getDepartment() + " + "
                                + tv.getCast().get(x).getDepartment());
                        tv.getCast().get(x).setJob(creditsDTO.getCrew().get(i).getJob() + " + " + tv.getCast().get(x).getJob());
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
                tv.getCast().add(cast);

                idsCrew.add(person_id);

            }


            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                return null;
            }
        }


        return tv;

    }

    public List<Integer> getMovieIdList(String page) {
        HashMap json = restTemplate.getForObject(this.URL + "/discover/tv?page="+ page + "&api_key=" + APIKEY, HashMap.class);
        List<Integer> ids = new ArrayList<>();

        for (Object o : (ArrayList) json.get("results")) {
            HashMap hashMap = (HashMap) o;

            ids.add((Integer) hashMap.get("id"));

        }

        return ids;

    }


}
