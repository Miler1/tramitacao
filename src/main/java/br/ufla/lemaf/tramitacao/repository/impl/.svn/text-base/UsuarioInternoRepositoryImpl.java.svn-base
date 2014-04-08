package br.ufla.lemaf.tramitacao.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.ufla.lemaf.tramitacao.model.mbpu.UsuarioInterno;
import br.ufla.lemaf.tramitacao.repository.UsuarioInternoRepository;

@Repository
public class UsuarioInternoRepositoryImpl implements UsuarioInternoRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public UsuarioInterno findById(Long id) {
		
		if (id == null)
			throw new IllegalArgumentException();
		
		try {
			
			return (UsuarioInterno) this.entityManager.createQuery(
					"SELECT usuarioInterno FROM " + UsuarioInterno.class.getSimpleName() + " usuarioInterno" +
					" WHERE usuarioInterno.id = :id")
					.setParameter("id", id)
					.getSingleResult();
			
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}
		
	}

}
