package kleberlz.apiprodutos.security;

import java.io.IOException;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kleberlz.apiprodutos.domain.model.Usuario;
import kleberlz.apiprodutos.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LoginSocialSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private static final String SENHA_PADRAO = "123";
	private final UsuarioService usuarioService;
	
				// Aqui acontece a criação/save do novo usuário.
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		OAuth2AuthenticationToken auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
		OAuth2User oAuth2User = auth2AuthenticationToken.getPrincipal();
		
		String email = oAuth2User.getAttribute("email");
		
		Usuario usuario = usuarioService.obterPorEmail(email);
		
		if(usuario == null) {
			usuario = cadastrarUsuarioNaBase(email);
		}
		
		authentication = new CustomAuthentication(usuario);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}
	
	private Usuario cadastrarUsuarioNaBase(String email) {
		Usuario usuario;
		usuario = new Usuario();
		usuario.setEmail(email);
		usuario.setLogin(ObterLoginApartirEmail(email));
		usuario.setSenha(SENHA_PADRAO);
		usuario.setRoles(List.of("OPERADOR"));
		
		usuarioService.salvar(usuario);
		return usuario;
	}
	
	private String ObterLoginApartirEmail(String email) {
		return email.substring(0, email.indexOf("@")); // O login será gerado a partir do indice 0 até a ultima
														// letra antes do "@" ex kleberluiz <- @
	}

}
