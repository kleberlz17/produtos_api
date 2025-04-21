package kleberlz.apiprodutos.dto;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;




public class ProdutoDTO {
		//  VALIDA COMO O PRODUTO DEVE ENTRAR OU SAIR DA API!!!
		private UUID id;
		
		@NotBlank(message = "campo obrigatório")
		@Size(min = 2, max = 100, message = "campo fora do tamanho padrão")
		private String nome;
		
		@NotNull(message = "campo obrigatório")
		private BigDecimal preco;
		
		@NotBlank(message = "campo obrigatório")
		@Size(max = 100, min = 5, message = "campo fora do tamanho padrão")
		private String descricao;

		public ProdutoDTO(UUID id,
				@NotBlank(message = "campo obrigatório") @Size(min = 2, max = 100, message = "campo fora do tamanho padrão") String nome,
				@NotNull(message = "campo obrigatório") BigDecimal preco,
				@NotBlank(message = "campo obrigatório") @Size(max = 100, min = 5, message = "campo fora do tamanho padrão") String descricao) {
			super();
			this.id = id;
			this.nome = nome;
			this.preco = preco;
			this.descricao = descricao;
		}

		public ProdutoDTO() {
			super();
		}

		public UUID getId() {
			return id;
		}

		public void setId(UUID id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public BigDecimal getPreco() {
			return preco;
		}

		public void setPreco(BigDecimal preco) {
			this.preco = preco;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		} 

		
		
}
