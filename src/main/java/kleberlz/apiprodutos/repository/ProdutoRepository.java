package kleberlz.apiprodutos.repository;


import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import kleberlz.apiprodutos.domain.model.Produto;

// 4 - INTERAGE COM O BANCO DE DADOS(SALVA,BUSCA,ETC)
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

	Optional<Produto> findByNomeIgnoreCase(String nome);

}
