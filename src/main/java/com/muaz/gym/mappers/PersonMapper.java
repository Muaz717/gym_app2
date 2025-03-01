package com.muaz.gym.mappers;

import com.muaz.gym.dto.PersonDTO;
import com.muaz.gym.models.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PersonMapper {

    private final ModelMapper modelMapper;

    @Autowired
    public PersonMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public PersonDTO toDto(Person person) {
        return modelMapper.map(person, PersonDTO.class);
    }

    public Person toEntity(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }
}
