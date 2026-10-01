package com.example.smart_pantry_waste_saver.entity;

public enum StatoIngrediente {

    DISPONIBILE("In dispensa"),
    IN_ESAURIMENTO("Quasi finito"),
    CONSUMATO("Consumato"),
    SCADUTO("Scaduto e buttato"),
    CONGELATO("In freezer");
	
	private final String descrizione;
	
	StatoIngrediente(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
}
