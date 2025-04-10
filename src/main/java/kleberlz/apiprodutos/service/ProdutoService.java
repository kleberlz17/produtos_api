package kleberlz.apiprodutos.service;

import org.springframework.stereotype.Service;

import kleberlz.apiprodutos.domain.model.Produto;
import kleberlz.apiprodutos.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {

	private final ProdutoRepository produtoRepository;

	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
}
