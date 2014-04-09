package br.ufla.lemaf.tramitacao.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import br.ufla.lemaf.tramitacao.model.mbpu.UsuarioInterno;
import br.ufla.lemaf.tramitacao.util.DateTimeDeserializer;
import br.ufla.lemaf.tramitacao.util.DateTimeSerializer;

@Entity
@Table(name = "OBJETO_TRAMITAVEL")
@JsonIgnoreProperties(ignoreUnknown = true)
public class ObjetoTramitavel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column(name = "ID_OBJETO_TRAMITAVEL")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_OBJETO_TRAMITAVEL")
	@SequenceGenerator(name = "SEQ_OBJETO_TRAMITAVEL", sequenceName = "SEQ_OBJETO_TRAMITAVEL")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "ID_STATUS", referencedColumnName = "ID_STATUS")
	private Status status;

	@ManyToOne
	@JoinColumn(name = "ID_ETAPA", referencedColumnName = "ID_ETAPA")
	private Etapa etapa;

	@ManyToOne
	@JoinColumn(name = "ID_FLUXO", referencedColumnName = "ID_FLUXO")
	private Fluxo fluxo;

	@ManyToOne
	@JoinColumn(name = "ID_TIPO_OBJETO_TRAMITAVEL", referencedColumnName = "ID_TIPO_OBJETO_TRAMITAVEL")
	private TipoObjetoTramitavel tipo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CRIACAO_OBJETO_TRAMITAVEL")
	private Date dataCriacao;

	@ManyToOne
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "ID_PESSOA_FISICA")
	private UsuarioInterno usuario;

	@ManyToOne
	@JoinColumn(name = "ID_PAI", referencedColumnName = "ID_OBJETO_TRAMITAVEL")
	private ObjetoTramitavel pai;
	
	@ManyToOne
	@JoinColumn(name = "ID_RESPONSAVEL_ANTERIOR", referencedColumnName = "ID_PESSOA_FISICA")
	private UsuarioInterno responsavelAnterior;
	
	@ManyToOne
	@JoinColumn(name = "ID_STATUS_FLUXO_ANTERIOR", referencedColumnName = "ID_STATUS")
	private Status statusFluxoAnterior;
	
	@ManyToOne
	@JoinColumn(name = "ID_RESPONSAVEL_FLUXO_ANTERIOR", referencedColumnName = "ID_PESSOA_FISICA")
	private UsuarioInterno responsavelFluxoAnterior;

	@ManyToMany(cascade = {CascadeType.MERGE}, fetch = FetchType.EAGER)
	@JoinTable(name = "REL_OBJETO_TRAMITAVEL_SITUACAO",
			   joinColumns = { @JoinColumn(name = "ID_OBJETO_TRAMITAVEL", referencedColumnName = "ID_OBJETO_TRAMITAVEL") },
			   inverseJoinColumns = { @JoinColumn(name = "ID_SITUACAO", referencedColumnName = "ID_SITUACAO") } )
	private Collection<Situacao> situacoes;
	
