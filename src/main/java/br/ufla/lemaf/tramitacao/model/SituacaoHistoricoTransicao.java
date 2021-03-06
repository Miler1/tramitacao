package br.ufla.lemaf.tramitacao.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.ufla.lemaf.tramitacao.consts.SCHEMAS;

@Entity
@Table(name = "REL_SITUACAO_HISTORICO_TRANSICAO", schema = SCHEMAS.TRAMITACAO)
public class SituacaoHistoricoTransicao implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotNull
	@Column(name = "ID_REL_SITUACAO_HISTORICO_TRAN")
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "tramitacao.REL_SITUACAO_HISTORICO_TRANSICAO_ID_REL_SITUACAO_HISTORICO_TRAN")
	@SequenceGenerator(name = "tramitacao.REL_SITUACAO_HISTORICO_TRANSICAO_ID_REL_SITUACAO_HISTORICO_TRAN", sequenceName = "tramitacao.REL_SITUACAO_HISTORICO_TRANSICAO_ID_REL_SITUACAO_HISTORICO_TRAN")
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "ID_SITUACAO", referencedColumnName = "ID_SITUACAO")
	private Situacao situacao;
	
	@ManyToOne
	@JoinColumn(name = "ID_HISTORICO_OBJETO_TRAMITAVEL", referencedColumnName = "ID_HISTORICO_OBJETO_TRAMITAVEL")
	private HistoricoObjTramitavel historicoTransicao;
	
	@Column(name = "DT_FINAL")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataFinal;
	
	@Column(name = "TX_OBSERVACAO")
	@Size(max = 1000)
	private String observacao;

	public Long getId() {
		return id;
	}

	public Situacao getSituacao() {
		return situacao;
	}

//	public HistoricoObjTramitavel getHistoricoTransicao() {
//		return historicoTransicao;
//	}

	public Date getDataFinal() {
		return dataFinal;
	}

	public String getObservacao() {
		return observacao;
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
		SituacaoHistoricoTransicao other = (SituacaoHistoricoTransicao) obj;
		if (this.id == null) {
			return false;
		} else if (!this.id.equals(other.id))
			return false;
		return true;
	}
}
