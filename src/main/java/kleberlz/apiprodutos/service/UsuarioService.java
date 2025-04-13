package kleberlz.apiprodutos.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kleberlz.apiprodutos.domain.model.Usuario;
import kleberlz.apiprodutos.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
// Contem a lógica de negócios. 
public class UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final PasswordEncoder encoder;

	public void salvar(Usuario usuario) {
		var senha = usuario.getSenha();
		usuario.setSenha(encoder.encode(senha));
		usuarioRepository.save(usuario);
	}

	public Usuario obterPorLogin(String login) {
		return usuarioRepository.findByLogin(login);
	}

	public Usuario obterPorEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

}
