package br.ufla.lemaf.tramitacao.exception;

import org.codehaus.jackson.annotate.JsonIgnore;

public class TramitacaoException extends RuntimeException {

	private String textoMensagem;
	private String textoLog;
	
	public TramitacaoException() {
	}
	
	public TramitacaoException(String message) {
		super(message);
		this.textoMensagem = message;
		this.textoLog = message;
	}
	
	@JsonIgnore
	public TramitacaoException mensagem(String mensagem) {
		
		this.textoMensagem = mensagem;
		return this;
	}
	
	@JsonIgnore
	public TramitacaoException log(String log) {
		
		this.textoLog = log;
		return this;
	}

	public String getTextoMensagem() {
		return textoMensagem;
	}

	public String getTextoLog() {
		return textoLog;
	}
}
