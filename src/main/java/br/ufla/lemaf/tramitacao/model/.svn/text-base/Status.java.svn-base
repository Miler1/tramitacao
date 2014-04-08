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
@Table( name = "STATUS" )
public class Status implements Serializable {

	public static final Status INITIAL_PSEUDO_STATE = new Status( Long.valueOf( 0 ) );

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column( name = "ID_STATUS" )
	@GeneratedValue( strategy = GenerationType.AUTO, generator = "SEQ_STATUS" )
	@SequenceGenerator( name = "SEQ_STATUS", sequenceName = "SEQ_STATUS" )
	private Long id;

	@ManyToOne
	@JoinColumn( name = "ID_ETAPA", referencedColumnName = "ID_ETAPA" )
	private Etapa etapa;

	@Column( name = "NM_STATUS" )
	@Size( max = 100 )
	private String nome;

	@Column( name = "FL_ATIVO" )
	private Integer ativo;

	public Status() {

	}

	public Status( Long id ) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Etapa getEtapa() {
		return etapa;
	}

	public String getNome() {
		return nome;
	}

	public Integer getAtivo() {
		return ativo;
	}
	
	public boolean isStatusOutroFluxo(Status novoStatus) {
		
		if (this.etapa == null || novoStatus == null || novoStatus.getEtapa() == null)
			return false;
		
		return this.etapa.isEtapaOutroFluxo( novoStatus.getEtapa() );
	}

	@Override
	public int hashCode() {
		final int hashSize = 31;
		return ( id == null ) ? 0 : ( id.intValue() % hashSize );
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || this.id == null || this.getClass() != obj.getClass())
			return false;
		
		Status other = (Status) obj;
		
		return this.id.equals(other.id);
	}
}
