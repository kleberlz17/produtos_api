package kleberlz.apiprodutos.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kleberlz.apiprodutos.domain.model.Usuario;

// Responsavel pela persistência de dados dos usuários no banco de dados.
public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {
	
	Usuario findByLogin(String login);
	
	Usuario findByEmail(String email);
	

}
