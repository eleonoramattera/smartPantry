package com.example.smart_pantry_waste_saver.dto;

public record UtenteResponse(
	    Long id,
	    String nome,
	    String cognome,
	    String email,
	    String username
	) {}