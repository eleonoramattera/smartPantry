package com.example.smart_pantry_waste_saver.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.smart_pantry_waste_saver.config.DatabaseConnection;

@Service
public class DizService {

	
	public List<String> getDiz() {
		
	List<String> diz = new ArrayList<String>();
        String query = "SELECT nome FROM ingredienti_alimenti_diz";		
	    
        // Prendi la connessione dal Pool e prepara lo statement
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
  
            
            // Esegui la chiamata sul server
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String nome = rs.getString("nome");	 
                    diz.add(nome);
                    System.out.println(nome );
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Errore durante l'esecuzione della query!");
            e.printStackTrace();
        } finally {
            // DatabaseConnection.shutdown();
        }

        return diz;
	}


}
