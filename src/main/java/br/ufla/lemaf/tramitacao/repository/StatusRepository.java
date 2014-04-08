package br.ufla.lemaf.tramitacao.repository;

import java.util.List;

import br.ufla.lemaf.tramitacao.model.Status;

public interface StatusRepository {

	List<Status> findByIdFluxo(Long idFluxo);
	
}
