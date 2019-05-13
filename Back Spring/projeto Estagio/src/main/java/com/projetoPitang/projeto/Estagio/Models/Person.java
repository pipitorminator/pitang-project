package com.projetoPitang.projeto.Estagio.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_person")
public class Person {

    @Id
    private Integer id;

    @Column(name = "cl_name")
    private String name;

    @Column(name = "cl_gender")
    private Integer gender;

    @Column(name = "cl_place_of_birth")
    private String place_of_birth;

    @Column(name = "cl_height")
    private double height;

    @Column(name = "cl_actual_country")
    private String actual_country;

    @Column(name = "cl_profile_path")
    private String profile_path;

    @JsonIgnore
    @OneToMany(mappedBy = "person")
    private Set<Cast> casts;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getActual_country() {
        return actual_country;
    }

    public void setActual_country(String actual_country) {
        this.actual_country = actual_country;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public Set<Cast> getCasts() {
        return casts;
    }

    public void setCasts(Set<Cast> casts) {
        this.casts = casts;
    }


}
