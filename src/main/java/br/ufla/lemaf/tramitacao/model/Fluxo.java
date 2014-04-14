package br.ufla.lemaf.tramitacao.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.codehaus.jackson.annotate.JsonIgnore;

import br.ufla.lemaf.tramitacao.consts.SCHEMAS;

@Entity
@Table(name = "FLUXO", schema = SCHEMAS.TRAMITACAO)
public class Fluxo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(name = "ID_FLUXO")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "tramitacao.fluxo_id_fluxo_seq")
	@SequenceGenerator(name = "tramitacao.fluxo_id_fluxo_seq", sequenceName = "tramitacao.fluxo_id_fluxo_seq")
	private Long id;
	
	@OneToOne
	@JoinColumn(name = "ID_STATUS_INICIAL", referencedColumnName = "ID_CONDICAO")
	private Condicao statusInicial;
	
	@Column(name = "TX_DESCRICAO")
	@Size(max = 1000)
	private String descricao;
	
	@Column(name = "DT_PRAZO")
	private Integer prazo;
	
	public Fluxo() {
		
	}
	
	public Fluxo(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return id;
	}

	@JsonIgnore
	public Condicao getStatusInicial() {
		return statusInicial;
	}

	public String getDescricao() {
		return descricao;
	}

	public Integer getPrazo() {
		return prazo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setStatusInicial(Condicao statusInicial) {
		this.statusInicial = statusInicial;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setPrazo(Integer prazo) {
		this.prazo = prazo;
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
		
		Fluxo other = (Fluxo) obj;
		
		return this.id.equals(other.id);
	}
}
