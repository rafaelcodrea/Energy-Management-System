package ro.tuc.ds2020.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.tuc.ds2020.controllers.handlers.exceptions.model.ResourceNotFoundException;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.dtos.builders.PersonBuilder;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
//import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class PersonService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonService.class);
    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<PersonDTO> findPersons() {
        List<Person> personList = personRepository.findAll();
        return personList.stream()
                .map(PersonBuilder::toPersonDTO)
                .collect(Collectors.toList());
    }

    public PersonDetailsDTO findPersonById(Long id) {
        Optional<Person> prosumerOptional = personRepository.findById(id);
        if (!prosumerOptional.isPresent()) {
            LOGGER.error("Person with id {} was not found in db", id);
            throw new ResourceNotFoundException(Person.class.getSimpleName() + " with id: " + id);
        }
        return PersonBuilder.toPersonDetailsDTO(prosumerOptional.get());
    }

    public Long insert(PersonDetailsDTO personDTO) {
        Person person = PersonBuilder.toEntity(personDTO);
        person = personRepository.save(person);
        LOGGER.debug("Person with id {} was inserted in db", person.getId());
        return person.getId();
    }
    public boolean updatePerson(Long personId, PersonDetailsDTO updatedPersonDTO) {
        Optional<Person> personOptional = personRepository.findById(personId);

        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            person.setName(updatedPersonDTO.getName());
            person.setRole(updatedPersonDTO.getRole());
            person.setPassword(updatedPersonDTO.getPassword());

            // Save the updated person entity
            personRepository.save(person);

            LOGGER.debug("Person with id {} was updated in the database", person.getId());
            return true; // Person updated successfully
        } else {
            LOGGER.error("Person with id {} was not found in the database", personId);
            return false; // Person not found
        }
    }
    public boolean deletePerson(Long personId) {
        Optional<Person> personOptional = personRepository.findById(personId);

        if (personOptional.isPresent()) {
            Person person = personOptional.get();
            personRepository.delete(person);

            LOGGER.debug("Person with id {} was deleted from the database", person.getId());
            return true; // Person deleted successfully
        } else {
            LOGGER.error("Person with id {} was not found in the database", personId);
            return false; // Person not found
        }
    }
    public PersonDTO checkUser(String username, String password) {
        Optional<Person> personOptional = personRepository.findByName(username);
        PersonDTO p = null;
        if (personOptional.isPresent()) {
            if(personOptional.get().getPassword().equals(password))
                p = PersonBuilder.toPersonDTO(personOptional.get());
        }
        return p;
    }


}
