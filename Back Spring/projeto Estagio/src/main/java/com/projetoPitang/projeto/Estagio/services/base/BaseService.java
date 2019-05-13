package com.projetoPitang.projeto.Estagio.services.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.projetoPitang.projeto.Estagio.utils.ObjectDiscover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BaseService {

    public String URL = "https://api.themoviedb.org/3/";
    public String APIKEY = "9473992455955d1a70389d65719214f2";

    public RestTemplate restTemplate;
    public ObjectMapper objectMapper;
    public ObjectDiscover objectDiscover;

    @Autowired
    public BaseService() {
        this.restTemplate = new RestTemplate();
        this.objectMapper = new ObjectMapper();
        this.objectDiscover = new ObjectDiscover();
    }

}
