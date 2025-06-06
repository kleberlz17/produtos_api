package kleberlz.apiprodutos.domain.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class Client {
	//Toda a construção por trás da classe Client (model, repository, service...)
	//é a estrutura interna da API pra que dados que vêm do front-end (ou Postman por exemplo)
	//possam ser recebidos, processados,  atualizados, validados, transformados e salvos no banco de dados.
	
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private UUID id;
	
	@Column(name = "client_id")
	private String clientId;
	
	@Column(name = "client_secret")
	private String clientSecret;
	
	@Column(name = "redirect_uri")
	private String redirectURI;
	
	@Column(name = "scope")
	private String scope;

}
