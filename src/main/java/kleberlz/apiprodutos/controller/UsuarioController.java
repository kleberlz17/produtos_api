package kleberlz.apiprodutos.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kleberlz.apiprodutos.dto.UsuarioDTO;
import kleberlz.apiprodutos.mappers.UsuarioMapper;
import kleberlz.apiprodutos.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários")
public class UsuarioController {
	
	private final UsuarioService usuarioService;
	private final UsuarioMapper usuarioMapper;
	
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(summary = "Salvar", description = "Cadastrar novo usuário")
	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Cadastrado com sucesso."),
		@ApiResponse(responseCode = "422", description = "Erro de validação."),
		@ApiResponse(responseCode = "409", description = "Usuário já cadastrado.")
	})
	public void salvar(@RequestBody @Valid UsuarioDTO dto) {
		var usuario = usuarioMapper.toEntity(dto);
		usuarioService.salvar(usuario);
		
	}

}
