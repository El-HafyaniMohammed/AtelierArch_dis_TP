package com.example.demo.controller;

import com.example.demo.model.Etudiant;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/etudiants")
public class EtudiantController {

    private List<Etudiant> etudiants = new ArrayList<>();

    @GetMapping
    public Flux<Etudiant> getEtudiants() {
        return Flux.fromIterable(etudiants);
    }

    @PostMapping
    public Mono<String> addEtudiant(@RequestBody Etudiant e) {
        etudiants.add(e);
        return Mono.just("Etudiant ajouté !");
    }
}