package br.ufla.lemaf.tramitacao.repository.mbpu.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.ufla.lemaf.tramitacao.model.mbpu.UnidadeAdministrativa;
import br.ufla.lemaf.tramitacao.repository.mbpu.UnidadeAdministrativaRepository;

@Repository
public class UnidadeAdministrativaRepositoryImpl implements UnidadeAdministrativaRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public UnidadeAdministrativa findById(Long id) {

		try {
			
			return entityManager.find(UnidadeAdministrativa.class, id);
			
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}
	}

}
