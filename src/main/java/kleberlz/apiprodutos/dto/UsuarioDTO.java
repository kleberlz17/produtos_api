package kleberlz.apiprodutos.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
		
		@NotBlank(message = "campo obrigatório")
		String login,
		
		@Email(message = "email inválido")
		@NotBlank(message = "campo obrigatório")
		String email,
		
		@NotBlank(message = "campo obrigatório")
		String senha) {

}
