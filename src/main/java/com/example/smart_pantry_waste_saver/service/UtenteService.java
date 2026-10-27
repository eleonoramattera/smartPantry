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

@Service 
public class UtenteService {

	@Autowired UtenteDAORepository repo;
	@Autowired @Qualifier("FakeUtenteBean") ObjectProvider<Utente> utenteFakeProvider;
	
	public void createFakeUtente() {
		Utente u = utenteFakeProvider.getObject();
		createUtenteByUsername(u);
	}
	
	public Utente getUtenteRandom() {
		return repo.findByUtenteRandom();
	}
	//JPA METHODS
	
	// getAll
	public List<Utente> findAllUtenti() {
	 return  repo.findAll();
    }
	
	//getByID
	public Utente findUtenteById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("Utente non esiste!");
		}
		return repo.findById(id).get();
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
	
	
	
	//update
	public Utente updateUtente(Utente utente, Long id) {
		if (repo.existsById(id)) {
			throw new EntityExistsException(" Utente [id:" + id + "] non esiste");
		}
		repo.save(utente);
		return utente;
	}
	
	
	//remove
	public String deleteUtenteById(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityExistsException("Utente not found");
		}
		repo.deleteById(id);
		return "Utente cancellato!";
	}
	
	//CUSTOM METHODS

	public Utente getByEmail(String email) {
		return repo.findByEmail(email);
	}
  
	
	
}
