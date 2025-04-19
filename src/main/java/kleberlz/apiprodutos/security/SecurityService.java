package kleberlz.apiprodutos.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import kleberlz.apiprodutos.domain.model.Usuario;
import kleberlz.apiprodutos.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityService {
	//Ajuda a acessar o usuário logado de forma simples ao invés de ficar pedindo autenticação
	//diretamente em várias partes do meu código por exemplo.
	
	@SuppressWarnings("unused")
	private final UsuarioService usuarioService;
	
	public Usuario obterUsuarioLogado() {
		//Abaixo, SpringSecurity guarda no contexto da requisição a autenticação atual, quem está
		//acessando o endpoint e verifica se é CustomAuthentication
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(authentication instanceof CustomAuthentication customAuth) {
			return customAuth.getUsuario();//Caso seja, é retornado o usuario logado armazenado na autenticação.
		}
		
		return null;
	}

}
