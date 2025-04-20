package kleberlz.apiprodutos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import kleberlz.apiprodutos.domain.model.Usuario;
import kleberlz.apiprodutos.repository.UsuarioRepository;
// SOMENTE TESTES UNITÁRIOS AQUI.// Testa sem envolver o banco de dados, teste isolado.
class UsuarioServiceTest {
	
	@InjectMocks
	private UsuarioService usuarioService;
	
	@Mock
	private UsuarioRepository usuarioRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void deveSalvarUsuarioComSenhaCriptografada() {
		
		Usuario usuario = new Usuario();
		usuario.setLogin("kleber");
		usuario.setEmail("kleber@email.com");
		usuario.setSenha("senha123");
		
		String senhaCriptografada = "encodedSenha123";
		//quando o passwordEncoder der encode na senha digitada senha123, ele retorna a senhaCriptografada acima.
		// ao inves de realmente criptografar alguma coisa.
		when(passwordEncoder.encode("senha123")).thenReturn(senhaCriptografada);
		
		usuarioService.salvar(usuario);
		
		assertEquals(senhaCriptografada, usuario.getSenha());
		verify(usuarioRepository).save(usuario);
	}
	
	@Test
	void deveObterUsuarioPorLogin() {
		
		Usuario usuario = new Usuario();
		usuario.setLogin("kleber");
        // quando alguem chamar o metodo findbylogin(kleber), o usuario que criei acima será retornado no lugar.
		// ao inves de realmente procurar o login.
		when(usuarioRepository.findByLogin("kleber")).thenReturn(usuario);
		
		Usuario resultado = usuarioService.obterPorLogin("kleber");
		
		assertEquals("kleber", resultado.getLogin());
		verify(usuarioRepository).findByLogin("kleber");
	}
	
	@Test
	void deveObterUsuarioPorEmail() {
		
		Usuario usuario = new Usuario();
		usuario.setEmail("kleber@email.com");
		
		
		when(usuarioRepository.findByEmail("kleber@email.com")).thenReturn(usuario);
		
		Usuario resultado = usuarioService.obterPorEmail("kleber@email.com");
	
		assertEquals("kleber@email.com", resultado.getEmail());
		verify(usuarioRepository).findByEmail("kleber@email.com");
	}
	

}
