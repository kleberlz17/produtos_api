package kleberlz.apiprodutos.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import kleberlz.apiprodutos.domain.model.Usuario;
import kleberlz.apiprodutos.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetailsService implements  UserDetailsService {
	//Busca e monta um usuário do sistema a partir dos dados salvos no banco (via UsuarioService).
	
	private final UsuarioService usuarioService;
	
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		
		//O metodo procura no banco pelo login via usuarioService.obterPorLogin, 
		Usuario usuario = usuarioService.obterPorLogin(login);
		
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado.");
		}
		
		//se for encontrado ele é transformado em um objeto UserDetails(classe padrão do Spring)
		//UserDetails é utilizado pelo Spring para verificar senha, validar permissões(roles)
		return User.builder()
				.username(usuario.getLogin())
				.password(usuario.getSenha())
				.roles(usuario.getRoles().toArray(new String[usuario.getRoles().size()]))
				.build();
	}

}
