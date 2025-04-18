package kleberlz.apiprodutos.security;



import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kleberlz.apiprodutos.domain.model.Usuario;
import kleberlz.apiprodutos.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
// Provedor de Autenticação customizada.
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	private final UsuarioService usuarioService;
	private final PasswordEncoder encoder;
	
	@Override //Chamado automaticamente quando alguem tenta se autenticar.
			  // Authentication Pega login e senhaDigitada .
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String login = authentication.getName();
		String senhaDigitada = authentication.getCredentials().toString();
		
		//Busca o usuário no banco pelo login >>
		Usuario usuarioEncontrado = usuarioService.obterPorLogin(login);
		
		//Se não achar, erro .
		if(usuarioEncontrado == null) {
			throw getErroUsuarioNaoEncontrado();
		}
		
		String senhaCriptografada = usuarioEncontrado.getSenha();
		
		//Comparação de senhas. Digitada e e criptografada que está salva no banco.
		boolean senhasBatem = encoder.matches(senhaDigitada, senhaCriptografada);
		
		if(senhasBatem) {//Cria novo objeto CustomAuthentication passando o usuarioEncontrado.
			return new CustomAuthentication(usuarioEncontrado);
		}
		
		throw getErroUsuarioNaoEncontrado();
	}
	
	private UsernameNotFoundException getErroUsuarioNaoEncontrado() {
		return new UsernameNotFoundException("Usuário e/ou senha incorretos!");
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		//Aqui diz pra qual tipo de autenticação esse provider serve.
		//Se receber uma UsernamePasswordAuthenticationToken, ele usa esse Provider acima para autenticar.
		return authentication.isAssignableFrom(UsernamePasswordAuthenticationToken.class);
	}

}
