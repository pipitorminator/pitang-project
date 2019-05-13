package com.projetoPitang.projeto.Estagio.dtos;

import java.util.List;

public class CreditsDTO {

    public Integer id;
    public List<CastDTO> cast;
    public List<CrewDTO> crew;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CastDTO> getCast() {
        return cast;
    }

    public void setCast(List<CastDTO> cast) {
        this.cast = cast;
    }

    public List<CrewDTO> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewDTO> crew) {
        this.crew = crew;
    }
}
