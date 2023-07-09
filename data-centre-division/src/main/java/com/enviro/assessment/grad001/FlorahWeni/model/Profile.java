package com.enviro.assessment.grad001.FlorahWeni.model;

// import java.net.URI;
import java.net.URL;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="acc_profiles")
public class Profile {
    @Id
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private URL httpImgLink;

    public Profile(){}

    public Profile(String name, String surname, URL httpImgLink,int id) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.httpImgLink = httpImgLink;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public URL getHttpImgLink() {
        return httpImgLink;
    }
    public void setHttpImgLink(URL httpImgLink) {
        this.httpImgLink = httpImgLink;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Profile))
            return false;
        Profile profile = (Profile) o;
        return Objects.equals(this.name, profile.name) && Objects.equals(this.surname, profile.surname)
                && Objects.equals(this.httpImgLink, profile.httpImgLink);
    }

    @Override
    public int hashCode() {
        return Objects.hash( this.name,this.surname, this.httpImgLink);
    }

    @Override
    public String toString() {
        return "Profile{" + "name=" + this.name + ", surname='" + this.surname + '\'' +
                ", httpImgLink='" + this.httpImgLink + '\'' + '}';
    }

}
