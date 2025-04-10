package kleberlz.apiprodutos.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import kleberlz.apiprodutos.domain.builder.ProdutoBuilder;
import kleberlz.apiprodutos.domain.model.Produto;
import kleberlz.apiprodutos.dto.ProdutoDTO;
import kleberlz.apiprodutos.service.ProdutoService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
public class ProdutoController {

	private final ProdutoService produtoService;

	@PostMapping
	public ResponseEntity<Produto> salvar(@RequestBody @Valid ProdutoDTO dto) {
		Produto produto = new ProdutoBuilder()
				.comID(dto.id())
				.comNome(dto.nome())
				.comPreco(dto.preco())
				.comDescricao(dto.descricao())
				.build();

		Produto salvo = produtoService.salvar(produto);
		return ResponseEntity.ok(salvo);

	}

}
