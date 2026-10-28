package com.example.smart_pantry_waste_saver.dto;

public class LoginRequest {
    private String email;
    private String username;
    private String password;

    // Costruttore vuoto, getter e setter indispensabili per Jackson
    public LoginRequest() {}

    public String getEmail() {
    	return email; 
    }
    
    public void setUsername(String username) { 
    	this.username = username; 
    }
    public String getUsername() {
    	return username; 
    }
    
    public void setEmail(String email) { 
    	this.email = email; 
    }

    public String getPassword() { 
    	return password; 
    }
    
    public void setPassword(String password) { 
    	this.password = password; 
    }
}
