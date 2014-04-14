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
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

import br.ufla.lemaf.tramitacao.consts.SCHEMAS;

@Entity
@Table(name = "CONFIG_SITUACAO", schema = SCHEMAS.TRAMITACAO)
public class ConfigSituacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(name = "ID_CONFIG_SITUACAO")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "tramitacao.config_situacao_id_config_situacao_seq")
	@SequenceGenerator(name = "tramitacao.config_situacao_id_config_situacao_seq", sequenceName = "tramitacao.config_situacao_id_config_situacao_seq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_SITUACAO", referencedColumnName = "ID_SITUACAO")
	@NotNull
	private Situacao situacao;
	
	@ManyToOne
	@JoinColumn(name = "ID_TRANSICAO", referencedColumnName = "ID_TRANSICAO")
	@NotNull
	private Transicao transicao;
	
	@Column(name = "FL_ADICIONAR")
	private Integer adicionar;
	
	@Column(name = "FL_PAI")
	private Integer pai;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Situacao getSituacao() {
		return situacao;
	}

	public void setSituacao(Situacao situacao) {
		this.situacao = situacao;
	}

	@JsonIgnore
	public Transicao getTransicao() {
		return transicao;
	}

	public void setTransicao(Transicao transicao) {
		this.transicao = transicao;
	}

	public Integer getAdicionar() {
		return adicionar;
	}

	public void setAdicionar(Integer adicionar) {
		this.adicionar = adicionar;
	}

	public Integer getPai() {
		return pai;
	}

	public void setPai(Integer pai) {
		this.pai = pai;
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
		ConfigSituacao other = (ConfigSituacao) obj;
		if (this.id == null) {
			return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
