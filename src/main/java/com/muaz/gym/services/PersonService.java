package com.muaz.gym.services;

import com.muaz.gym.models.Person;
import com.muaz.gym.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public List<Person> findAll() {
        return personRepository.findAll();
    }

    @Transactional
    public Optional<Person> findByName(String name) {
        Optional<Person> person = personRepository.findByName(name);

        return person;
    }

    @Transactional
    public Optional<Person> findById(int id) {
        Optional<Person> person = personRepository.findById(id);

        return person;
    }

    @Transactional
    public void register(Person person) {
        personRepository.save(person);
    }

}
