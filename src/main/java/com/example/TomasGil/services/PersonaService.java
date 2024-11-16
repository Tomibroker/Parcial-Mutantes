package com.example.TomasGil.services;

import com.example.TomasGil.entities.Persona;
import com.example.TomasGil.repositories.PersonaRepository;
import com.example.TomasGil.exceptions.InvalidDnaException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public PersonaService(PersonaRepository personaRepository) {
        this.personaRepository = personaRepository;
    }


    @Transactional
    public boolean isMutant(String[] dna) throws Exception {

        InvalidDnaException.validateDNASequence(dna);

        String dnaSequence = String.join(",", dna);

        //Verificar si existe una persona con el mismo Dna
        Optional<Persona> personaExistente = personaRepository.findByDna(dnaSequence);
        if(personaExistente.isPresent()){
            return personaExistente.get().isMutant();
        }

        boolean isMutant = checkDna(dna);
        //Verificar si existe sino crear la persona
        Persona persona = Persona.builder().dna(dnaSequence).isMutant(isMutant).build();
        personaRepository.save(persona);

        return isMutant;
    }

    public boolean checkDna(String[] dna) {
        int n = dna.length;
        Set<Character> secuenciasEncontradas = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                char letraActual = dna[i].charAt(j);

                //Recorre en sentido vertical
                if (i + 3 < n &&
                        dna[i].charAt(j) == dna[i + 1].charAt(j) &&
                        dna[i + 1].charAt(j) == dna[i + 2].charAt(j) &&
                        dna[i + 2].charAt(j) == dna[i + 3].charAt(j)) {
                    secuenciasEncontradas.add(letraActual);
                }

                //Recorre en sentido horizontal
                if (j + 3 < n &&
                        dna[i].charAt(j) == dna[i].charAt(j + 1) &&
                        dna[i].charAt(j + 1) == dna[i].charAt(j + 2) &&
                        dna[i].charAt(j + 2) == dna[i].charAt(j + 3)) {
                    secuenciasEncontradas.add(letraActual);
                }

                //Recorre en sentido diagonal ascendente
                if (i - 3 >= 0 && j + 3 < n &&
                        dna[i].charAt(j) == dna[i - 1].charAt(j + 1) &&
                        dna[i - 1].charAt(j + 1) == dna[i - 2].charAt(j + 2) &&
                        dna[i - 2].charAt(j + 2) == dna[i - 3].charAt(j + 3)) {
                    secuenciasEncontradas.add(letraActual);
                }

                //Recorre en sentido diagonal descendente
                if (i + 3 < n && j + 3 < n &&
                        dna[i].charAt(j) == dna[i + 1].charAt(j + 1) &&
                        dna[i + 1].charAt(j + 1) == dna[i + 2].charAt(j + 2) &&
                        dna[i + 2].charAt(j + 2) == dna[i + 3].charAt(j + 3)) {
                    secuenciasEncontradas.add(letraActual);
                }

                //Verifica si ya se encontró más de una secuencia
                if (secuenciasEncontradas.size() > 1) {
                    return true;
                }
            }
        }

        return false;
    }
}


