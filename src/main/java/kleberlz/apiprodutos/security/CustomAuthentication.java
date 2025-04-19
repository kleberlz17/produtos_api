package kleberlz.apiprodutos.security;


import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import kleberlz.apiprodutos.domain.model.Usuario;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@SuppressWarnings("serial")
@RequiredArgsConstructor
@Getter
public class CustomAuthentication implements Authentication {
	
	private final Usuario usuario;
	
	public Collection<GrantedAuthority> getAuthorities() {
		//Pega as roles do Usuario, mapeia elas pro SimpleGrantedAuthority
		return this.usuario
				.getRoles()
				.stream()
				.map(SimpleGrantedAuthority::new) //Chama construtor pra remover a ROLE.
				.collect(Collectors.toList());
	}

	@Override
	public String getName() {
		return usuario.getLogin();
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getDetails() {
		return usuario;
	}

	@Override
	public Object getPrincipal() {
		return usuario;
	}

	@Override
	public boolean isAuthenticated() {
		return true;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {	
	}

}
