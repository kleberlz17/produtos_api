package kleberlz.apiprodutos.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kleberlz.apiprodutos.domain.model.Client;
import kleberlz.apiprodutos.repository.ClientRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor //Injeta automaticamente as dependencias abaixo. ClientRepository e PasswordEncoder.
public class ClientService {
	
	private final ClientRepository clientRepository;
	private final PasswordEncoder encoder;
	
	public Client salvar(Client client) {
		var senhaCriptografada = encoder.encode(client.getClientSecret()); //Pega o clientSecret que esta no objeto Client
																		  //em texto puro.
		
		//encoder irá criptografar esse clientSecret e guardar em senhaCriptografada.
		
		client.setClientSecret(senhaCriptografada);//senhaCriptograda é setada no objeto client, substituindo
													// a em texto puro.
		return clientRepository.save(client);
	}
	//Apenas busca um Client pelo seu clientID usando o findByClientId do repositorio.
	public Client obterPorClientID(String clientId) {
		return clientRepository.findByClientId(clientId);
	}

}
