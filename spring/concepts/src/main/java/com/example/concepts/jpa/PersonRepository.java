package com.example.concepts.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    
    List<Person> findByState(String state);
    
    Person findByCountry(String country);

    int countByState(String state);
    
}
