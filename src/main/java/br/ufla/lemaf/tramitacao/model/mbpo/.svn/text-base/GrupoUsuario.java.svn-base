package br.ufla.lemaf.tramitacao.model.mbpo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import br.ufla.lemaf.tramitacao.consts.SCHEMAS;

@Entity
@Table(name = "TB_GRUPO_USUARIO", schema = SCHEMAS.MBPO)
public class GrupoUsuario implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public GrupoUsuario() {
	}
	
	public GrupoUsuario(Long id) {
		this.id = id;
	}

	@Id
	@Column(name = "SEQ_GRUPO_USUARIO")
	private Long id;
	
	@Column(name = "DES_GRUPO_USUARIO")
	private String descricao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		return (id == null) ? 0 : (id.intValue() % prime);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		
		if (obj == null || this.id == null || this.getClass() != obj.getClass())
			return false;
		
		GrupoUsuario other = (GrupoUsuario) obj;
		
		return this.id.equals(other.id);
	}
}
