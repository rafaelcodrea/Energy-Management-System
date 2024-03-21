package ro.tuc.ds2020.dtos;

import ro.tuc.ds2020.dtos.validators.annotation.AgeLimit;
import ro.tuc.ds2020.entities.ROLE;

import javax.validation.constraints.NotNull;
import java.util.Objects;
import java.util.UUID;

public class PersonDetailsDTO {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private ROLE role;
    @NotNull
    private String password;


    public PersonDetailsDTO() {
    }

    public PersonDetailsDTO( String name, Long id, ROLE role, String password) {
        this.name = name;
        this.id = id;
        this.role = role;
        this.password=password;
    }
    public PersonDetailsDTO( String name, ROLE role, String password) {
        this.name = name;
        this.role = role;
        this.password=password;
    }

    public PersonDetailsDTO(Long id, String name, ROLE role, String password) {
        this.id = id;
        this.name = name;
        this.password=password;
        this.role=role;
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

    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
    public void setRole(ROLE role){this.role=role;}
    public ROLE getRole(){return this.role;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDetailsDTO that = (PersonDetailsDTO) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, role,password);
    }
}
