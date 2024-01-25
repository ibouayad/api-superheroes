package com.aubay.api;

import com.aubay.api.dto.SuperheroeDTO;
import com.aubay.api.entity.Superheroe;
import com.aubay.api.repository.SuperheroesRepository;
import com.aubay.api.service.SuperheroesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
class ApiSuperheroesApplicationTests {

  @MockBean
  SuperheroesRepository superheroesRepository;

  @Autowired
  SuperheroesService superheroesService;

  // PRUEBAS UNITARIAS
  @Test
  void whenGetAllSuperheroes_ReturnListOfSuperheroes() {
    when(superheroesRepository.findByNameContaining("")).thenReturn(getListSuperheroes());
    List<SuperheroeDTO> resultado = superheroesService.getAllSuperheroes("");

    assertEquals(4, resultado.size());
  }

  @Test
  void whenGetSuperheroeById_ReturnSuperheroe() {
    when(superheroesRepository.findById(1L)).thenReturn(Optional.of(getSuperheroe()));
    SuperheroeDTO resultado = superheroesService.getSuperheroe(1L);

    assertNotNull(resultado);
  }

  /**
   * Devuelve una lista con varios superheroes
   *
   * @return lista de superheroes
   */
  private List<Superheroe> getListSuperheroes() {
    List<Superheroe> superheroes = new ArrayList<>();

    Superheroe superheroe = new Superheroe(1L, "Superman");
    superheroes.add(superheroe);

    superheroe = new Superheroe(2L, "Spiderman");
    superheroes.add(superheroe);

    superheroe = new Superheroe(3L, "Thor");
    superheroes.add(superheroe);

    superheroe = new Superheroe(4L, "Manolito el fuerte");
    superheroes.add(superheroe);

    return superheroes;
  }

  /**
   * Devuelve un superheroe
   *
   * @return superheroe
   */
  private Superheroe getSuperheroe() {
    return  new Superheroe(1L, "Superman");
  }

}
