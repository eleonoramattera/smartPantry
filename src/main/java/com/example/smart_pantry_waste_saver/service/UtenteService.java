package com.example.smart_pantry_waste_saver.service;

import java.util.List;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.example.smart_pantry_waste_saver.entity.Utente;
import com.example.smart_pantry_waste_saver.repository.UtenteDAORepository;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.example.smart_pantry_waste_saver.dto.PasswordUpdateRequest;
import com.example.smart_pantry_waste_saver.dto.UtenteCreateRequest;
import com.example.smart_pantry_waste_saver.dto.UtenteResponse;
import com.example.smart_pantry_waste_saver.dto.UtenteUpdateRequest;

import org.springframework.security.crypto.password.PasswordEncoder;

@Service 
public class UtenteService {

	@Autowired UtenteDAORepository repo;
	@Autowired @Qualifier("FakeUtenteBean") ObjectProvider<Utente> utenteFakeProvider;
	
    private final PasswordEncoder passwordEncoder;

    // Costruttore: Spring inietta automaticamente le dipendenze
    public UtenteService(UtenteDAORepository repo, PasswordEncoder passwordEncoder) {
        this.repo = repo;
        this.passwordEncoder = passwordEncoder;
    }
	
	public void createFakeUtente() {
		Utente u = utenteFakeProvider.getObject();
		createUtenteByUsername(u);
	}
	
	public Utente getUtenteRandom() {
		return repo.findByUtenteRandom();
	}
	//JPA METHODS
	
	// getAll - recupera tutti gli utenti
	public List<UtenteResponse> findAllUtenti() {
	    return repo.findAll()
	            .stream()
	            .map(this::toResponse) //converte ogni Utente nel DTO corrispondente
	            .toList();
	}
	
	//getByID - recupera un utente tramite ID
	public UtenteResponse findUtenteById(Long id) {
	    Utente utente = repo.findById(id)
	        .orElseThrow(() -> new ResponseStatusException( HttpStatus.NOT_FOUND, "Utente con ID " + id + " non trovato" ));

	    return toResponse(utente);
	}
	
	//create
	public Utente createUtenteByEmail(Utente utente) {
	 // Gestione di un errore | email già presente
	 if(repo.existsByEmail(utente.getEmail())) {
		throw new EntityNotFoundException("email/numero di telefono già presente");
	    }
	 
	 System.out.println("Utente " + utente.getNome() + " " + utente.getCognome() + " inserito nel DB");
		return repo.save(utente);
	}
	public Utente createUtenteByUsername(Utente utente) {
		// Gestione di un errore | email già presente
		if(repo.existsByUsername(utente.getUsername())) {
			throw new EntityNotFoundException("email/numero di telefono già presente");
		}
		
		System.out.println("Utente " + utente.getNome() + " " + utente.getCognome() + " inserito nel DB");
		return repo.save(utente);
	}
	
	public UtenteResponse updateUtente(Long id, UtenteCreateRequest request) {
	    Utente utente = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Utente con ID " + id + " non trovato" ));
	
	    utente.setNome(request.nome());
	    utente.setCognome(request.cognome());
	    utente.setEmail(request.email());
	    utente.setUsername(request.username());
	
	    Utente utenteModificato = repo.save(utente);

	    return toResponse(utenteModificato);
	}


	public UtenteResponse partiallyUpdateUtente(Long id, UtenteUpdateRequest request) {
	
	    Utente utente = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Utente con ID " + id + " non trovato"));
	
	    if (request.nome() != null) {
	        utente.setNome(request.nome());
	    }
	
	    if (request.cognome() != null) {
	        utente.setCognome(request.cognome());
	    }
	
	    if (request.email() != null) {
	        utente.setEmail(request.email());
	    }
	
	    if (request.username() != null) {
	        utente.setUsername(request.username());
	    }
	
	    Utente utenteModificato = repo.save(utente);
	
	    return toResponse(utenteModificato);
	}


	public void updatePassword(Long id, PasswordUpdateRequest request) {
	    Utente utente = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Utente con ID " + id + " non trovato"));
	
	    utente.setPassword(passwordEncoder.encode(request.password()));
	
	    repo.save(utente);
	}

	
	//remove
	public void eliminaUtente(Long id) { 
		Utente utente = repo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Utente con ID " + id + " non trovato" ));
	
	    repo.delete(utente);
	}

	//CUSTOM METHODS

	public Utente getByEmail(String email) {
		return repo.findByEmail(email);
	}
  
	//il Service che recupera l'Entity dal database e la converte nel DTO da restituire al Controller.
	// Converte l'Entity in DTO
	private UtenteResponse toResponse(Utente utente) {
	    return new UtenteResponse(
	        utente.getId(),
	        utente.getNome(),
	        utente.getCognome(),
	        utente.getEmail(),
	        utente.getUsername()
	    );
	}
		

	public UtenteResponse creaUtente(UtenteCreateRequest request) {
	    Utente utente = new Utente();
	
	    utente.setNome(request.nome());
	    utente.setCognome(request.cognome());
	    utente.setEmail(request.email());
	    utente.setUsername(request.username());
	    utente.setPassword(passwordEncoder.encode(request.password()));
	    Utente utenteSalvato = repo.save(utente);

    return toResponse(utenteSalvato);
}

	
}
