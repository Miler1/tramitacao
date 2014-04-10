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
@Table(name = "EVENTO", schema = SCHEMAS.TRAMITACAO)
public class Evento implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(name = "ID_EVENTO")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_EVENTO")
	@SequenceGenerator(name = "SEQ_EVENTO", sequenceName = "SEQ_EVENTO")
	private Long id;
	
	@Column(name = "TX_DESCRICAO")
	@Size(max = 100)
	private String descricao;

	public Long getId() {
		return id;
	}

	public String getDescricao() {
		return descricao;
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
		Evento other = (Evento) obj;
		if (this.id == null) {
			return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
