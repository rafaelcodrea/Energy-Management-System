package ro.tuc.ds2020.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.tuc.ds2020.dtos.PersonDTO;
import ro.tuc.ds2020.dtos.PersonDetailsDTO;
import ro.tuc.ds2020.entities.Person;
import ro.tuc.ds2020.services.PersonService;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@CrossOrigin
@RequestMapping(value = "/person")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping()
    public ResponseEntity<List<PersonDTO>> getPersons() {
        List<PersonDTO> dtos = personService.findPersons();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @PostMapping(value = "/allow")
    public ResponseEntity<Long> insertProsumer(@Valid @RequestBody PersonDetailsDTO personDTO) {
        System.out.println("sunt in controller");
        Long personID = personService.insert(personDTO);
        return new ResponseEntity<>(personID, HttpStatus.CREATED);
    }

    @GetMapping(value = "/response1/{id}")
    public ResponseEntity<PersonDetailsDTO> getPerson(@PathVariable("id") Long personId) {
        PersonDetailsDTO dto = personService.findPersonById(personId);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @GetMapping(value = "/read/{id}")
    public ResponseEntity<PersonDetailsDTO> getPersonM(@PathVariable("id") Long personId) {
        PersonDetailsDTO dto = personService.findPersonById(personId);
        if (dto != null) {
            return new ResponseEntity<>(dto, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping(value = "/update/{id}")
    public ResponseEntity<Void> updatePerson(
            @PathVariable("id") Long personId,
            @Valid @RequestBody PersonDetailsDTO personDTO
    ) {
        System.out.println("sunt in update ma fut");

        if (personService.updatePerson(personId, personDTO)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable("id") Long personId) {
        if (personService.deletePerson(personId)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/allow/check")
    public ResponseEntity<PersonDTO> checkUser(@Valid @RequestBody PersonDTO personDTO) {
        PersonDTO p = personService.checkUser(personDTO.getName(), personDTO.getPassword());
        if (p!=null) {
            return new ResponseEntity<>(p, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
