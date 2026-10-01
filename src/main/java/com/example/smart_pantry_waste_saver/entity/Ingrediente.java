package com.example.smart_pantry_waste_saver.entity;

public class Ingrediente {
	
	private Long id;
	private String name; 
	private CategoriaIngrediente categoria; 
	private Double quantita;
	private UnitaMisura unitaMisura; 
	private StatoIngrediente stato; 
	private Utente utente; //Relationship Many-to-One
	

	public Ingrediente() {
	}

	public Ingrediente(String name, CategoriaIngrediente categoria, Double quantita, UnitaMisura unitaMisura, StatoIngrediente stato, Utente utente) {
		super();
		this.name = name;
		this.categoria = categoria;
		this.quantita = quantita;
		this.unitaMisura = unitaMisura;
		this.stato = stato;
		this.utente = utente;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CategoriaIngrediente getCategoria() {
		return categoria;
	}

	public void setCategoria(CategoriaIngrediente categoria) {
		this.categoria = categoria;
	}

	public Double getQuantita() {
		return quantita;
	}

	public void setQuantita(Double quantita) {
		this.quantita = quantita;
	}

	public UnitaMisura getUnitaMisura() {
		return unitaMisura;
	}

	public void setUnitaMisura(UnitaMisura unitaMisura) {
		this.unitaMisura = unitaMisura;
	}

	public StatoIngrediente getStato() {
		return stato;
	}

	public void setStato(StatoIngrediente stato) {
		this.stato = stato;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	
	
}
