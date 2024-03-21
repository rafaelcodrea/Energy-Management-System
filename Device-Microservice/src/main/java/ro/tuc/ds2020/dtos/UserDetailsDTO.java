package ro.tuc.ds2020.dtos;


import ro.tuc.ds2020.entities.User;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class UserDetailsDTO {


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NotNull
    private Long id;


    public UserDetailsDTO() {
    }

    public UserDetailsDTO( Long id) {
        this.id=id;
    }

//    public UserDetailsDTO(Long id) {
//        this.id = id;
//        this.address=address;
//        this.description=description;
//        this.maxEnergyH=maxEnergyH;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDetailsDTO that = (UserDetailsDTO) o;
        return id == that.id &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
