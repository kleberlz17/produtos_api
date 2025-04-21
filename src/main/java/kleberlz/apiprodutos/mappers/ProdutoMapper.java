package kleberlz.apiprodutos.mappers;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import kleberlz.apiprodutos.domain.model.Produto;
import kleberlz.apiprodutos.dto.ProdutoDTO;



@Mapper(componentModel = "spring")
public interface ProdutoMapper {
	
	//Ignora o mapeamento do campo "id" ao converter ProdutoDTO para Produto
	//Id de Produto é gerado pelo banco de dados, não devo mapear esse campo pq ele será atribuido
	//automaticamente pelo JPA ao salvar a entidade.
	
	@Mapping(source = "id", target = "id")
	@Mapping(source = "nome", target = "nome")
	@Mapping(source = "preco", target = "preco")
	@Mapping(source = "descricao", target = "descricao")
	Produto toEntity(ProdutoDTO dto);


	ProdutoDTO toDTO(Produto produto);

}
