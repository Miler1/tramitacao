package br.ufla.lemaf.tramitacao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnore;

import br.ufla.lemaf.tramitacao.consts.SCHEMAS;

@Entity
@Table(name = "IMPEDIMENTO_TRANSICAO", schema = SCHEMAS.TRAMITACAO)
public class Impedimento implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(name = "ID_IMPEDIMENTO_TRANSICAO")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_SITUACAO", referencedColumnName = "ID_SITUACAO")
	@NotNull
	private Situacao situacao;
	
	@ManyToOne
	@JoinColumn(name = "ID_TRANSICAO", referencedColumnName = "ID_TRANSICAO")
	@NotNull
	private Transicao transicao;
	
	@Column(name = "TP_IMPEDIMENTO")
	private Integer tipo;

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

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
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
		Impedimento other = (Impedimento) obj;
		if (this.id == null) {
			return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
	
}
