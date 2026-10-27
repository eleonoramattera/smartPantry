package com.example.smart_pantry_waste_saver.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "ingredienti")
public class Ingrediente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String name; 
	
	@Column(nullable = false, length = 50)
	@Enumerated(EnumType.STRING)
	private CategoriaIngrediente categoria; 

    @Column(nullable = false) 
	private Double quantita;
	
	@Column(nullable = false, length = 20, name ="unita_misura")
	@Enumerated(EnumType.STRING)
	private UnitaMisura unitaMisura; 

	@Column(nullable = false, length = 30)
	@Enumerated(EnumType.STRING)
	private StatoIngrediente stato; 
	
    @Column(name = "data_scadenza")
	private LocalDate dataScadenza;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "utente_id", nullable = false) 
	private Utente utente; //Relationship Many-to-One
	

	
	
}
