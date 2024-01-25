package com.aubay.api.controller.impl;

import com.aubay.api.aop.TrackTime;
import com.aubay.api.controller.SuperheroesController;
import com.aubay.api.dto.SuperheroeDTO;
import com.aubay.api.service.SuperheroesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
public class SuperheroesControllerImpl implements SuperheroesController {

  @Autowired
  SuperheroesService superheroesService;

  @Autowired
  CacheManager cacheManager;

  @TrackTime
  @GetMapping("/superheroes")
  public ResponseEntity<List<SuperheroeDTO>> getAllSuperheroes(
      @RequestParam(value = "nombre heroe opcional", required = false, defaultValue = "") String palabra) {
    log.info("SuperheroesController: getAllSuperheroes");
    return new ResponseEntity<>(
        superheroesService.getAllSuperheroes(palabra), HttpStatus.OK);
  }

  @TrackTime
  @GetMapping("/superheroes/{id}")
  public ResponseEntity<SuperheroeDTO> getSuperheroe(@PathVariable(value = "id") long id) {
    log.info("SuperheroesController: getSuperheroe");
    return new ResponseEntity<>(superheroesService.getSuperheroe(id), HttpStatus.OK);
  }

  @TrackTime
  @PutMapping("/superheroe")
  public ResponseEntity<SuperheroeDTO> updateSuperheroe(
      @RequestHeader("Authorization") String authorization,
      @RequestBody SuperheroeDTO superheroeDTO) {
    log.info("SuperheroesController: updateSuperheroe");
    return new ResponseEntity<>(superheroesService.updateSuperheroe(superheroeDTO), HttpStatus.OK);
  }

  @TrackTime
  @DeleteMapping("/superheroe/{id}")
  public ResponseEntity<HttpStatus> deleteSuperheroe(
      @RequestHeader("Authorization") String authorization,
      @PathVariable long id) {
    log.info("SuperheroesController: deleteSuperheroe");
    superheroesService.deleteSuperheroe(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

  @TrackTime
  @DeleteMapping("/superheroes/cache")
  public ResponseEntity<HttpStatus> limpiarCacheSuperheroes() {
    log.info("Refrescando la caché de superheroes");
    Objects.requireNonNull(cacheManager.getCache("superheroes")).clear();
    Objects.requireNonNull(cacheManager.getCache("superheroe")).clear();
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  }

}
