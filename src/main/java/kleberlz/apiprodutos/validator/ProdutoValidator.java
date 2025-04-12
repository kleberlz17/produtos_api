package kleberlz.apiprodutos.validator;

import java.util.Optional;


import org.springframework.stereotype.Component;

import kleberlz.apiprodutos.domain.model.Produto;
import kleberlz.apiprodutos.exceptions.RegistroDuplicadoException;
import kleberlz.apiprodutos.repository.ProdutoRepository;

@Component
public class ProdutoValidator {
	
	private ProdutoRepository produtoRepository;
	
	public ProdutoValidator(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	public void validar(Produto produto) {
		if(existeProdutoCadastrado(produto)) {
			throw new RegistroDuplicadoException("Produto já cadastrado!");
		}
	}
	
	private boolean existeProdutoCadastrado(Produto produto) {
		Optional<Produto> produtoEncontrado = produtoRepository.findByNomeIgnoreCase(produto.getNome());
		
		if(produto.getId() == null) {
			return produtoEncontrado.isPresent();
		}
		
		return !produto.getId().equals(produtoEncontrado.get().getId()) && produtoEncontrado.isPresent();
	}

}
