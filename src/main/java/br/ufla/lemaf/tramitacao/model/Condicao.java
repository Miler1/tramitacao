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

import br.ufla.lemaf.tramitacao.consts.SCHEMAS;

@Entity
@Table(name = "CONDICAO", schema = SCHEMAS.TRAMITACAO)
public class Condicao implements Serializable {

	public static final Condicao INITIAL_PSEUDO_STATE = new Condicao( Long.valueOf( 0 ) );

	private static final long serialVersionUID = 1L;

	@Id
	@NotNull
	@Column( name = "ID_CONDICAO" )
	@GeneratedValue( strategy = GenerationType.AUTO, generator = "tramitacao.condicao_id_condicao_seq" )
	@SequenceGenerator(name = "tramitacao.condicao_id_condicao_seq", sequenceName = "tramitacao.condicao_id_condicao_seq" )
	private Long id;

	@ManyToOne
	@JoinColumn( name = "ID_ETAPA", referencedColumnName = "ID_ETAPA" )
	private Etapa etapa;

	@Column( name = "NM_CONDICAO" )
	@Size( max = 100 )
	private String nome;

	@Column( name = "FL_ATIVO" )
	private Integer ativo;

	public Condicao() {

	}

	public Condicao( Long id ) {
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
	
	public boolean isCondicaoOutroFluxo(Condicao novaCondicao) {
		
		if (this.etapa == null || novaCondicao == null || novaCondicao.getEtapa() == null)
			return false;
		
		return this.etapa.isEtapaOutroFluxo( novaCondicao.getEtapa() );
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
		
		Condicao other = (Condicao) obj;
		
		return this.id.equals(other.id);
	}
}
