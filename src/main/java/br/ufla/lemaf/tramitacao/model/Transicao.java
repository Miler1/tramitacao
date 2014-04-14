package br.ufla.lemaf.tramitacao.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.ufla.lemaf.tramitacao.consts.SCHEMAS;
import br.ufla.lemaf.tramitacao.consts.SimNao;

@Entity
@Table(name = "TRANSICAO", schema = SCHEMAS.TRAMITACAO)
public class Transicao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(name = "ID_TRANSICAO")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "tramitacao.transicao_id_transicao_seq")
	@SequenceGenerator(name = "tramitacao.transicao_id_transicao_seq", sequenceName = "tramitacao.transicao_id_transicao_seq")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_ACAO", referencedColumnName = "ID_ACAO")
	private Acao acao;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONDICAO_INICIAL", referencedColumnName = "ID_CONDICAO")
	private Condicao statusInicial;
	
	@ManyToOne
	@JoinColumn(name = "ID_CONDICAO_FINAL", referencedColumnName = "ID_CONDICAO")
	private Condicao statusFinal;
	
	@Column(name = "DT_PRAZO")
	private Integer prazo;
	
	@Column(name = "FL_RETORNAR_FLUXO_ANTERIOR")
	private Integer retornarFluxoAnterior;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(schema = SCHEMAS.TRAMITACAO, name = "REL_EVENTO_TRANSICAO",
			joinColumns = { @JoinColumn(name = "ID_TRANSICAO", referencedColumnName = "ID_TRANSICAO") },
			inverseJoinColumns = { @JoinColumn(name = "ID_EVENTO", referencedColumnName = "ID_EVENTO") } )
	private Set<Evento> eventos;
	
	@OneToMany(mappedBy = "transicao", fetch = FetchType.EAGER)
	private Set<Impedimento> impedimentos;
	
	@Transient
	private Collection<ConfigSituacao> configuracoesImpedimento;

	public Long getId() {
		return id;
	}

	public Acao getAcao() {
		return acao;
	}

	public Condicao getStatusInicial() {
		return statusInicial;
	}

	public Condicao getStatusFinal() {
		return statusFinal;
	}

	public Integer getPrazo() {
		return prazo;
	}
	
	public boolean isRetornarFluxoAnterior() {
		return this.retornarFluxoAnterior != null && this.retornarFluxoAnterior.equals( SimNao.SIM );
	}
	
	public Set<Evento> getEventos() {
		return eventos;
	}
	
	public Set<Impedimento> getImpedimentos () {
		return impedimentos;
	}
	
	public Collection<ConfigSituacao> getConfiguracoesImpedimento() {
		return configuracoesImpedimento;
	}

	public void setConfiguracoesImpedimento(Collection<ConfigSituacao> configuracoesImpedimento) {
		this.configuracoesImpedimento = configuracoesImpedimento;
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
		Transicao other = (Transicao) obj;
		if (this.id == null) {
			return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
