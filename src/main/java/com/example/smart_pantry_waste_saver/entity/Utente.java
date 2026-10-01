package com.example.smart_pantry_waste_saver.entity;

import java.util.ArrayList;
import java.util.List;


public class Utente {
		
	private Long id;
	private String nome;
	private String cognome;
	private String email;
	private String password;	
	private RuoloUtente ruolo;
    private List<Ingrediente> prodotti = new ArrayList<>();

	public Utente () {
	}

	public Utente(String nome, String cognome,  String email, String password, RuoloUtente ruolo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.ruolo = ruolo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RuoloUtente getRuolo() {
		return ruolo;
	}

	public void setRuolo(RuoloUtente ruolo) {
		this.ruolo = ruolo;
	}	
	
	public void addProduct(Ingrediente ingrediente) {
	    this.prodotti.add(ingrediente);
	    ingrediente.setUtente(this); // Questo garantisce che anche il prodotto sappia a quale utente appartiene
	}

	public void removeProduct(Ingrediente ingrediente) {
	    this.prodotti.remove(ingrediente);
	    ingrediente.setUtente(null);
	}

	

}
