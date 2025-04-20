package kleberlz.apiprodutos.mappers;


import org.mapstruct.Mapper;


import kleberlz.apiprodutos.domain.model.Produto;
import kleberlz.apiprodutos.dto.ProdutoDTO;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {
	
	//Ignora o mapeamento do campo "id" ao converter ProdutoDTO para Produto
	//Id de Produto é gerado pelo banco de dados, não devo mapear esse campo pq ele será atribuido
	//automaticamente pelo JPA ao salvar a entidade.
//	@Mapping(target = "id", ignore = true)
	Produto toEntity(ProdutoDTO dto);

	ProdutoDTO toDTO(Produto produto);

}
