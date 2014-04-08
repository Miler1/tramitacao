package br.ufla.lemaf.tramitacao.repository;

import java.util.List;

import br.ufla.lemaf.tramitacao.model.Acao;
import br.ufla.lemaf.tramitacao.model.ObjetoTramitavel;

public interface AcaoRepository {

	List<Acao> findAll();
	
	List<Acao> findByObjetoTramitavel(ObjetoTramitavel objetoTramitavel);
	
}
