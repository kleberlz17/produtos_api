package kleberlz.apiprodutos.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kleberlz.apiprodutos.domain.model.Produto;
import kleberlz.apiprodutos.dto.ProdutoDTO;
import kleberlz.apiprodutos.mappers.ProdutoMapper;
import kleberlz.apiprodutos.service.ProdutoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/produtos")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Produtos")
// 1 - RECEBE REQUISIÇÃO DA HTTP(POST,GET, ETC) da API
public class ProdutoController implements GenericController {

	private final ProdutoService produtoService;
	private final ProdutoMapper produtoMapper;

	@PostMapping
	@Operation(summary = "Salvar", description = "Salva um novo produto.")
	public ResponseEntity<Object> salvar(@RequestBody @Valid ProdutoDTO dto) {
		log.info("Produto cadastrado com sucesso: {}", dto.nome());

		Produto produto = produtoMapper.toEntity(dto);
		produtoService.salvar(produto);

		URI location = gerarHeaderLocation(produto.getId());
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("{id}")
	@Operation(summary = "Deletar", description = "Deleta um produto existente.")
	public ResponseEntity<Void> deletar(@PathVariable("idProduto") String id) {
		log.info("Produto com ID {} deletado com sucesso", id);
		var idProduto = UUID.fromString(id);
		Optional<Produto> produtoOptional = produtoService.obterPorId(idProduto);

		if (produtoOptional.isEmpty()) {
			return ResponseEntity.notFound().build();

		}
		produtoService.deletar(produtoOptional.get());

		return ResponseEntity.noContent().build();
	}
	
	@GetMapping
	@Operation(summary = "Pesquisar", description = "Realiza pesquisa de produtos por parametros.")
	public ResponseEntity<List<ProdutoDTO>> pesquisar(
			@RequestParam(value = "nome", required = false) String nome) {
		
		List<Produto> resultado = produtoService.pesquisaByExample(nome);
		List<ProdutoDTO> lista = resultado.stream().map(produtoMapper::toDTO).collect(Collectors.toList());
		
		return ResponseEntity.ok(lista);
	}
	@PutMapping("{id}")
	@Operation(summary = "Atualizar", description = "Atualiza um produto existente")
	public ResponseEntity<Void> atualizar(@PathVariable("idProduto") String id, @RequestBody @Valid ProdutoDTO dto) {
		
		var idProduto = UUID.fromString(id);
		Optional<Produto> produtoOptional = produtoService.obterPorId(idProduto);
		
		if(produtoOptional.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		var produto = produtoOptional.get();
		produto.setNome(dto.nome());
		produto.setPreco(dto.preco());
		produto.setDescricao(dto.descricao());
		
		produtoService.atualizar(produto);
		
		return ResponseEntity.noContent().build();
		
	}
	
	

}
