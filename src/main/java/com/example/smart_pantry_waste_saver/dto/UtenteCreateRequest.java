
package com.example.smart_pantry_waste_saver.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UtenteCreateRequest(

    @NotBlank
    @Size(max = 100)
    String nome,

    @NotBlank
    @Size(max = 100)
    String cognome,

    @NotBlank
    @Email
    @Size(max = 100)
    String email,

    @NotBlank
    @Size(max = 100)
    String username,

    @NotBlank
    @Size(min = 8, max = 72)
    String password

) {}
  