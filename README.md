POST https://parcial-mutantes-c9rr.onrender.com/mutant/ { "dna": ["AAAA", "CCCC", "TCAT", "AGGA"] }

Response: 200-OK Es un mutante

- Respuesta del servicio:
      - 200-OK cuando es un mutante.
      - 403-Forbidden cuando no mutante.


GET https://parcial-mutantes-c9rr.onrender.com/stats

Response: { “count_mutant_dna”:40, “count_human_dna”:100, “ratio”:0.4 }


## CÓMO EJECUTAR LA API MUTANT ?

-- LOCAL --

1) Descargar repositorio
2) Abir IDE y correr el programa 
3) Abrir POSTMAN
4) Copiar la url: http://localhost:8080
5) Probar los ENDPOINTS:
   
   5.1) **POST** --> http://localhost:8080/mutant/
   
   5.2) **GET**  --> http://localhost:8080/stats

DEPLOY RENDER

https://parcial-mutantes-c9rr.onrender.com



