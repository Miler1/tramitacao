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

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.ufla.lemaf.tramitacao.consts.SCHEMAS;

@Entity
@Table(name = "ACAO", schema = SCHEMAS.TRAMITACAO)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Acao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(name = "ID_ACAO")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ACAO")
	@SequenceGenerator(name = "SEQ_ACAO", sequenceName = "SEQ_ACAO")
	private Long id;
	
	@Column(name = "TX_DESCRICAO")
	@Size(max = 1000)
	private String descricao;
	
	@Column(name = "FL_ATIVO")
	private Integer ativo;

	@Column(name = "FL_TRAMITAVEL")
	private Integer tramitavel;
	
	public Acao() {
		
	}
	
	public Acao(Long id) {
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

	public void setTramitavel(Integer tramitavel) {
		this.tramitavel = tramitavel;
	}

	public Integer getTramitavel() {
		return tramitavel;
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
		Acao other = (Acao) obj;
		if (this.id == null) {
			return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
