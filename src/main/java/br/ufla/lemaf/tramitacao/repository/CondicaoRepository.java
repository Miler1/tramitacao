package br.ufla.lemaf.tramitacao.repository;

import java.util.List;

import br.ufla.lemaf.tramitacao.model.Condicao;

public interface CondicaoRepository {

	List<Condicao> findByIdFluxo(Long idFluxo);
	
}
