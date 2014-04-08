package br.ufla.lemaf.tramitacao.repository;

import java.util.List;

import br.ufla.lemaf.tramitacao.model.TipoObjetoTramitavel;

public interface TipoObjetoTramitavelRepository {

	List<TipoObjetoTramitavel> findAll();
	
	TipoObjetoTramitavel findById(Long id);
}
