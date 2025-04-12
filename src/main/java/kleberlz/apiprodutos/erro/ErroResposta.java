package kleberlz.apiprodutos.erro;

import java.util.List;

import org.springframework.http.HttpStatus;

public record ErroResposta(int status, String mensagem, List<ErroCampo> erros) {
	// Unico erro com a mensagem padronizada é o erroValidacao pq não tem diferença em que ele for usado.
	// Já os outros, respostaPadrao e Conflito, podem vir a ter mensagens diferentes dependendo de onde sao acionados.
	
	public static ErroResposta respostaPadrao(String mensagem) {
		return new ErroResposta(HttpStatus.BAD_REQUEST.value(), mensagem, List.of());
	}
	
	public static ErroResposta conflito(String mensagem) {
		return new ErroResposta(HttpStatus.CONFLICT.value(), mensagem, List.of());
	}
	
	public static ErroResposta erroValidacao(List<ErroCampo> erros) {
		return new ErroResposta(HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", erros);
	}

}
