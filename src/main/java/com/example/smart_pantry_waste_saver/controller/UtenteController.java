package com.example.smart_pantry_waste_saver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.smart_pantry_waste_saver.entity.Utente;
import com.example.smart_pantry_waste_saver.service.UtenteService;

@RestController
@RequestMapping("/api/utenti")
public class UtenteController {

    @Autowired
    private UtenteService utenteService;

    // 1. GET - Recupera tutti gli utenti
    @GetMapping
    public ResponseEntity<List<Utente>> getAllUtenti() {
        List<Utente> utenti = utenteService.findAllUtenti(); 
        return new ResponseEntity<>(utenti, HttpStatus.OK);
    }

    // 2. GET per ID 
    @GetMapping("/{id}")
    public ResponseEntity<Utente> getUtenteById(@PathVariable Long id) {
        Utente utente = utenteService.findUtenteById(id);
        return new ResponseEntity<>(utente, HttpStatus.OK);
    }

    // 3. POST - Crea un nuovo utente (i dati viaggiano nel Body in JSON)
    // URL su Postman: POST http://localhost:8080/api/utenti
    @PostMapping
    public ResponseEntity<Utente> createUtente(@RequestBody Utente utente) {
        Utente nuovoUtente = utenteService.createUtenteByUsername(utente);
        return new ResponseEntity<>(nuovoUtente, HttpStatus.CREATED); //stato 201
    }

    // 4. PUT - Aggiorna un utente esistente (ID nell'URL, dati aggiornati nel Body)
    // URL su Postman: PUT http://localhost:8080/api/utenti/1
    @PutMapping("/{id}")
    public ResponseEntity<Utente> updateUtente(@PathVariable Long id, @RequestBody Utente utente) {
        Utente utenteAggiornato = utenteService.updateUtente(utente, id);
        return new ResponseEntity<>(utenteAggiornato, HttpStatus.OK);
    }

    // 5. DELETE - Cancella un utente tramite il suo ID nell'URL
    // URL su Postman: DELETE http://localhost:8080/api/utenti/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUtente(@PathVariable Long id) {
        utenteService.deleteUtenteById(id);
        return new ResponseEntity<>("Utente eliminato con successo", HttpStatus.OK);
    }

}
