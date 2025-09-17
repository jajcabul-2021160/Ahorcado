package com.joseajcabul.ahorcado.service;

import com.joseajcabul.ahorcado.model.Palabra;
import com.joseajcabul.ahorcado.repository.PalabraRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PalabraServiceImplements implements PalabraService {

    private final PalabraRepository palabraRepository;

    public PalabraServiceImplements(PalabraRepository palabraRepository) {
        this.palabraRepository = palabraRepository;
    }

    @Override
    public List<Palabra> getAllPalabras() {
        return palabraRepository.findAll();
    }

    @Override
    public Palabra getPalabraById(Integer id) {
        return palabraRepository.findById(id).orElse(null);
    }

    @Override
    public Palabra savePalabra(Palabra palabra) {
        if (palabraRepository.existsByPalabra(palabra.getPalabra())) {
            throw new RuntimeException("La palabra ya existe en el diccionario");
        }
        return palabraRepository.save(palabra);
    }

    @Override
    public Palabra updatePalabra(Integer idPalabra, Palabra palabra) {
        Palabra existingPalabra = palabraRepository.findById(idPalabra)
                .orElseThrow(() -> new RuntimeException("Palabra no encontrada con ID: " + idPalabra));

        // Corregido: se usa el método del repositorio que coincide con el ID
        if (palabraRepository.existsByPalabraAndIdPalabraNot(palabra.getPalabra(), idPalabra)) {
            throw new RuntimeException("La palabra ya está en uso por otra entrada");
        }

        // Corregido: se utilizan los métodos getter y setter de la entidad
        existingPalabra.setPalabra(palabra.getPalabra());
        existingPalabra.setPistaUno(palabra.getPistaUno());
        existingPalabra.setPistaDos(palabra.getPistaDos());
        existingPalabra.setPistaTres(palabra.getPistaTres());

        return palabraRepository.save(existingPalabra);
    }

    @Override
    public void deletePalabra(Integer idPalabra) {
        if (palabraRepository.existsById(idPalabra)) {
            palabraRepository.deleteById(idPalabra);
            System.out.println("La palabra se ha eliminado");
        } else {
            throw new RuntimeException("La palabra no fue encontrada");
        }
    }
}