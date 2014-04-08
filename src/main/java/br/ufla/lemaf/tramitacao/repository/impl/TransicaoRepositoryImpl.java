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
import br.ufla.lemaf.tramitacao.model.Status;
import br.ufla.lemaf.tramitacao.model.Transicao;
import br.ufla.lemaf.tramitacao.repository.TransicaoRepository;

@Repository
public class TransicaoRepositoryImpl implements TransicaoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Transicao findTransicaoInicial( Fluxo fluxo ) {

		if ( fluxo == null || fluxo.getStatusInicial() == null )
			return null;

		try {

			return ( Transicao ) this.entityManager.createQuery(
					"SELECT transicao FROM " + Transicao.class.getSimpleName() + " transicao" +
							" WHERE transicao.statusInicial = :statusInicial" +
							"   AND transicao.statusFinal = :statusFinal" )
					.setParameter( "statusInicial", Status.INITIAL_PSEUDO_STATE )
					.setParameter( "statusFinal", fluxo.getStatusInicial() )
					.getSingleResult();

		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}

	}

	public Transicao findByStatusInicialAndAcao( Status statusInicial, Acao acao ) {

		if ( statusInicial == null || acao == null )
			return null;

		try {

			return ( Transicao ) this.entityManager.createQuery(
					"SELECT transicao FROM " + Transicao.class.getSimpleName() + " transicao" +
							" WHERE transicao.statusInicial = :statusInicial" +
							"   AND transicao.acao = :acao" )
					.setParameter( "statusInicial", statusInicial )
					.setParameter( "acao", acao )
					.getSingleResult();

		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}

	}

	public List<Transicao> findByStatusInicial( Status statusInicial, Integer apenasAcoesTramitaveis ) {
		return this.findBy( statusInicial, null, apenasAcoesTramitaveis );
	}

	@SuppressWarnings( "unchecked" )
	public List<Transicao> findBy( Status statusInicial, Acao acao, Integer apenasAcoesTramitaveis ) {

		Map<String, Object> parameters = new HashMap<String, Object>();
		List<String> conditions = new ArrayList<String>();

		String jpql = "SELECT transicao FROM " + Transicao.class.getSimpleName() + " transicao";

		if ( statusInicial != null && statusInicial.getId() != null ) {

			conditions.add( "transicao.statusInicial = :statusInicial" );
			conditions.add( "transicao.statusInicial.ativo = :ativoStatusInicial" );

			parameters.put( "statusInicial", statusInicial );
			parameters.put( "ativoStatusInicial", SimNao.SIM );

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
					" LEFT OUTER JOIN transicao.statusInicial statusInicial" +
					" LEFT OUTER JOIN statusInicial.etapa etapaInicial" +
					" LEFT OUTER JOIN etapaInicial.fluxo fluxoInicial" +
					" LEFT OUTER JOIN transicao.statusFinal statusFinal" +
					" LEFT OUTER JOIN statusFinal.etapa etapaFinal" +
					" LEFT OUTER JOIN etapaFinal.fluxo fluxoFinal" +
					" WHERE (statusInicial = :statusInicial" +
					"        OR fluxoInicial.id = :idFluxo)" +
					"    AND fluxoFinal.id = :idFluxo" +
					" ORDER BY transicao.id")
					.setParameter("idFluxo", idFluxo)
					.setParameter("statusInicial", Status.INITIAL_PSEUDO_STATE)
					.getResultList();
			
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}
	}
}
