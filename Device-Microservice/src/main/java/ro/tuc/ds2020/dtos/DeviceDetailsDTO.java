package ro.tuc.ds2020.dtos;


import ro.tuc.ds2020.entities.User;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class DeviceDetailsDTO {

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
    @NotNull
    private String description;
    @NotNull
    private String address;
    @NotNull
    private Double maxEnergyH;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    private User user;

    public DeviceDetailsDTO() {
    }

    public DeviceDetailsDTO( String description, String address, Double maxEnergyH, User user) {
        this.description=description;
        this.address=address;
        this.maxEnergyH=maxEnergyH;
        this.user=user;
    }


    public DeviceDetailsDTO( String description, String address, Double maxEnergyH, Long userId) {
        this.description=description;
        this.address=address;
        this.maxEnergyH=maxEnergyH;
        this.user=new User(userId);
    }
//    public DeviceDetailsDTO( String description, String address, Double maxEnergyH) {
//        this.description=description;
//        this.address=address;
//        this.maxEnergyH=maxEnergyH;
//    }
    public DeviceDetailsDTO( String address, String description, Double maxEnergyH) {
        this.address=address;
        this.description=description;
        this.maxEnergyH=maxEnergyH;
    }

    public DeviceDetailsDTO(Long id, String address, String description, Double maxEnergyH) {
        this.id = id;
        this.address=address;
        this.description=description;
        this.maxEnergyH=maxEnergyH;
    }
    public DeviceDetailsDTO(Long id, String address, String description, Double maxEnergyH, User user) {
        this.id = id;
        this.address=address;
        this.description=description;
        this.maxEnergyH=maxEnergyH;
        this.user=user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeviceDetailsDTO that = (DeviceDetailsDTO) o;
        return id == that.id &&
                Objects.equals(address, that.getAddress()) &&
                Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, id, description,maxEnergyH);
    }
}
