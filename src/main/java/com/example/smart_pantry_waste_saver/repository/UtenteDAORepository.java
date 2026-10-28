package com.example.smart_pantry_waste_saver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.smart_pantry_waste_saver.entity.Utente;

@Repository
public interface UtenteDAORepository extends JpaRepository<Utente, Long>  {
	
	public boolean existsByEmail(String email);	
	public Utente findByEmail(String email);
	public boolean existsByUsername(String username);	
	public Utente findByUsername(String username);

	@Query(value="SELECT u FROM Utente u ORDER BY RANDOM() LIMIT 1")
	Utente findByUtenteRandom();
		
	
}
