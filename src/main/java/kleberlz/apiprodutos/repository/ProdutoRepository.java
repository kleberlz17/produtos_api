package kleberlz.apiprodutos.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kleberlz.apiprodutos.domain.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

	List<Produto> findByNomeIgnoreCase(String nome);

}
