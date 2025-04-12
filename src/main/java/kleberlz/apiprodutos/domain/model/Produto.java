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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // getters, setters, hashcode, toString
@Entity
@NoArgsConstructor // Criar Construtor sem argumentos(vazio)(obrigatório para JPA)
@AllArgsConstructor // Criar Construtor com todos os atributos
@Table(name = "produtos") // Evitar conflitos
@EntityListeners(AuditingEntityListener.class)
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "nome", length = 100, nullable = false )
	private String nome;
	
	@Column(name = "preco", nullable = false, precision = 10, scale = 2) // valor terá até 2 digitos, sendo 2 depois da vírgula
	private BigDecimal preco;
	
	@Column(name = "descricao", nullable = true)
	private String descricao;

}
