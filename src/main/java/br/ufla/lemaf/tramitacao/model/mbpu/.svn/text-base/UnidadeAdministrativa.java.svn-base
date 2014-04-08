package br.ufla.lemaf.tramitacao.model.mbpu;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ufla.lemaf.tramitacao.consts.SCHEMAS;

@Entity
@Table(name = "TB_UNIDADE_ADM", schema = SCHEMAS.MBPU)
public class UnidadeAdministrativa implements Serializable {

	private static final long serialVersionUID = 1L;

	public UnidadeAdministrativa() {
	}
	
	public UnidadeAdministrativa(Long id) {
		this.id = id;
	}
	
	@Id
	@Column(name = "ID_AREA_PLANEJAMENTO")
	private Long id;
	
	@Column(name = "SIGLA")
	private String sigla;
	
	@Column(name = "DESCRICAO")
	private String descricao;
	
	@Column(name = "FLG_STATUS")
	private Character status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Character getStatus() {
		return status;
	}

	public void setStatus(Character status) {
		this.status = status;
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
		
		UnidadeAdministrativa other = (UnidadeAdministrativa) obj;
		
		return this.id.equals(other.id);
	}
}
