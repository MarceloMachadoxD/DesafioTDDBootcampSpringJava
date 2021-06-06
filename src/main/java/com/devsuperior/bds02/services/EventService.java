package com.devsuperior.bds02.services;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.repositories.EventRepository;
import com.devsuperior.bds02.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

     @Transactional(readOnly = false)
    public EventDTO update(Long id, EventDTO dto){
        try {
            Event entity = repository.getOne(id);
            entity.setDate(dto.getDate());
            entity.setName(dto.getName());
            entity.setUrl(dto.getUrl());
            entity.setCity(new City(dto.getCityId(), null));

            return new EventDTO(entity);

        }catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("id Not found id: " + id);
        }

    }

}
