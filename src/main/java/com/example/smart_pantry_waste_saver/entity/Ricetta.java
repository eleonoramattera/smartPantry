package com.example.smart_pantry_waste_saver.entity;

import java.util.ArrayList;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.JoinColumn;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "ricette")
public class Ricetta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
  	@Column(nullable = false, length = 150)
    private String titolo;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb", nullable = false)
    private String istruzioni;
	
	@Builder.Default
	@ManyToMany
	@JoinTable(
	    name = "ricetta_ingredienti", 
	    joinColumns = @JoinColumn(name = "ricetta_id"), 
	    inverseJoinColumns = @JoinColumn(name = "ingrediente_id") 
	)
	private List<Ingrediente> ingredienti = new ArrayList<Ingrediente>();
	


}
