package com.joseajcabul.ahorcado.controller;

import com.joseajcabul.ahorcado.model.Palabra;
import com.joseajcabul.ahorcado.service.PalabraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/palabras")
public class PalabraController {

    private final PalabraService palabraService;

    public PalabraController(PalabraService palabraService) {
        this.palabraService = palabraService;
    }

    @GetMapping
    public List<Palabra> getAllPalabras() {
        return palabraService.getAllPalabras();
    }

    @GetMapping("/{id}")
    public Palabra getPalabraById(@PathVariable("id") Integer idPalabra) {
        return palabraService.getPalabraById(idPalabra);
    }

    @PostMapping
    public Palabra createPalabra(@RequestBody Palabra palabra) {
        return palabraService.savePalabra(palabra);
    }

    @PutMapping("/{id}")
    public Palabra updatePalabra(@PathVariable("id") Integer idPalabra, @RequestBody Palabra palabra) {
        return palabraService.updatePalabra(idPalabra, palabra);
    }

    @DeleteMapping("/{id}")
    public void deletePalabra(@PathVariable("id") Integer idPalabra) {
        palabraService.deletePalabra(idPalabra);
    }
}