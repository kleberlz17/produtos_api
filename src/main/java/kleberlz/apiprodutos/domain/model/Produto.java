package kleberlz.apiprodutos.domain.model;

import java.math.BigDecimal;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getters, setters, hashcode, toString
@Entity
@NoArgsConstructor // Criar Construtor sem argumentos(vazio)(obrigatório para JPA)
@AllArgsConstructor // Criar Construtor com todos os atributos
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	private String nome;
	private BigDecimal preco;
	private String descricao;

}
