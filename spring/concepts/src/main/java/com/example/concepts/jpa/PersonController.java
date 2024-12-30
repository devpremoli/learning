package com.example.concepts.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping("/create")
    public Person createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable Long id) {
        return personService.getPersonById(id);
    }


    // Derived Queries

    @GetMapping("/state/{state}")
    public List<Person> getPersonsByState(@PathVariable String state) {
        return personService.getPersonsByState(state);
    }

    // works if Query return a unique result, otherwise throws org.hibernate.NonUniqueResultException
    @GetMapping("/country/{country}")
    public Person getPersonByCountry(@PathVariable String country) {
        return personService.getPersonByCountry(country);
    }

    @GetMapping("state/{state}/count")
    public int getPersonsCountByState(@PathVariable String state) {
        return personService.getPersonsCountByState(state);
    }


}
