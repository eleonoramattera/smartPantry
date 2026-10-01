package com.example.smart_pantry_waste_saver.entity;

public enum UnitaMisura {
	
	GRAMMI("g"),
    CHILOGRAMMI("kg"),
    MILLILITRI("ml"),
    LITRI("l"),
    PEZZI("pz"),
    CUCCHIAI("cucc.va"),
    UN_PIZZICO("pizzico");

	private final String simbolo;

    UnitaMisura(String simbolo) {
        this.simbolo = simbolo;
    }

    public String getSimbolo() {
        return simbolo;
    }
}
