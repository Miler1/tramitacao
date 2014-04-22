package br.ufla.lemaf.tramitacao.repository;

import java.util.List;

import br.ufla.lemaf.tramitacao.model.Acao;
import br.ufla.lemaf.tramitacao.model.Fluxo;
import br.ufla.lemaf.tramitacao.model.Condicao;
import br.ufla.lemaf.tramitacao.model.Transicao;

public interface TransicaoRepository {
	
	Transicao findTransicaoInicial(Fluxo fluxo);
	
	Transicao findByCondicaoInicialAndAcao(Condicao condicaoInicial, Acao acao);
	
	List<Transicao> findByCondicaoInicial(Condicao condicaoInicial, Integer apenasAcoesTramitaveis);
	
	List<Transicao> findBy(Condicao condicaoInicial, Acao acao, Integer apenasAcoesTramitaveis);

	List<Transicao> findByIdFluxo(Long idFluxo);
}
