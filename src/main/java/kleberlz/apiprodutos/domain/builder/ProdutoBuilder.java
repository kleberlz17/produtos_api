package kleberlz.apiprodutos.domain.builder;

import java.math.BigDecimal;
import java.util.UUID;

import kleberlz.apiprodutos.domain.model.Produto;



public class ProdutoBuilder {
	private UUID id;
	private String nome;
	private BigDecimal preco;
	private String descricao;

	public ProdutoBuilder comNome(String nome) {
		this.nome = nome;
		return this;
	}
	
	public ProdutoBuilder comPreco(BigDecimal preco) {
		this.preco = preco;
		return this;
	}
	
	public ProdutoBuilder comDescricao(String descricao) {
		this.descricao = descricao;
		return this;
	}
	
	public ProdutoBuilder comID(UUID id) {
		this.id = id;
		return this;
	}
	
	public Produto build() {
		return new Produto(id, nome, preco, descricao);
	}

}
