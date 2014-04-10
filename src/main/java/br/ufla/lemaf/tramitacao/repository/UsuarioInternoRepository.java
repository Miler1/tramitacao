package br.ufla.lemaf.tramitacao.repository;

import br.ufla.lemaf.tramitacao.model.usrgeocar.Usuario;

public interface UsuarioInternoRepository {
	
	Usuario findById(Long id);

}
