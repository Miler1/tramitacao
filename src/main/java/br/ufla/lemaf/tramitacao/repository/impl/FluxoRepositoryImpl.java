package br.ufla.lemaf.tramitacao.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.ufla.lemaf.tramitacao.model.Fluxo;
import br.ufla.lemaf.tramitacao.repository.FluxoRepository;

@Repository
public class FluxoRepositoryImpl implements FluxoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	public Fluxo findById(Long id) {

		try {

			return (Fluxo) entityManager.createQuery(
					"SELECT fluxo FROM " + Fluxo.class.getSimpleName() + " fluxo"
					+ " WHERE fluxo.id = :id"
					).setParameter("id", id).getSingleResult();

		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}

	}
	
	@SuppressWarnings("unchecked")
	public List<Fluxo> findAll() {
		
		try {
			
			return (List<Fluxo>) entityManager.createQuery(
					"SELECT fluxo FROM " + Fluxo.class.getSimpleName() + " fluxo"
					).getResultList();
			
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}
		
	}

}
