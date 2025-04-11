package kleberlz.apiprodutos.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;

import kleberlz.apiprodutos.domain.model.Produto;
import kleberlz.apiprodutos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
// 3 - RECEBE O DTO E EXECUTA A REGRA DE NEGÓCIO. EX: CHAMAR O BUILDER,VALIDAR ALGO, CHAMA REPOSITORIO.
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	public Produto salvar(Produto produto) {
		log.info("Salvando produto: {}", produto.getNome());
		return produtoRepository.save(produto);
	}

	public Optional<Produto> obterPorId(UUID id) {
		log.info("Buscando produto por ID: {}", id);
		return produtoRepository.findById(id);
	}
	
	public void deletar(Produto produto) {
		log.info("Deletando produto com ID: {}", produto.getId());
		produtoRepository.delete(produto);
	}
	
	public List<Produto> pesquisaByExample(String nome){
		var produto = new Produto();
		produto.setNome(nome);
		
		ExampleMatcher matcher = ExampleMatcher
				.matching()
				.withIgnorePaths("id", "preco", "descricao")
				.withIgnoreNullValues()
				.withIgnoreCase()
				.withStringMatcher(StringMatcher.CONTAINING);
		Example<Produto> produtoExample = Example.of(produto, matcher);
		return produtoRepository.findAll(produtoExample);
	}
	
	public void atualizar(Produto produto) {
		if(produto.getId() == null) {
			log.warn("Tentativa de atualizar produto inexistente!");
			throw new IllegalArgumentException("Pra atualizar é necessário que o produto esteja cadastrado na base");
		}
		log.info("Atualizando produto com ID: {}", produto.getId());
		produtoRepository.save(produto);
	}

}
