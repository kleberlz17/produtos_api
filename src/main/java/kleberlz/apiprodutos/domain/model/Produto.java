package kleberlz.apiprodutos.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "produtos") // Evitar conflitos
@EntityListeners(AuditingEntityListener.class)
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "nome", length = 100, nullable = false)
	private String nome;
	
	@Column(name = "preco", nullable = false, precision = 10, scale = 2) // valor terá até 2 digitos, sendo 2 depois da vírgula
	private BigDecimal preco;
	
	@Column(name = "descricao", nullable = true)
	private String descricao;

	public Produto(UUID id, String nome, BigDecimal preco, String descricao) {
		super();
		this.id = id;
		this.nome = nome;
		this.preco = preco;
		this.descricao = descricao;
	}

	public Produto() {
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

	@Override
	public String toString() {
		return "Produto [id=" + id + ", nome=" + nome + ", preco=" + preco + ", descricao=" + descricao + "]";
	}

}
