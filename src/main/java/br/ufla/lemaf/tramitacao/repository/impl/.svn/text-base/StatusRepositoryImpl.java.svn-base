package br.ufla.lemaf.tramitacao.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.ufla.lemaf.tramitacao.model.Status;
import br.ufla.lemaf.tramitacao.repository.StatusRepository;

@Repository
public class StatusRepositoryImpl implements StatusRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Status> findByIdFluxo(Long idFluxo) {

		try {
			
			String jpql = "SELECT status FROM " + Status.class.getSimpleName()
					+ " status INNER JOIN status.etapa e INNER JOIN e.fluxo f"
					+ " WHERE f.id = " + idFluxo;

			return (List<Status>) entityManager.createQuery(jpql).getResultList();

		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}

	}
}
