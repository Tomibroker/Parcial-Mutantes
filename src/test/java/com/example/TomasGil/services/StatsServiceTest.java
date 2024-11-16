package com.example.TomasGil.services;

import com.example.TomasGil.dtos.Stats;
import com.example.TomasGil.repositories.PersonaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class StatsServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private StatsService statsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //Test sin mutantes ni humanos
    @Test
    void testEmpty() {
        when(personaRepository.countMutant()).thenReturn(40L);
        when(personaRepository.countNoMutant()).thenReturn(100L);

        Stats stats = statsService.getStats();

        assertEquals(40L, stats.getCountMutant());
        assertEquals(100L, stats.getCountHuman());
        assertEquals(0.4, stats.getRatio());  // 40 / 100 = 0.4
    }


    @Test
    void testNoMutants() {
        when(personaRepository.countMutant()).thenReturn(0L);
        when(personaRepository.countNoMutant()).thenReturn(100L);

        Stats stats = statsService.getStats();

        assertEquals(0L, stats.getCountMutant());
        assertEquals(100L, stats.getCountHuman());
        assertEquals(0.0, stats.getRatio());
    }


    @Test
    void testNoHumans() {
        when(personaRepository.countMutant()).thenReturn(50L);
        when(personaRepository.countNoMutant()).thenReturn(0L);

        Stats stats = statsService.getStats();

        assertEquals(50L, stats.getCountMutant());
        assertEquals(0L, stats.getCountHuman());
        assertEquals(0.0, stats.getRatio());
    }

}