package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.User;

import java.util.Objects;

public class UserDTO extends RepresentationModel<DeviceDTO> {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    private Long id;




    private User user;
    public UserDTO() {
    }

    //    public DeviceDTO(String description, String address, Double maxEnergyH) {
//        this.description=description;
//        this.address=address;
//        this.maxEnergyH=maxEnergyH;
//    }
    public UserDTO(Long id) {
        this.id=id;
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return id == userDTO.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
