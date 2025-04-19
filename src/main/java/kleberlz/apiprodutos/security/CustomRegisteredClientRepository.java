package kleberlz.apiprodutos.security;

import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Component;



import kleberlz.apiprodutos.service.ClientService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CustomRegisteredClientRepository implements RegisteredClientRepository {
	//Pega os dados de um cliente ja cadastrado diretamente do banco via ClientService.
	//Exemplo: endpoint da API /client com post, onde alguém envia clientId,
	//clientSecret, redirectUri, etc. Ou eu mesmo insiro direto no banco de dados via SQL,etc.
	
	private final ClientService clientService;
	private final TokenSettings tokenSettings;
	private final ClientSettings clientSettings;
	
	@Override
	public void save(RegisteredClient registeredClient) {} //Não usado no momento.
	
	@Override
	public RegisteredClient findById(String id) {//Não usado no momento.
		return null;
	}
	
	@Override
	public RegisteredClient findByClientId(String clientId) {
		var client = clientService.obterPorClientID(clientId); //Busca no banco de dados pelo clientId.
		
		if(client == null) {//se não achar.
			return null;
		}
		
		return RegisteredClient//se achar cria um RegisteredClient(Cliente autorizado)
				.withId(client.getId().toString())
				.clientId(client.getClientId())
				.clientSecret(client.getClientSecret())
				.redirectUri(client.getRedirectURI())
				.scope(client.getScope())
				.clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_BASIC)
				
				//Abaixo, esse cliente novo pode usar Auth Code, Client Credentials e Refresh Token.
				.authorizationGrantType(AuthorizationGrantType.AUTHORIZATION_CODE)
				.authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
				.authorizationGrantType(AuthorizationGrantType.REFRESH_TOKEN)
				.tokenSettings(tokenSettings)
				.clientSettings(clientSettings)
				.build();
	}
	
	

}
