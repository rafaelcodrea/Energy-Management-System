package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.ROLE;

import java.util.Objects;
import java.util.UUID;

public class PersonDTO {
    private Long id;
    private String name;
    private String password;
    private ROLE role;

    private String jwtTkn;


    public PersonDTO(Long id, String name, String password, ROLE role, String jwtTkn) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.role = role;
        this.jwtTkn = jwtTkn;
    }

    public String getJwtTkn() {
        return jwtTkn;
    }

    public void setJwtTkn(String jwtTkn) {
        this.jwtTkn = jwtTkn;
    }

    public PersonDTO() {
    }

    public PersonDTO(String name, String password, ROLE role) {
        this.name = name;
        this.role=role;
        this.password=password;
    }

    public PersonDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setPassword(String password){this.password=password;}
    public void setRole(ROLE role){this.role=role;}
    public ROLE getRole(){return this.role;}
    public String getPassword(){return this.password;}


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDTO personDTO = (PersonDTO) o;
        return id == personDTO.id &&
                Objects.equals(name, personDTO.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }
}
