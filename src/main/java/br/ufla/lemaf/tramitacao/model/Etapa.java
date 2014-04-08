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
import javax.validation.constraints.Size;

@Entity
@Table(name = "ETAPA")
public class Etapa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(name = "ID_ETAPA")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_ETAPA")
	@SequenceGenerator(name = "SEQ_ETAPA", sequenceName = "SEQ_ETAPA")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_FLUXO", referencedColumnName = "ID_FLUXO")
	private Fluxo fluxo;
	
	@Column(name = "TX_ETAPA")
	@Size(max = 2000)
	private String texto;
	
	@Column(name = "DT_PRAZO")
	private Integer prazo;

	public Long getId() {
		return id;
	}

	public Fluxo getFluxo() {
		return fluxo;
	}

	public String getTexto() {
		return texto;
	}

	public Integer getPrazo() {
		return prazo;
	}
	
	public boolean isEtapaOutroFluxo(Etapa novaEtapa) {
		
		if (this.fluxo == null || novaEtapa == null || novaEtapa.getFluxo() == null)
			return false;
		
		return !this.fluxo.equals( novaEtapa.getFluxo() );
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
		if (obj == null || this.id == null || this.getClass() != obj.getClass())
			return false;
		
		Etapa other = (Etapa) obj;
		
		return this.id.equals(other.id);
	}
}
