package kleberlz.apiprodutos.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Example;
import org.mockito.ArgumentMatchers;

import kleberlz.apiprodutos.domain.model.Produto;
import kleberlz.apiprodutos.exceptions.RegistroDuplicadoException;
import kleberlz.apiprodutos.repository.ProdutoRepository;
import kleberlz.apiprodutos.validator.ProdutoValidator;
//SOMENTE TESTES UNITÁRIOS AQUI
class ProdutoServiceTest {
	
	@Mock
	private ProdutoRepository produtoRepository;
	
	@Mock
	private ProdutoValidator produtoValidator;
	
	@InjectMocks
	private ProdutoService produtoService;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void deveSalvarProdutoComSucesso() {
		Produto produto = new Produto(UUID.randomUUID(), "Notebook", new BigDecimal("7500.00"), "Dell XPS");
		
		when(produtoRepository.findByNomeIgnoreCase(produto.getNome())).thenReturn(Optional.empty());
		when(produtoRepository.save(produto)).thenReturn(produto);
		
		Produto resultado = produtoService.salvar(produto);
		
		assertEquals(produto, resultado);
		verify(produtoRepository).save(produto);
	}
	
	@Test
	void deveLancarErroAoSalvarProdutoDuplicado() {
		Produto produto = new Produto(UUID.randomUUID(), "Notebook", new BigDecimal("7500.00"), "Dell XPS");
		
		when(produtoRepository.findByNomeIgnoreCase(produto.getNome())).thenReturn(Optional.of(produto));
		
		assertThrows(RegistroDuplicadoException.class, () -> produtoService.salvar(produto));
		verify(produtoRepository, never()).save(any());
	}
	
	@Test
	void deveObterProdutoPorId() {
		UUID id = UUID.randomUUID();
		Produto produto = new Produto(id, "Monitor", new BigDecimal("1200.00"), "Samsung");
		
		when(produtoRepository.findById(id)).thenReturn(Optional.of(produto));
		
		Optional<Produto> resultado = produtoService.obterPorId(id);
		
		assertTrue(resultado.isPresent());
		assertEquals(produto, resultado.get());	
	}
	
	@Test
	void deveAtualizarProduto() {
		UUID id = UUID.randomUUID();
		Produto produto = new Produto(id, "Mouse", new BigDecimal("200.00"), "Logitech");
		
		produtoService.atualizar(produto);
		
		verify(produtoRepository).save(produto);
	}
	
	@Test
	void deveDeletarProduto() {
		Produto produto = new Produto(UUID.randomUUID(), "Teclado", new BigDecimal("300.00"), "Redragon");
		
		produtoService.deletar(produto);
		
		verify(produtoRepository).delete(produto);
	}
	
	@Test
	void devePesquisarProdutoPorNome() {
		String nome = "notebook";
		
		Produto produtoMock = new Produto(UUID.randomUUID(), "Notebook", new BigDecimal("6500.00"), "Dell");
		List<Produto> produtosMock = List.of(produtoMock);
	
		
		when(produtoRepository.findAll(ArgumentMatchers.<Example<Produto>>any())).thenReturn(produtosMock);
		
		List<Produto> resultado = produtoService.pesquisaByExample(nome);
		
		assertEquals(1, resultado.size());
		assertEquals("Notebook", resultado.get(0).getNome());
	}

}
