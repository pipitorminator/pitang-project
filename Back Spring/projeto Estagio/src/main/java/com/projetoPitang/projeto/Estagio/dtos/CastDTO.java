package com.projetoPitang.projeto.Estagio.dtos;

import com.projetoPitang.projeto.Estagio.Models.Person;

import javax.persistence.Column;
import javax.persistence.Id;

public class CastDTO {


    private Integer id;
    private String character;
    private String department;
    private String job;
    private Person person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCharacter() {
        return character;
    }

    public void setCharacter(String character) {
        this.character = character;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
