package kleberlz.apiprodutos.exceptions;

@SuppressWarnings("serial")
public class RegistroDuplicadoException extends RuntimeException {
	
	public RegistroDuplicadoException(String message) {
		super(message);
	}

}
