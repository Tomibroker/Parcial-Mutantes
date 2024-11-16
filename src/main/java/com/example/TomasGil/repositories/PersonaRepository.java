package com.example.TomasGil.repositories;

import com.example.TomasGil.entities.Persona;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonaRepository extends BaseRepository<Persona, Long> {

    Optional<Persona> findByDna(String dna);

    //Cuenta la cantidad de mutantes en la BD
    @Query(value = "SELECT COUNT(ind) FROM Persona ind WHERE ind.isMutant = true")
    long countMutant();

    //Cuenta la cantidad de no mutantes en la BD
    @Query(value = "SELECT COUNT(ind) FROM Persona ind WHERE ind.isMutant = false")
    long countNoMutant();

}
