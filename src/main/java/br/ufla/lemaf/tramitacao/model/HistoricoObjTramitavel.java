package br.ufla.lemaf.tramitacao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import br.ufla.lemaf.tramitacao.model.mbpu.UsuarioInterno;
import br.ufla.lemaf.tramitacao.util.DateTimeSerializer;

@Entity
@Table(name = "HISTORICO_OBJETO_TRAMITAVEL")
public class HistoricoObjTramitavel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "ID_HISTORICO_OBJETO_TRAMITAVEL")
	@NotNull
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_HIST_OBJETO_TRAMITAVEL")
	@SequenceGenerator(name = "SEQ_HIST_OBJETO_TRAMITAVEL", sequenceName = "SEQ_HIST_OBJETO_TRAMITAVEL")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_OBJETO_TRAMITAVEL", referencedColumnName = "ID_OBJETO_TRAMITAVEL", nullable=false)
	private ObjetoTramitavel objetoTramitavel;
	
	@Column(name = "ID_ACAO")
	private Long idAcao;
	
	@Column(name = "TX_ACAO")
	private String nomeAcao;
	
	@Column(name = "ID_STATUS_INICIAL")
	private Long idStatusInicial;
	
	@Column(name = "TX_STATUS_INICIAL")
	private String nomeStatusInicial;

	@Column(name = "ID_STATUS_FINAL")
	private Long idStatusFinal;
	
	@Column(name = "TX_STATUS_FINAL")
	private String nomeStatusFinal;
	
	@Column(name = "ID_ETAPA_INICIAL")
	private Long idEtapaInicial;
	
	@Column(name = "TX_ETAPA_INICIAL")
	private String nomeEtapaInicial;
	
	@Column(name = "ID_ETAPA_FINAL")
	private Long idEtapaFinal;
	
	@Column(name = "TX_ETAPA_FINAL")
	private String nomeEtapaFinal;
	
	@Column(name = "ID_USUARIO")
	private Long idUsuario;
	
	@Column(name = "TX_USUARIO")
	private String nomeUsuario;
	
	@Column(name = "ID_USUARIO_DESTINO")
	private Long idUsuarioDestino;
	
	@Column(name = "TX_USUARIO_DESTINO")
	private String nomeUsuarioDestino;
	
//	@Column(name = "ID_GRUPO_USUARIO_DESTINO")
//	private Long idGrupoUsuarioDestino;
//	
//	@Column(name = "TX_GRUPO_USUARIO_DESTINO")
//	private String nomeGrupoUsuarioDestino;
//	
//	@Column(name = "ID_UNIDADE_ADM_DESTINO")
//	private Long idUnidadeAdministrativaDestino;
//	
//	@Column(name = "TX_UNIDADE_ADM_DESTINO")
//	private String nomeUnidadeAdministrativaDestino;
//	
//	@Column(name = "ID_ORGAO_DESTINO")
//	private Long idOrgaoDestino;
//	
//	@Column(name = "TX_ORGAO_DESTINO")
//	private String nomeOrgaoDestino;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_CADASTRO")
	private Date dataCadastro;

	@Column(name = "TX_OBSERVACAO")
	@Size(max = 1000)
	private String observacao;
	
	public HistoricoObjTramitavel() {
		super();
	}

	public HistoricoObjTramitavel(ObjetoTramitavel objetoTramitavel, Acao acao, Status statusInicial, Status statusFinal,
			UsuarioInterno usuarioExecutor, UsuarioInterno usuarioDestino, 
			//ResponsavelObjetoTramitavel responsavelDestino,
			Date dataCadastro, String observacao) {

		super();

		this.objetoTramitavel = objetoTramitavel;
		this.idAcao = acao.getId();
		this.nomeAcao = acao.getDescricao();
		this.idStatusInicial = statusInicial.getId();
		this.nomeStatusInicial = statusInicial.getNome();
		this.idStatusFinal = statusFinal.getId();
		this.nomeStatusFinal = statusFinal.getNome();
		this.idEtapaInicial = (statusInicial.getEtapa() == null) ? null : statusInicial.getEtapa().getId();
		this.nomeEtapaInicial = (statusInicial.getEtapa() == null) ? null : statusInicial.getEtapa().getTexto();
		this.idEtapaFinal = (statusFinal.getEtapa() == null) ? null : statusFinal.getEtapa().getId();
		this.nomeEtapaFinal = (statusFinal.getEtapa() == null) ? null : statusFinal.getEtapa().getTexto();
		this.dataCadastro = new Date();
		this.observacao = observacao;
		
		setUsuario(usuarioExecutor);
		setUsuarioDestino(usuarioDestino);
		//setResponsavelDestino(responsavelDestino);
	}
	
	private void setUsuario(UsuarioInterno usuarioExecutor) {
		
		if (usuarioExecutor != null && usuarioExecutor.getPessoa() != null) {
			
			this.idUsuario = usuarioExecutor.getId();
			this.nomeUsuario = usuarioExecutor.getPessoa().getNomeRazaoSocial();
		}
	}
	
	private void setUsuarioDestino(UsuarioInterno usuarioDestino) {
		
		if (usuarioDestino != null && usuarioDestino.getPessoa() != null) {
			
			this.idUsuarioDestino = usuarioDestino.getId();
			this.nomeUsuarioDestino = usuarioDestino.getPessoa().getNomeRazaoSocial();
		}
	}
	
