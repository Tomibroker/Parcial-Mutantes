package com.example.TomasGil.services;

import com.example.TomasGil.dtos.Stats;
import com.example.TomasGil.repositories.PersonaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StatsService {
    @Autowired
    private PersonaRepository personaRepository;

    public Stats getStats() {
        long countMutant = personaRepository.countMutant();
        long countHuman = personaRepository.countNoMutant();
        double ratio = 0;
        if(countHuman != 0){
            ratio = Math.round(((double) countMutant / countHuman) * 100.0) / 100.0;
        }
        return
                Stats.builder().countMutant(countMutant).countHuman(countHuman).ratio(ratio).build();
    }
}

