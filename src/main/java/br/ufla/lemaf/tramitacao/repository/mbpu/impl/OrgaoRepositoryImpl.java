package br.ufla.lemaf.tramitacao.repository.mbpu.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.ufla.lemaf.tramitacao.model.mbpu.Orgao;
import br.ufla.lemaf.tramitacao.repository.mbpu.OrgaoRepository;

@Repository
public class OrgaoRepositoryImpl implements OrgaoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Orgao findById(Long id) {

		try {
			
			return entityManager.find(Orgao.class, id);
			
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}
	}

}
