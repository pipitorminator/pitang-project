package com.projetoPitang.projeto.Estagio.services;

import com.projetoPitang.projeto.Estagio.Models.Genres;
import com.projetoPitang.projeto.Estagio.services.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class GenreService extends BaseService {

    private Genres genres;

    @Autowired
    public GenreService() {
        super();
    }

    public List<Genres> getMovieGenres(){
        HashMap json = restTemplate.getForObject(this.URL + "genre/movie/list?api_key=" +this.APIKEY,HashMap.class);
        List<Genres> genresList = new ArrayList<>();
        for(Object o: (ArrayList)json.get("genres")){
            Genres genresadd = new Genres();
            HashMap hashMap =(HashMap) o;
            genresadd.setId((Integer) hashMap.get("id"));
            genresadd.setName((String) hashMap.get("name"));
            genresList.add(genresadd);

        }

        return genresList;
    }
}
