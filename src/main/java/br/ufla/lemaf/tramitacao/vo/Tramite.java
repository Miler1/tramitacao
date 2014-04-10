package br.ufla.lemaf.tramitacao.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.ufla.lemaf.tramitacao.model.Acao;
import br.ufla.lemaf.tramitacao.model.Fluxo;
import br.ufla.lemaf.tramitacao.model.ObjetoTramitavel;
import br.ufla.lemaf.tramitacao.model.TipoObjetoTramitavel;
import br.ufla.lemaf.tramitacao.model.Transicao;
import br.ufla.lemaf.tramitacao.model.usrgeocar.Usuario;
import br.ufla.lemaf.tramitacao.util.Mensagem;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Tramite implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private ObjetoTramitavel objetoTramitavel;
	
	private Acao acao;
	
	private Long idUsuarioExecutor;
	
	private Long idUsuarioDestino;
	
	private Mensagem mensagem;
	
	private String observacao;
	
	/** usuário que executou a tramitação anterior. */
	private Usuario ultimoUsuarioExecutor;
	
	private Transicao transicao;
	
	public Tramite() {
		super();
	}
	
	/**
	 * Contrutor com os parâmetros necessários para se iniciar uma tramitação.
	 */
	public Tramite(Fluxo fluxo,
				   TipoObjetoTramitavel tipoObjetoTramitavel,
				   Long idUsuarioExecutor,
				   Long idUsuarioDestino) {

		this.objetoTramitavel = new ObjetoTramitavel();
		
		if (fluxo != null) {
			this.objetoTramitavel.setFluxo(fluxo);
		}
		
		if (tipoObjetoTramitavel != null) {
			this.objetoTramitavel.setTipo(tipoObjetoTramitavel);
		}
		
		if (idUsuarioExecutor != null) {
			this.idUsuarioExecutor = idUsuarioExecutor;
		}
		
		if (idUsuarioDestino != null) {
			this.idUsuarioDestino = idUsuarioDestino;
		}
		
	}
	
	public Tramite(ObjetoTramitavel objetoTramitavel, Acao acao,
			Long idUsuarioExecutor, Long idUsuarioDestino) {
		this.objetoTramitavel = objetoTramitavel;
		this.acao = acao;
		this.idUsuarioExecutor = idUsuarioExecutor;
		this.idUsuarioDestino = idUsuarioDestino;
	}
	
	public Tramite(ObjetoTramitavel objetoTramitavel, Mensagem mensagem) {
		this.objetoTramitavel = objetoTramitavel;
		this.mensagem = mensagem;
	}
	
	public Tramite(Mensagem mensagem) {
		this.mensagem = mensagem;
	}
	
	public Tramite(Usuario ultimoUsuarioExecutor) {
		this.ultimoUsuarioExecutor = ultimoUsuarioExecutor;
	}

	public ObjetoTramitavel getObjetoTramitavel() {
		return objetoTramitavel;
	}

	public Acao getAcao() {
		return acao;
	}
	
	public Long getIdUsuarioExecutor() {
		return idUsuarioExecutor;
	}

	public Long getIdUsuarioDestino() {
		return idUsuarioDestino;
	}

	public void setObjetoTramitavel(ObjetoTramitavel objetoTramitavel) {
		this.objetoTramitavel = objetoTramitavel;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}
	
	public void setIdUsuarioExecutor(Long idUsuarioExecutor) {
		this.idUsuarioExecutor = idUsuarioExecutor;
	}

	public void setIdUsuarioDestino(Long idUsuarioDestino) {
		this.idUsuarioDestino = idUsuarioDestino;
	}

	public void setMensagem(Mensagem mensagem) {
		this.mensagem = mensagem;
	}

	public Mensagem getMensagem() {
		return mensagem;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setUltimoUsuarioExecutor(Usuario ultimoUsuarioExecutor) {
		this.ultimoUsuarioExecutor = ultimoUsuarioExecutor;
	}

	public Usuario getUltimoUsuarioExecutor() {
		return ultimoUsuarioExecutor;
	}

	public void setTransicao(Transicao transicao) {
		this.transicao = transicao;
	}

	@JsonIgnore
	public Transicao getTransicao() {
		return transicao;
	}
	
}
