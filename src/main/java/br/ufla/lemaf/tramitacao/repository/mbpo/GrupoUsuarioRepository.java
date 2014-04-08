package br.ufla.lemaf.tramitacao.repository.mbpo;

import br.ufla.lemaf.tramitacao.model.mbpo.GrupoUsuario;

public interface GrupoUsuarioRepository {

	GrupoUsuario findById( Long id );
}
