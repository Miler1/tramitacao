package br.ufla.lemaf.tramitacao.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.ufla.lemaf.tramitacao.consts.SimNao;
import br.ufla.lemaf.tramitacao.model.Acao;
import br.ufla.lemaf.tramitacao.model.Fluxo;
import br.ufla.lemaf.tramitacao.model.Condicao;
import br.ufla.lemaf.tramitacao.model.Transicao;
import br.ufla.lemaf.tramitacao.repository.TransicaoRepository;

@Repository
public class TransicaoRepositoryImpl implements TransicaoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Transicao findTransicaoInicial( Fluxo fluxo ) {

		if ( fluxo == null || fluxo.getCondicaoInicial() == null )
			return null;

		try {

			return ( Transicao ) this.entityManager.createQuery(
					"SELECT transicao FROM " + Transicao.class.getSimpleName() + " transicao" +
							" WHERE transicao.condicaoInicial = :condicaoInicial" +
							"   AND transicao.condicaoFinal = :condicaoFinal" )
					.setParameter( "condicaoInicial", Condicao.INITIAL_PSEUDO_STATE )
					.setParameter( "condicaoFinal", fluxo.getCondicaoInicial() )
					.getSingleResult();

		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}

	}

	public Transicao findByCondicaoInicialAndAcao( Condicao condicaoInicial, Acao acao ) {

		if ( condicaoInicial == null || acao == null )
			return null;

		try {

			return ( Transicao ) this.entityManager.createQuery(
					"SELECT transicao FROM " + Transicao.class.getSimpleName() + " transicao" +
							" WHERE transicao.condicaoInicial = :condicaoInicial" +
							"   AND transicao.acao = :acao" )
					.setParameter( "condicaoInicial", condicaoInicial )
					.setParameter( "acao", acao )
					.getSingleResult();

		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}

	}

	public List<Transicao> findByCondicaoInicial( Condicao condicaoInicial, Integer apenasAcoesTramitaveis ) {
		return this.findBy( condicaoInicial, null, apenasAcoesTramitaveis );
	}

	@SuppressWarnings( "unchecked" )
	public List<Transicao> findBy( Condicao condicaoInicial, Acao acao, Integer apenasAcoesTramitaveis ) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		List<String> conditions = new ArrayList<String>();

		String jpql = "SELECT transicao FROM " + Transicao.class.getSimpleName() + " transicao";

		if ( condicaoInicial != null && condicaoInicial.getId() != null ) {

			conditions.add( "transicao.condicaoInicial = :condicaoInicial" );
			conditions.add( "transicao.condicaoInicial.ativo = :ativoCondicaoInicial" );

			parameters.put( "condicaoInicial", condicaoInicial );
			parameters.put( "ativoCondicaoInicial", SimNao.SIM );

		}

		if ( acao != null && acao.getId() != null ) {

			conditions.add( "transicao.acao = :acao" );
			conditions.add( "transicao.acao.ativo = :ativoAcao" );

			parameters.put( "acao", acao );
			parameters.put( "ativoAcao", SimNao.SIM );

		}

		if ( apenasAcoesTramitaveis != null ) {

			conditions.add( "transicao.acao.tramitavel = :apenasAcoesTramitaveis" );
			conditions.add( "transicao.acao.ativo = :ativoAcoesTramitaveis" );

			parameters.put( "apenasAcoesTramitaveis", apenasAcoesTramitaveis );
			parameters.put( "ativoAcoesTramitaveis", SimNao.SIM );

		}

		if ( conditions.size() > 0 ) {

			jpql += " WHERE " + conditions.get( 0 );

			for ( int i = 1; i < conditions.size(); i++ ) {

				jpql += " AND " + conditions.get( i );

			}

		}

		jpql += " ORDER BY transicao.acao.descricao";

		Query query = entityManager.createQuery( jpql );

		for ( Entry<String, Object> parameter : parameters.entrySet() ) {

			query.setParameter( parameter.getKey(), parameter.getValue() );
		}

		try {

			return ( List<Transicao> ) query.getResultList();

		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}

	}

	@SuppressWarnings("unchecked")
	public List<Transicao> findByIdFluxo(Long idFluxo) {
		
		try {
			
			return (List<Transicao>) entityManager.createQuery(
					"SELECT transicao FROM " + Transicao.class.getSimpleName() + " transicao" +
					" LEFT OUTER JOIN transicao.condicaoInicial condicaoInicial" +
					" LEFT OUTER JOIN condicaoInicial.etapa etapaInicial" +
					" LEFT OUTER JOIN etapaInicial.fluxo fluxoInicial" +
					" LEFT OUTER JOIN transicao.condicaoFinal condicaoFinal" +
					" LEFT OUTER JOIN condicaoFinal.etapa etapaFinal" +
					" LEFT OUTER JOIN etapaFinal.fluxo fluxoFinal" +
					" WHERE (condicaoInicial = :condicaoInicial" +
					"        OR fluxoInicial.id = :idFluxo)" +
					"    AND fluxoFinal.id = :idFluxo" +
					" ORDER BY transicao.id")
					.setParameter("idFluxo", idFluxo)
					.setParameter("condicaoInicial", Condicao.INITIAL_PSEUDO_STATE)
					.getResultList();
			
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}
	}
}
