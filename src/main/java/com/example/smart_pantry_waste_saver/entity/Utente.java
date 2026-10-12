package com.example.smart_pantry_waste_saver.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "utenti",
	    uniqueConstraints = {  @UniqueConstraint( name = "uk_utenti_email", columnNames = "email" )}
		)
public class Utente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String nome;

	@Column(nullable = false, length = 100)
	private String cognome;

	@Column(nullable = false, length = 100)
	private String email;

	@Column(nullable = false, length = 255)
	private String password;	
	
	@Column(nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private RuoloUtente ruolo;
	
	@OneToMany(mappedBy = "utente", cascade = CascadeType.ALL, orphanRemoval = true)
	@Builder.Default
    private List<Ingrediente> prodotti = new ArrayList<>();

	public Utente(String nome, String cognome,  String email, String password, RuoloUtente ruolo) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.password = password;
		this.ruolo = ruolo;
	}


}
