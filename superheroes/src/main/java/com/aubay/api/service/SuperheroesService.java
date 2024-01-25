package com.aubay.api.service;



import com.aubay.api.dto.SuperheroeDTO;

import java.util.List;

public interface SuperheroesService {

  List<SuperheroeDTO> getAllSuperheroes(String palabra);

  SuperheroeDTO getSuperheroe(long id);

  SuperheroeDTO updateSuperheroe(SuperheroeDTO superheroe);

  void deleteSuperheroe(long id);
}
