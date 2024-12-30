package com.example.concepts.jpa;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    public Person createPerson(Person person) {
        return personRepository.save(person);
    }

    public Person getPersonById(Long id) {
        return personRepository.findById(id).orElse(null);
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    // Derived Queries

    public List<Person> getPersonsByState(String state) {
        return personRepository.findByState(state);
    }


    public Person getPersonByCountry(String country) {
        return personRepository.findByCountry(country);
    }

    public int getPersonsCountByState(String state) {
        return personRepository.countByState(state);
    }
}
