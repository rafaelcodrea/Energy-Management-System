package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Device;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    /**
     * Example: JPA generate Query by Field
     */
   // List<Device> findByName(String name);

    /**
     * Example: Write Custom Query
     */
//    @Query(value = "SELECT p " +
//            "FROM Person p " +
//            "WHERE p.name = :name " +
//            "AND p.age >= 60  ")
//    Optional<Person> findSeniorsByName(@Param("name") String name);


//    @Query(value = "SELECT Device d FROM Device d WHERE d.user.id= :id " )
//    List<Device> findDevicesForUser(@Param("id")Long id);
    @Query("SELECT d FROM Device d WHERE d.user.id = :id")
    List<Device> findDevicesForUser(@Param("id") Long id);


}
