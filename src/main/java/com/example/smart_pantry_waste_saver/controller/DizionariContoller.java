package com.example.smart_pantry_waste_saver.controller;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dizionari")
public class DizionariContoller {

	 private final JdbcTemplate jdbcTemplate = new JdbcTemplate();
  
	 // Restituisce tutte le macrocategorie
	 @GetMapping("/macrocategorie")
	 public List<Map<String, Object>> getMacrocategorie() {
		 String sql = "SELECT id, nome, "
				 + "FROM public.ingredienti_macrocategoria_diz "
				 + "ORDER BY nome";
		 
		 return jdbcTemplate.queryForList(sql);
	 }
	// Restituisce tutte le sottocategorie
    @GetMapping("/sottocategorie")
    public List<Map<String, Object>> getSottocategorie() {
    	String sql = "SELECT id, nome, id_macrocategoria "
    			+ "FROM public.ingredienti_sottocategoria_diz "
    			+ "ORDER BY nome";

        return jdbcTemplate.queryForList(sql);
    }

	    // Restituisce gli alimenti, eventualmente filtrati
	    // per sottocategoria
	    @GetMapping("/alimenti")
	    public List<Map<String, Object>> getAlimenti(@RequestParam(required = false) Integer sottocategoriaId) {

	        if (sottocategoriaId == null) {
	            String sql = " SELECT id, nome, id_sottocategoria"
	            		+ "FROM public.ingredienti_diz"
	            		+ " ORDER BY nome";

	            return jdbcTemplate.queryForList(sql);
	        }

	        String sql = " SELECT id, nome, id_sottocategoria "
	        		+ "FROM public.ingredienti_diz "
	        		+ "WHERE id_sottocategoria = ?  "
	        		+ "ORDER BY nome ";

	        return jdbcTemplate.queryForList(sql, sottocategoriaId);
	    }

}
