package com.example.TomasGil.exceptions;

public class InvalidDnaException extends RuntimeException {
    public InvalidDnaException(String message) {
        super(message);
    }

    public static void validateDNASequence (String[] dna) throws InvalidDnaException{

        //Validar que no sea null
        if(dna == null){
            throw new InvalidDnaException("El DNA no puede ser null.");
        }

        int n = dna.length;

        //Validar que no sea un array vacío
        if(n == 0){
            throw new InvalidDnaException("El DNA no puede estar vacío.");
        }

        //Validar que sea una matriz cuadrada
        for(int i=0; i<n; i++){

            if(dna[i] == null){
                throw new InvalidDnaException("El DNA no puede contener un NULL.");
            }
            if(dna[i].length() != n){
                throw new InvalidDnaException("El DNA debe ser una matriz cuadrada.");
            }

            //Validar que contenga solamente A, C, G y T
            for(int j=0; j<n; j++){
                char c = dna[i].charAt(j);
                if(c != 'A' && c != 'T' && c != 'C' && c != 'G'){
                    throw new InvalidDnaException("El DNA solo puede contener las letras A, C, G y T.");
                }
            }
        }
    }
}
