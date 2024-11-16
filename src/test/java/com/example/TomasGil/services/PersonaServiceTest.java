package com.example.TomasGil.services;

import com.example.TomasGil.exceptions.InvalidDnaException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PersonaServiceTest {

    @Autowired
    private PersonaService personaService;

    //vertical
    @Test
    public void testVertical() throws Exception {
        String[] dna = {
                "ATGCTA",
                "AACCTC",
                "ATACTT",
                "AGACTG",
                "CCTGTA",
                "TCACTG"
        };
        assertTrue(personaService.isMutant(dna));
    }

    //horizontal
    @Test
    public void testHorizontal() throws Exception {
        String[] dna = {
                "TTTTAA",
                "CCGTGC",
                "TTATGT",
                "AGATGG",
                "GGGGTA",
                "TTACTG"
        };
        assertTrue(personaService.isMutant(dna));
    }

    //diagonales descendentes
    @Test
    public void testDiagonal1() throws Exception {
        String[] dna = {
                "ACGCGA",
                "CACTGC",
                "TTACGT",
                "AGAACG",
                "CCGCTA",
                "TCACTG"
        };
        assertTrue(personaService.isMutant(dna));
    }

    //diagonales ascendentes
    @Test
    public void testDiagonal2() throws Exception {
        String[] dna = {
                "ATGCGC",
                "CATTCG",
                "TTACGT",
                "AGCGTG",
                "CTGCTA",
                "TCACTG"
        };
        assertTrue(personaService.isMutant(dna));
    }


    @Test
    public void testNoMutant() throws Exception  {
        String[] dna = {
                "ATGCGA",
                "CGTTAC",
                "TTAGCT",
                "AGAATG",
                "CTCCTA",
                "TCACTG"
        };
        assertFalse(personaService.isMutant(dna));
    }



    @Test
    public void test1() throws Exception{
        String[] dna = {
                "AAAA",
                "CCCC",
                "TCAT",
                "AGGA"
        };
        assertTrue(personaService.isMutant(dna));
    }


    @Test
    public void test2() throws Exception{
        String[] dna = {
                "TGAC",
                "AGTG",
                "TTTG",
                "GGCC"
        };
        assertTrue(personaService.isMutant(dna));
    }


    @Test
    public void test3() throws Exception{
        String[] dna = {
                "AAAT",
                "AACC",
                "AAAC",
                "CGGG"
        };
        assertFalse(personaService.isMutant(dna));
    }


    @Test
    public void test4() throws Exception{
        String[] dna = {
                "AAAA",
                "AAAA",
                "AAAA",
                "AAAA"
        };
        assertFalse(personaService.isMutant(dna));
    }


    @Test
    public void test5() throws Exception{
        String[] dna = {
                "TGAC",
                "ATCC",
                "TAAC",
                "GGTC"
        };
        assertFalse(personaService.isMutant(dna));
    }



    @Test
    public void testDnaNull() {
        String[] dna = null;
        InvalidDnaException exception = assertThrows(InvalidDnaException.class, () -> {
            personaService.isMutant(dna);
        });
        assertEquals("El ADN no puede ser null.", exception.getMessage());
    }

    @Test
    public void testDnaEmpty() {
        String[] dna = {};
        InvalidDnaException exception = assertThrows(InvalidDnaException.class, () -> {
            personaService.isMutant(dna);
        });
        assertEquals("El ADN no puede ser vacío.", exception.getMessage());
    }

    @Test
    public void testContainsNull() {
        String[] dna = {
                "CTA",
                null,
                "ACC"
        };
        InvalidDnaException exception = assertThrows(InvalidDnaException.class, () -> {
            personaService.isMutant(dna);
        });
        assertEquals("El ADN no puede contener NULL.", exception.getMessage());
    }

    @Test
    public void testSizeDna() {
        String[] dna = {
                "AAAA",
                "AAAC",
                "CCCT"
        };
        InvalidDnaException exception = assertThrows(InvalidDnaException.class, () -> {
            personaService.isMutant(dna);
        });
        assertEquals("El ADN debe ser una matriz de nxn.", exception.getMessage());
    }

    @Test
    public void testInvalidadCharacters() {
        String[] dna = {
                "ZCC",
                "GGG",
                "TTT",
        };
        InvalidDnaException exception = assertThrows(InvalidDnaException.class, () -> {
            personaService.isMutant(dna);
        });
        assertEquals("El ADN solo puede contener A, C, G y T.", exception.getMessage());
    }
}




