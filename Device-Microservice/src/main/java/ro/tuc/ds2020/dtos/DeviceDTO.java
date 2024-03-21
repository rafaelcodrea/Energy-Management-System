package ro.tuc.ds2020.dtos;

import org.springframework.hateoas.RepresentationModel;
import ro.tuc.ds2020.entities.User;

import java.util.Objects;

public class DeviceDTO extends RepresentationModel<DeviceDTO> {
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getMaxEnergyH() {
        return maxEnergyH;
    }

    public void setMaxEnergyH(Double maxEnergyH) {
        this.maxEnergyH = maxEnergyH;
    }

    private Long id;
    private String description;
    private String address;
    private Double maxEnergyH;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;
    public DeviceDTO() {
    }

//    public DeviceDTO(String description, String address, Double maxEnergyH) {
//        this.description=description;
//        this.address=address;
//        this.maxEnergyH=maxEnergyH;
//    }
    public DeviceDTO(String description, String address, Double maxEnergyH, User user) {
        this.description=description;
        this.address=address;
        this.maxEnergyH=maxEnergyH;
        this.user=user;
    }
    public DeviceDTO(String description, String address, Double maxEnergyH) {
        this.description=description;
        this.address=address;
        this.maxEnergyH=maxEnergyH;
    }
    public DeviceDTO(String description, String address, Double maxEnergyH, Long userId) {
        this.description=description;
        this.address=address;
        this.maxEnergyH=maxEnergyH;
        this.user= new User(userId);
    }




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDTO deviceDTO = (DeviceDTO) o;
        return id == deviceDTO.id &&
                Objects.equals(address, deviceDTO.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, id);
    }
}
