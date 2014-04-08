package br.ufla.lemaf.tramitacao.model.mbpu;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import br.ufla.lemaf.tramitacao.consts.SCHEMAS;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "TB_USUARIO_INTERNO", catalog = "", schema = SCHEMAS.MBPU)
public class UsuarioInterno implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID_PESSOA_FISICA")
	@NotNull
	private Long id;

	@OneToOne
	@JoinColumn(name = "ID_PESSOA_FISICA")
	private Pessoa pessoa;

	public UsuarioInterno() {
		
	}
	
	public UsuarioInterno(Long idPessoaFisica) {
		this.id = idPessoaFisica;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long idPessoaFisica) {
		this.id = idPessoaFisica;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
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
		UsuarioInterno other = (UsuarioInterno) obj;
		if (this.id == null) {
			return false;
		} else if (!this.id.equals(other.getId()))
			return false;
		return true;
	}

}
