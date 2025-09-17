package com.joseajcabul.ahorcado.repository;

import com.joseajcabul.ahorcado.model.Palabra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PalabraRepository extends JpaRepository<Palabra, Integer> {

    // Method to check if a word already exists
    boolean existsByPalabra(String palabra);

    // Method to check if a word exists by ID
    boolean existsByPalabraAndIdPalabraNot(String palabra, Integer id_Palabra);

}