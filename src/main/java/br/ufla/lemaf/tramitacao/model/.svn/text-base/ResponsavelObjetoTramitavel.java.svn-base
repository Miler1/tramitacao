package br.ufla.lemaf.tramitacao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.ufla.lemaf.tramitacao.model.mbpo.GrupoUsuario;
import br.ufla.lemaf.tramitacao.model.mbpu.Orgao;
import br.ufla.lemaf.tramitacao.model.mbpu.UnidadeAdministrativa;

@Entity
@Table(name = "RESPONSAVEL_OBJETO_TRAMITAVEL")
public class ResponsavelObjetoTramitavel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_RESP_OBJETO_TRAMITAVEL")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_RESP_OBJETO_TRAMITAVEL")
	@SequenceGenerator(name = "SEQ_RESP_OBJETO_TRAMITAVEL", sequenceName = "SEQ_RESP_OBJETO_TRAMITAVEL")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_GRUPO_USUARIO", referencedColumnName = "SEQ_GRUPO_USUARIO")
	private GrupoUsuario grupoUsuario;
	
	@ManyToOne
	@JoinColumn(name = "ID_UNIDADE_ADM", referencedColumnName = "ID_AREA_PLANEJAMENTO")
	private UnidadeAdministrativa unidadeAdministrativa;
	
	@ManyToOne
	@JoinColumn(name = "ID_ORGAO", referencedColumnName = "ID")
	private Orgao orgao;
	
	public ResponsavelObjetoTramitavel() {
		
	}
	
	public ResponsavelObjetoTramitavel(ResponsavelObjetoTramitavel responsavelObjetoTramitavel) {
		
		super();
		
		if (responsavelObjetoTramitavel == null)
			return;
		
		this.grupoUsuario = responsavelObjetoTramitavel.grupoUsuario;
		this.unidadeAdministrativa = responsavelObjetoTramitavel.unidadeAdministrativa;
		this.orgao = responsavelObjetoTramitavel.orgao;
	}
	
	public void limpar() {
		this.grupoUsuario = null;
		this.unidadeAdministrativa = null;
		this.orgao = null;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public GrupoUsuario getGrupoUsuario() {
		return grupoUsuario;
	}

	public void setGrupoUsuario(GrupoUsuario grupoUsuario) {
		this.grupoUsuario = grupoUsuario;
	}

	public UnidadeAdministrativa getUnidadeAdministrativa() {
		return unidadeAdministrativa;
	}

	public void setUnidadeAdministrativa(UnidadeAdministrativa unidadeAdministrativa) {
		this.unidadeAdministrativa = unidadeAdministrativa;
	}

	public Orgao getOrgao() {
		return orgao;
	}

	public void setOrgao(Orgao orgao) {
		this.orgao = orgao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return (id == null) ? 0 : (id.intValue() % prime);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null || this.id == null || this.getClass() != obj.getClass())
			return false;
		
		ResponsavelObjetoTramitavel other = (ResponsavelObjetoTramitavel) obj;
		
		boolean grupoUsuarioDiferente = (this.grupoUsuario == null && other.grupoUsuario != null) ||
									(this.grupoUsuario != null && !this.grupoUsuario.equals(other.grupoUsuario));
		if (grupoUsuarioDiferente)
			return false;
		
		boolean unidadeAdmDiferente = (this.unidadeAdministrativa == null && other.unidadeAdministrativa != null) ||
									(this.unidadeAdministrativa != null && !this.unidadeAdministrativa.equals(other.unidadeAdministrativa));
		if (unidadeAdmDiferente)
			return false;
		
		boolean orgaoDiferente = (this.orgao == null && other.orgao != null) ||
									(this.orgao != null && !this.orgao.equals(other.orgao));
		if (orgaoDiferente)
			return false;
		
		return true;
	}
}
