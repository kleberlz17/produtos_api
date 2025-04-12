package kleberlz.apiprodutos.exceptions;

@SuppressWarnings("serial")
public class OperacaoNaoPermitidaException extends RuntimeException {
	
	public OperacaoNaoPermitidaException(String mensagem) {
		super(mensagem);
	}

}
