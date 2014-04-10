package br.ufla.lemaf.tramitacao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufla.lemaf.tramitacao.consts.SCHEMAS;

@Entity
@Table(name = "TIPO_OBJETO_TRAMITAVEL", schema = SCHEMAS.TRAMITACAO)
public class TipoObjetoTramitavel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(name = "ID_TIPO_OBJETO_TRAMITAVEL")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TIPO_OBJETO_TRAMITAVEL")
	@SequenceGenerator(name = "SEQ_TIPO_OBJETO_TRAMITAVEL", sequenceName = "SEQ_TIPO_OBJETO_TRAMITAVEL")
	private Long id;
	
	@Column(name = "TX_DESCRICAO")
	@Size(max = 200)
	private String descricao;
	
	@Column(name = "FL_ATIVO")
	private Integer ativo;
	
	public TipoObjetoTramitavel() {
		
	}
	
	public TipoObjetoTramitavel(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getAtivo() {
		return ativo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setAtivo(Integer ativo) {
		this.ativo = ativo;
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
		TipoObjetoTramitavel other = (TipoObjetoTramitavel) obj;
		if (this.id == null) {
			return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
