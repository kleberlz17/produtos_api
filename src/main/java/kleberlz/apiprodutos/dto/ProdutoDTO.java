package kleberlz.apiprodutos.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ProdutoDTO(
		// CONTRATO DE COMO O PRODUTO DEVE ENTRAR OU SAIR DA API!!!
		UUID id,
		
		@NotBlank(message = "campo obrigatório")
		@Size(min = 2, max = 100, message = "campo fora do tamanho padrão")
		String nome,
		
		@NotNull(message = "campo obrigatório")
		BigDecimal preco,
		
		@NotBlank(message = "campo obrigatório")
		@Size(max = 100, min = 20, message = "campo fora do tamanho padrão")
		String descricao) {

}
