package kleberlz.apiprodutos.security;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kleberlz.apiprodutos.domain.model.Usuario;
import kleberlz.apiprodutos.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtCustomAuthenticationFilter extends OncePerRequestFilter {
	
	private UsuarioService usuarioService;
	
	@Override 
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
			FilterChain filterChain) throws ServletException, IOException {
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		if(deveConverter(authentication)) {
			String login = authentication.getName(); // Extrai o login (usuário) do token
			Usuario usuario = usuarioService.obterPorLogin(login);// Busca o usuário completo no banco com
																  // usuarioService.
			if(usuario != null) {
				authentication = new CustomAuthentication(usuario);// Substitui autenticação padrão por 
																   // uma customizada, contendo objeto Usuário.
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
		
		filterChain.doFilter(request, response); // Somente segue o fluxo.
	}
	// Verifica se a autenticação existe e se ela é do tipo Token JWT.
	private boolean deveConverter(Authentication authentication) {
		return authentication != null && authentication instanceof JwtAuthenticationToken;
	}

}
