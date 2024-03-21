package ro.tuc.ds2020.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name="id", nullable=false)
    private Long id;
    public Long getId() {
        return id;
    }
    public User(){}
    public User(long id){
        this.id=id;}

    public void setId(Long id) {
        this.id = id;
    }

//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getAddress() {
//        return address;
//    }

//    public void setAddress(String address) {
//        this.address = address;
//    }
//
//    public Double getMaxEnergyH() {
//        return maxEnergyH;
//    }
//
//    public void setMaxEnergyH(Double maxEnergyH) {
//        this.maxEnergyH = maxEnergyH;
//    }
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "description", nullable = false)
//    private String description;
//
//    @Column(name = "address", nullable = false)
//    private String address;  // This can be UserRole.ADMIN or UserRole.CLIENT
//
//    @Column(name = "maxEnergyH", nullable = false)
//    private Double maxEnergyH;
//
//    public Device() {
//    }
//
//    public Device(Long id, String description, String address, Double maxEnergyH) {
//        this.id=id;
//        this.description=description;
//        this.address=address;
//        this.maxEnergyH=maxEnergyH;
//    }
//
//    public Device(String address, String description, Double maxEnergyH) {
//        this.address=address;
//        this.description=description;
//        this.maxEnergyH=maxEnergyH;
//    }

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public ROLE getRole() {
//        return role;
//    }
//
//    public void setRole(ROLE role) {
//        this.role = role;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
}
