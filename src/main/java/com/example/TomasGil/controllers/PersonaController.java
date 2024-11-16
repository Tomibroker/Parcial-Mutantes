package com.example.TomasGil.controllers;

import com.example.TomasGil.dtos.DnaRequest;
import com.example.TomasGil.services.PersonaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(path = "mutant")
public class PersonaController {

    private PersonaService personaService;

    public PersonaController(PersonaService personaService){
        this.personaService = personaService;
    }
    @PostMapping("/")
    public ResponseEntity<String> isMutant(@RequestBody DnaRequest dnaRequest){
        try{
            boolean isMutant = personaService.isMutant(dnaRequest.getDna());
            if (isMutant){
                return ResponseEntity.status(HttpStatus.OK).body("La persona es mutante");
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body("La persona no es mutante");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());

        }
    }


}

