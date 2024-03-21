package ro.tuc.ds2020.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.tuc.ds2020.entities.Person;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PersonRepository extends JpaRepository<Person, Long> {

    /**
     * Example: JPA generate Query by Field
     */
    Optional<Person> findByName(String name);
    //Person findByUsernameAndPassword(String username, String password);

    /**
     * Example: Write Custom Query
     */
//    @Query(value = "SELECT p " +
//            "FROM Person p " +
//            "WHERE p.name = :name " +
//            "AND p.age >= 60  ")
//    Optional<Person> findSeniorsByName(@Param("name") String name);

}
