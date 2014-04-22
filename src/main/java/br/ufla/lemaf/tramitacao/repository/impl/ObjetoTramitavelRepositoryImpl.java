package br.ufla.lemaf.tramitacao.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.ufla.lemaf.tramitacao.model.ObjetoTramitavel;
import br.ufla.lemaf.tramitacao.repository.ObjetoTramitavelRepository;

@Repository
public class ObjetoTramitavelRepositoryImpl implements ObjetoTramitavelRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public void save( ObjetoTramitavel objetoTramitavel ) {
		
		//this.entityManager.persist( objetoTramitavel );
		//this.entityManager.flush();
		this.getSession().save(objetoTramitavel);
		this.getSession().flush();
	}
	
	private Session getSession(){
		return ((Session) this.entityManager.getDelegate());
	}
	
	public void update( ObjetoTramitavel objetoTramitavel ) {
		this.getSession().saveOrUpdate(objetoTramitavel);
		this.getSession().flush();

	}
	
	public ObjetoTramitavel findById( Long id ) {

		try {

			return ( ObjetoTramitavel ) this.entityManager.createQuery(
					"SELECT objetoTramitavel FROM "
							+ ObjetoTramitavel.class.getSimpleName()
							+ " objetoTramitavel "
							+ " WHERE objetoTramitavel.id = :id" ).setParameter(
					"id", id ).getSingleResult();

		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}

	}

	@SuppressWarnings( "unchecked" )
	public List<ObjetoTramitavel> findAll() {

		try {

			return ( List<ObjetoTramitavel> ) this.entityManager.createQuery(
					"SELECT objetoTramitavel FROM "
							+ ObjetoTramitavel.class.getSimpleName()
							+ " objetoTramitavel " ).getResultList();

		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}

	}

	@SuppressWarnings( "unchecked" )
	public List<ObjetoTramitavel> findByIdFluxoAndIdUsuario( Long idFluxo, Long idUsuario ) {
		
		try {
			
			List<ObjetoTramitavel> objetosTramitaveis = this.entityManager
					.createQuery(
							"SELECT objetoTramitavel FROM "
									+ ObjetoTramitavel.class.getSimpleName()
									+ " objetoTramitavel "
									+ " LEFT JOIN objetoTramitavel.fluxo f "
									+ "WHERE f.id = " + idFluxo
									+ " AND objetoTramitavel.idUsuario = "
									+ idUsuario ).getResultList();
			return objetosTramitaveis;

		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}
	}

	@SuppressWarnings( "unchecked" )
	public List<ObjetoTramitavel> findByIdCondicao( Long idCondicao ) {
		
		try {

			List<ObjetoTramitavel> objetosTramitaveis = this.entityManager
					.createQuery(
							"SELECT objetoTramitavel FROM "
									+ ObjetoTramitavel.class.getSimpleName()
									+ "  objetoTramitavel "
									+ "WHERE objetoTramitavel.condicao.id = "
									+ idCondicao ).getResultList();
			return objetosTramitaveis;

		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}

	}
}
