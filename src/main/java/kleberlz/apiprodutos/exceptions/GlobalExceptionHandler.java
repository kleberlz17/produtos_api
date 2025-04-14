package kleberlz.apiprodutos.exceptions;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import kleberlz.apiprodutos.erro.ErroCampo;
import kleberlz.apiprodutos.erro.ErroResposta;
import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
	
	@ExceptionHandler(OperacaoNaoPermitidaException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErroResposta handleOperacaoNaoPermitidaException(OperacaoNaoPermitidaException exception) {
		return ErroResposta.respostaPadrao(exception.getMessage());
	}
	
	@ExceptionHandler(RegistroDuplicadoException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ErroResposta handleRegistroDuplicadoException(RegistroDuplicadoException exception) {
		return ErroResposta.conflito(exception.getMessage());
	}
	
	@ExceptionHandler(CampoInvalidoException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ErroResposta handleCampoInvalidoException(CampoInvalidoException exception) {
		return ErroResposta.erroValidacao(List.of(new ErroCampo(exception.getCampo(), exception.getMessage())));
		
	}
	// Aqui é Method generico, estou tratando ele. Erro de validação no DTO vai ter resposta padronizada.
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
	public ErroResposta handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
		List<ErroCampo> erros = exception.getBindingResult().getFieldErrors()
				.stream().map(erro -> new ErroCampo(erro.getField(), erro.getDefaultMessage())).toList();
		
		return ErroResposta.erroValidacao(erros);
	}
	// Quando enviarem ID errado(ex: abcdef), o sistema vai retornar a mensagem abaixo.
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErroResposta> handleIllegalArgumentException(IllegalArgumentException exception) {
		var erro = ErroResposta.respostaPadrao("ID inválido. O formato deve ser UUID.");
		return ResponseEntity.badRequest().body(erro);
	}

	
}
