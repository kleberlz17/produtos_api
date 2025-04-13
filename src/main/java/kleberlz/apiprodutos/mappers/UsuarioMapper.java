package kleberlz.apiprodutos.mappers;

import org.mapstruct.Mapper;

import kleberlz.apiprodutos.domain.model.Usuario;
import kleberlz.apiprodutos.dto.UsuarioDTO;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
	
	Usuario toEntity(UsuarioDTO dto);

}