//	@OneToOne(cascade = {CascadeType.ALL})
//	@JoinColumn(name = "ID_RESP_OBJETO_TRAMITAVEL", referencedColumnName = "ID_RESP_OBJETO_TRAMITAVEL")
//	private ResponsavelObjetoTramitavel responsavel;
//	
//	@OneToOne(cascade = {CascadeType.ALL})
//	@JoinColumn(name = "ID_RESP_OBJ_TRAMIT_ANTERIOR", referencedColumnName = "ID_RESP_OBJETO_TRAMITAVEL")
//	private ResponsavelObjetoTramitavel responsavelObjetoTramitavelAnterior;
	
	@Transient
	private Collection<HistoricoObjTramitavel> historico;
	
	@Transient
	private Collection<Acao> acoesDisponiveis;

	public ObjetoTramitavel() {
		super();
	}

	public ObjetoTramitavel(Long id) {
		this.id = id;
	}

	public ObjetoTramitavel(Long id, Long idTipo) {
		this.id = id;
		this.tipo = new TipoObjetoTramitavel(idTipo);
	}

	public Long getId() {
		return id;
	}

	public Status getStatus() {
		return status;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public Fluxo getFluxo() {
		return fluxo;
	}

	public TipoObjetoTramitavel getTipo() {
		return tipo;
	}

	@JsonSerialize(using = DateTimeSerializer.class)
	public Date getDataCriacao() {
		return dataCriacao;
	}

	public UsuarioInterno getUsuario() {
		return usuario;
	}

	public ObjetoTramitavel getPai() {
		return pai;
	}

	public Collection<Situacao> getSituacoes() {
		return situacoes;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatus(Status novoStatus) {
		
		boolean alterouFluxo = this.status != null && this.status.isStatusOutroFluxo(novoStatus);
		
		if (alterouFluxo) {
			
			this.setStatusFluxoAnterior(this.status);
			this.setResponsavelFluxoAnterior(this.usuario);
		}
		
		this.status = novoStatus;
		this.etapa = novoStatus.getEtapa();
		this.fluxo = (novoStatus.getEtapa() != null) ? novoStatus.getEtapa().getFluxo() : null;
	}

	public void setEtapa(Etapa etapa) {
		this.etapa = etapa;
	}

	public void setFluxo(Fluxo fluxo) {
		this.fluxo = fluxo;
	}

	public void setTipo(TipoObjetoTramitavel tipo) {
		this.tipo = tipo;
	}

	@JsonDeserialize(using = DateTimeDeserializer.class)
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public void setUsuario(UsuarioInterno novoUsuario) {
		
		boolean alterouUsuarioResponsavel = (this.usuario != null && !this.usuario.equals(novoUsuario)) ||
											(novoUsuario != null && !novoUsuario.equals(this.usuario));
		
		if (alterouUsuarioResponsavel)
			this.setResponsavelAnterior( this.usuario);
		
		this.usuario = novoUsuario;
	}

	public void setPai(ObjetoTramitavel pai) {
		this.pai = pai;
	}

	public UsuarioInterno getResponsavelAnterior() {
		return responsavelAnterior;
	}

	private void setResponsavelAnterior(UsuarioInterno responsavelAnterior) {
		this.responsavelAnterior = responsavelAnterior;
	}

	public Status getStatusFluxoAnterior() {
		return statusFluxoAnterior;
	}

	public void setStatusFluxoAnterior(Status statusFluxoAnterior) {
		this.statusFluxoAnterior = statusFluxoAnterior;
	}

	public UsuarioInterno getResponsavelFluxoAnterior() {
		return responsavelFluxoAnterior;
	}

	public void setResponsavelFluxoAnterior(UsuarioInterno responsavelFluxoAnterior) {
		this.responsavelFluxoAnterior = responsavelFluxoAnterior;
	}

	public void setSituacoes(Collection<Situacao> situacoes) {
		this.situacoes = situacoes;
	}

	public void setHistorico(Collection<HistoricoObjTramitavel> historico) {
		this.historico = historico;
	}

	public Collection<HistoricoObjTramitavel> getHistorico() {
		return historico;
	}

	public void setAcoesDisponiveis(Collection<Acao> acoesDisponiveis) {
		this.acoesDisponiveis = acoesDisponiveis;
	}

	public Collection<Acao> getAcoesDisponiveis() {
		return acoesDisponiveis;
	}
	
//	public ResponsavelObjetoTramitavel getResponsavel() {
//		return responsavel;
//	}
//	
//	public ResponsavelObjetoTramitavel getResponsavelObjetoTramitavelAnterior() {
//		return responsavelObjetoTramitavelAnterior;
//	}
//	
//	private void setResponsavelObjetoTramitavelAnterior(ResponsavelObjetoTramitavel responsavel) {
//		
//		if ( this.responsavelObjetoTramitavelAnterior == null )
//			this.responsavelObjetoTramitavelAnterior = new ResponsavelObjetoTramitavel();
//		
//		if ( responsavel == null ) {
//			
//			this.responsavelObjetoTramitavelAnterior.limpar();
//			
//		} else {
//			
//			this.responsavelObjetoTramitavelAnterior.setGrupoUsuario( responsavel.getGrupoUsuario() );
//			this.responsavelObjetoTramitavelAnterior.setUnidadeAdministrativa( responsavel.getUnidadeAdministrativa() );
//			this.responsavelObjetoTramitavelAnterior.setOrgao( responsavel.getOrgao() );
//		}
//	}
//
//	public void setResponsavel(ResponsavelObjetoTramitavel novoResponsavel) {
//		
//		boolean alterouResponsavel = ( this.responsavel != null && !this.responsavel.equals(novoResponsavel)) ||
//									(novoResponsavel != null && !novoResponsavel.equals(this.responsavel) );
//
//		if ( !alterouResponsavel )
//			return;
//		
//		setResponsavelObjetoTramitavelAnterior( this.responsavel );
//		
//		if ( this.responsavel == null )
//			this.responsavel = new ResponsavelObjetoTramitavel();
//
//		if ( novoResponsavel == null ) {
//			
//			this.responsavel.limpar();
//			
//		} else {
//		
//			this.responsavel.setGrupoUsuario( novoResponsavel.getGrupoUsuario() );
//			this.responsavel.setUnidadeAdministrativa( novoResponsavel.getUnidadeAdministrativa() );
//			this.responsavel.setOrgao( novoResponsavel.getOrgao() );
//		}
//	}

	public void adicionarSituacao(Situacao situacao) {
		
		if (this.situacoes == null)
			this.situacoes = new ArrayList<Situacao>();
		
		this.situacoes.add( situacao );
	}
	
	public void removerSituacao(Situacao situacao) {
		
		if (this.situacoes == null)
			return;
		
		this.situacoes.remove(situacao);
	}
	
	public void setObjetoParaRetornarParaFluxoAnterior() {
		
		Status novoStatus = this.statusFluxoAnterior;
		UsuarioInterno novoResponsavel = this.responsavelFluxoAnterior;
		
		setStatus(novoStatus);
		setUsuario(novoResponsavel);
		
//		if (responsavel != null)
//			responsavel.limpar();
	}
	
	@Override
	public int hashCode() {
		final int hashSize = 31;
		return (id == null) ? 0 : (id.intValue() % hashSize);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		ObjetoTramitavel other = (ObjetoTramitavel) obj;
		if (this.id == null) {
			return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
