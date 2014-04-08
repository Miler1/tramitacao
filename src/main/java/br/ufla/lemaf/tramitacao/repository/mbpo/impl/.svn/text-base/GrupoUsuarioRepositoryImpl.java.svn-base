package br.ufla.lemaf.tramitacao.repository.mbpo.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.ufla.lemaf.tramitacao.model.mbpo.GrupoUsuario;
import br.ufla.lemaf.tramitacao.repository.mbpo.GrupoUsuarioRepository;

@Repository
public class GrupoUsuarioRepositoryImpl implements GrupoUsuarioRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public GrupoUsuario findById(Long id) {

		try {
			
			return entityManager.find(GrupoUsuario.class, id);
			
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}
	}

	
}
