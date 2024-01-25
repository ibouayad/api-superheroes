package com.aubay.api.entity;

import com.aubay.api.dto.SuperheroeDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "superheroes")
public class Superheroe {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String nombre;

  public Superheroe (SuperheroeDTO superheroeDTO) {
    super();
    this.id = superheroeDTO.getId();
    this.nombre = superheroeDTO.getNombre();
  }

}
