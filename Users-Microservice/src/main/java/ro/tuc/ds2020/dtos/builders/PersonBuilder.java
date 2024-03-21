package ro.tuc.ds2020.dtos.builders;

import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.security.JwtUtils;

public class PersonBuilder {

    private PersonBuilder() {
    }


    private static JwtUtils jwtUtils = new JwtUtils();
    public static PersonDTO toPersonDTO(Person person) {
        return new PersonDTO(person.getId(),person.getName(), person.getPassword(),person.getRole(),jwtUtils.generateToken(person));
    }

    public static PersonDetailsDTO toPersonDetailsDTO(Person person) {
        return new PersonDetailsDTO(person.getId(), person.getName(), person.getRole(), person.getPassword());
    }

    public static Person toEntity(PersonDetailsDTO personDetailsDTO) {
        return new Person(personDetailsDTO.getName(),
                personDetailsDTO.getRole(),
                personDetailsDTO.getPassword());
    }
}
