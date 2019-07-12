package br.ufla.lemaf.tramitacao.model.usrgeocar;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.ufla.lemaf.tramitacao.consts.SCHEMAS;

@Entity
@Table(name = "PESSOA", catalog = "", schema = SCHEMAS.ANALISE)
public class Pessoa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_PESSOA")
	@NotNull
	private Long idPessoa;
	
	@Column(name = "NOME")
	@NotNull
	private String nomeRazaoSocial;
	
	public Pessoa() {
		
	}
	
	public Pessoa(Long id) {
		this.idPessoa = id;
	}

	public Long getIdPessoa() {
		return idPessoa;
	}

	public void setIdPessoa(Long idPessoa) {
		this.idPessoa = idPessoa;
	}

	public String getNomeRazaoSocial() {
		return nomeRazaoSocial;
	}

	public void setNomeRazaoSocial(String nomeRazaoSocial) {
		this.nomeRazaoSocial = nomeRazaoSocial;
	}

	@Override
	public int hashCode() {
		final int hashSize = 31;
		return (idPessoa == null) ? 0 : (idPessoa.intValue() % hashSize);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		if (this.idPessoa == null) {
			return false;
		} else if (!this.idPessoa.equals(other.idPessoa))
			return false;
		return true;
	}
}
