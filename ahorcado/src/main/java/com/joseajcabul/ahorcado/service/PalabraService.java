package com.joseajcabul.ahorcado.service;

import com.joseajcabul.ahorcado.model.Palabra;

import java.util.List;


public interface PalabraService {
    List<Palabra> getAllPalabras();
    Palabra getPalabraById(Integer idPalabra);
    Palabra savePalabra(Palabra palabra);
    Palabra updatePalabra(Integer idPalabra, Palabra palabra);
    void deletePalabra(Integer idPalabra);
}
