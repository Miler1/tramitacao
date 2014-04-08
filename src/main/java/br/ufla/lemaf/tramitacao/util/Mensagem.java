package br.ufla.lemaf.tramitacao.util;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Mensagem implements Serializable {

	private static final long serialVersionUID = 1L;

	public static final String ERRO = "error";
	public static final String SUCESSO = "success";
	public static final String MSG_ERRO_PADRAO = "A operação não pode ser executada, verifique sua conexão e tente novamente.";
	private String tipo;
	private List<String> mensagens = new LinkedList<String>();
	private List<String> informacoesComplementares = new LinkedList<String>();

	public Mensagem() {
		
	}
	
	public Mensagem(String tipo) {
		this.tipo = tipo;
	}

	public Mensagem(String tipo, String mensagem) {
		this(tipo);
		this.addMensagem(mensagem);
	}

	public void addMensagem(String message) {
		this.mensagens.add(message);
	}
	
	public void addInformacaoComplementar(String informacaoComplementar) {
		this.informacoesComplementares.add(informacaoComplementar);
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<String> getMensagens() {
		return mensagens;
	}

	public void setMensagens(List<String> mensagens) {
		this.mensagens = mensagens;
	}

	public List<String> getInformacoesComplementares() {
		return informacoesComplementares;
	}

	public void setInformacoesComplementares(List<String> informacoesComplementares) {
		this.informacoesComplementares = informacoesComplementares;
	}

}