//	private void setResponsavelDestino(ResponsavelObjetoTramitavel responsavelDestino) {
//		
//		if (responsavelDestino != null) {
//			
//			GrupoUsuario grupoUsuarioDestino = responsavelDestino.getGrupoUsuario();
//			UnidadeAdministrativa unidadeAdmDestino = responsavelDestino.getUnidadeAdministrativa();
//			Orgao orgaoDestino = responsavelDestino.getOrgao();
//			
//			if (grupoUsuarioDestino != null) {
//				this.idGrupoUsuarioDestino = grupoUsuarioDestino.getId();
//				this.nomeGrupoUsuarioDestino = grupoUsuarioDestino.getDescricao();
//			}
//			
//			if (unidadeAdmDestino != null) {
//				this.idUnidadeAdministrativaDestino = unidadeAdmDestino.getId();
//				this.nomeUnidadeAdministrativaDestino = unidadeAdmDestino.getDescricao();
//			}
//			
//			if (orgaoDestino != null) {
//				this.idOrgaoDestino = orgaoDestino.getId();
//				this.nomeOrgaoDestino = orgaoDestino.getDescricao();
//			}
//		}
//	}

	public Long getId() {
		return id;
	}

	@JsonIgnore
	public ObjetoTramitavel getObjetoTramitavel() {
		return objetoTramitavel;
	}

	public Long getIdAcao() {
		return idAcao;
	}

	public String getNomeAcao() {
		return nomeAcao;
	}

	public Long getIdStatusInicial() {
		return idStatusInicial;
	}

	public String getNomeStatusInicial() {
		return nomeStatusInicial;
	}

	public Long getIdStatusFinal() {
		return idStatusFinal;
	}

	public String getNomeStatusFinal() {
		return nomeStatusFinal;
	}
	
	public Long getIdEtapaInicial() {
		return idEtapaInicial;
	}
	
	public String getNomeEtapaInicial() {
		return nomeEtapaInicial;
	}
	
	public Long getIdEtapaFinal() {
		return idEtapaFinal;
	}
	
	public String getNomeEtapaFinal() {
		return nomeEtapaFinal;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public Long getIdUsuarioDestino() {
		return idUsuarioDestino;
	}

	public String getNomeUsuarioDestino() {
		return nomeUsuarioDestino;
	}

//	public Long getIdGrupoUsuarioDestino() {
//		return idGrupoUsuarioDestino;
//	}
//
//	public String getNomeGrupoUsuarioDestino() {
//		return nomeGrupoUsuarioDestino;
//	}
//
//	public Long getIdUnidadeAdministrativaDestino() {
//		return idUnidadeAdministrativaDestino;
//	}
//
//	public String getNomeUnidadeAdministrativaDestino() {
//		return nomeUnidadeAdministrativaDestino;
//	}
//
//	public Long getIdOrgaoDestino() {
//		return idOrgaoDestino;
//	}
//
//	public String getNomeOrgaoDestino() {
//		return nomeOrgaoDestino;
//	}

	@JsonSerialize(using = DateTimeSerializer.class)
	public Date getDataCadastro() {
		return dataCadastro;
	}

	public String getObservacao() {
		return observacao;
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
		HistoricoObjTramitavel other = (HistoricoObjTramitavel) obj;
		if (this.id == null) {
			return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
