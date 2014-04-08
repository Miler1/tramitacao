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

@Entity
@Table(name = "SITUACAO")
public class Situacao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(name = "ID_SITUACAO")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_SITUACAO")
	@SequenceGenerator(name = "SEQ_SITUACAO", sequenceName = "SEQ_SITUACAO")
	private Long id;
	
	@Column(name = "TX_DESCRICAO")
	@Size(max = 100)
	private String descricao;
	
	@Column(name = "FL_ATIVO")
	private Integer ativo;

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getAtivo() {
		return ativo;
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
		Situacao other = (Situacao) obj;
		if (this.id == null) {
			return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
