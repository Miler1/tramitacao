package br.ufla.lemaf.tramitacao.repository;

import java.util.List;

import br.ufla.lemaf.tramitacao.model.Acao;
import br.ufla.lemaf.tramitacao.model.Fluxo;
import br.ufla.lemaf.tramitacao.model.Condicao;
import br.ufla.lemaf.tramitacao.model.Transicao;

public interface TransicaoRepository {
	
	Transicao findTransicaoInicial(Fluxo fluxo);
	
	Transicao findByStatusInicialAndAcao(Condicao statusInicial, Acao acao);
	
	List<Transicao> findByStatusInicial(Condicao statusInicial, Integer apenasAcoesTramitaveis);
	
	List<Transicao> findBy(Condicao statusInicial, Acao acao, Integer apenasAcoesTramitaveis);

	List<Transicao> findByIdFluxo(Long idFluxo);
}
