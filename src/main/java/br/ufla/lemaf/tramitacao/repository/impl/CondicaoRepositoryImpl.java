package br.ufla.lemaf.tramitacao.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.ufla.lemaf.tramitacao.model.Condicao;
import br.ufla.lemaf.tramitacao.repository.CondicaoRepository;

@Repository
public class CondicaoRepositoryImpl implements CondicaoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public List<Condicao> findByIdFluxo(Long idFluxo) {

		try {
			
			String jpql = "SELECT condicao FROM " + Condicao.class.getSimpleName()
					+ " condicao INNER JOIN condicao.etapa e INNER JOIN e.fluxo f"
					+ " WHERE f.id = " + idFluxo;

			return (List<Condicao>) entityManager.createQuery(jpql).getResultList();

		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}

	}
}
