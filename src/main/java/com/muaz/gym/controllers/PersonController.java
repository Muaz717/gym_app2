package com.muaz.gym.controllers;

import com.muaz.gym.dto.PersonDTO;


import com.muaz.gym.mappers.PersonMapper;
import com.muaz.gym.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    @Autowired
    public PersonController(PersonService personService , PersonMapper personMapper) {
        this.personMapper = personMapper;
        this.personService = personService;
    }

    @PostMapping("/add")
    public ResponseEntity<HttpStatus> add(@RequestBody PersonDTO personDTO) {
        personService.register(personMapper.toEntity(personDTO));

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<PersonDTO>> getAll() {
        List<PersonDTO> personDTOS = personService.findAll().stream()
                .map(personMapper::toDto)
                .toList();

        personDTOS.forEach(p -> p.getMembershipDTOS().forEach(m -> m.setOwner(null)));
//        personDTOS.forEach(p -> p.getMemberships().forEach(this::enrichMembershipDTO));

        return ResponseEntity.ok(personDTOS);
    }

}
