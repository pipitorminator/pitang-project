package com.projetoPitang.projeto.Estagio.utils;

import com.projetoPitang.projeto.Estagio.dtos.MovieDTO;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component
public class ObjectDiscover implements Serializable {

    private long id;
    private Integer page;
    private Integer total_pages;
    private List<Object> results;
    private Integer total_results;

    public ObjectDiscover() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(Integer total_pages) {
        this.total_pages = total_pages;
    }

    public List<Object> getResults() {
        return results;
    }

    public void setResults(List<Object> results) {
        this.results = results;
    }

    public Integer getTotal_results() {
        return total_results;
    }

    public void setTotal_results(Integer total_results) {
        this.total_results = total_results;
    }
}
