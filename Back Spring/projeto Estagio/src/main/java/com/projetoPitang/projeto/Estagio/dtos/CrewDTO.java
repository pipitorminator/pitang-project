package com.projetoPitang.projeto.Estagio.dtos;

import com.projetoPitang.projeto.Estagio.Models.Person;

public class CrewDTO {

    private Integer id;
    private String department;
    private String job;
    private Person person;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
