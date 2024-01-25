/*
 * Copyright (c) 2024. IB
 */

package com.aubay.api.service.impl;

import com.aubay.api.dto.SuperheroeDTO;
import com.aubay.api.entity.Superheroe;
import com.aubay.api.exception.SuperHeroeNoEncontrado;
import com.aubay.api.repository.SuperheroesRepository;
import com.aubay.api.service.SuperheroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SuperheroesServiceImpl implements SuperheroesService {

  @Autowired
  SuperheroesRepository superheroesRepository;


  @Override
  @Cacheable("superheroes")
  public List<SuperheroeDTO> getAllSuperheroes(String palabra) {

    log.info("SuperheroesService: getAllSuperheroes");
    List<Superheroe> superHeroes = superheroesRepository
        .findByNameContaining(palabra.toLowerCase());
    List<SuperheroeDTO> superheroeDTOS = new ArrayList<>();
    superHeroes.forEach(s ->
        superheroeDTOS.add(new SuperheroeDTO(s))
    );
    return superheroeDTOS;
  }

  @Override
  @Cacheable("superheroe")
  public SuperheroeDTO getSuperheroe(long id) {
    log.info("SuperheroesService: getSuperheroe");
    Optional<Superheroe> superheroe = superheroesRepository.findById(id);
    return superheroe
        .map(SuperheroeDTO::new)
        .orElseThrow(
            () -> new SuperHeroeNoEncontrado(
                String.format("No existe el superheroe con id: %s", id)));
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value="superheroe", allEntries=true),
      @CacheEvict(value="superheroes", allEntries=true)})
  public SuperheroeDTO updateSuperheroe(SuperheroeDTO superheroeDTO) {
    log.info("SuperheroesService: updateSuperheroe");
    Superheroe superheroe = superheroesRepository.save(new Superheroe(superheroeDTO));
    return new SuperheroeDTO(superheroe);
  }

  @Override
  @Caching(evict = {
      @CacheEvict(value="superheroe", allEntries=true),
      @CacheEvict(value="superheroes", allEntries=true)})
  public void deleteSuperheroe(long id) {
    log.info("SuperheroesService: deleteSuperheroe");
    superheroesRepository.deleteById(id);
  }
}
