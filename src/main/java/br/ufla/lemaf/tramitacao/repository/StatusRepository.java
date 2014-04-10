package br.ufla.lemaf.tramitacao.repository;

import java.util.List;

import br.ufla.lemaf.tramitacao.model.Condicao;

public interface StatusRepository {

	List<Condicao> findByIdFluxo(Long idFluxo);
	
}
