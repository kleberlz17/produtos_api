package kleberlz.apiprodutos.mappers;


import org.mapstruct.Mapper;


import kleberlz.apiprodutos.domain.model.Produto;
import kleberlz.apiprodutos.dto.ProdutoDTO;

@Mapper(componentModel = "spring")
public interface ProdutoMapper {

	Produto toEntity(ProdutoDTO dto);

	ProdutoDTO toDTO(Produto produto);

}
