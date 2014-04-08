package br.ufla.lemaf.tramitacao.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.ufla.lemaf.tramitacao.model.TipoObjetoTramitavel;
import br.ufla.lemaf.tramitacao.repository.TipoObjetoTramitavelRepository;

@Repository
public class TipoObjetoTramitavelRepositoryImpl implements TipoObjetoTramitavelRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<TipoObjetoTramitavel> findAll() {
		
		try {
			
			return (List<TipoObjetoTramitavel>) entityManager.createQuery(
					"SELECT tipoObjetoTramitavel FROM " + TipoObjetoTramitavel.class.getSimpleName() +
					" tipoObjetoTramitavel").getResultList();
			
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}
		
		
	}

	public TipoObjetoTramitavel findById(Long id) {
		
		try {
			
			return (TipoObjetoTramitavel) entityManager.createQuery(
					"select t from " + TipoObjetoTramitavel.class.getSimpleName() +
					" t where t.id = :id")
					.setParameter("id", id)
					.getSingleResult();
			
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}
	}

}
