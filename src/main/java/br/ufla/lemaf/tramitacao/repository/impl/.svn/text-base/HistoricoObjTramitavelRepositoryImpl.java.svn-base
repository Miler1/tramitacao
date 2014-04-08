package br.ufla.lemaf.tramitacao.repository.impl;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import br.ufla.lemaf.tramitacao.model.HistoricoObjTramitavel;
import br.ufla.lemaf.tramitacao.repository.HistoricoObjTramitavelRepository;

@Repository
public class HistoricoObjTramitavelRepositoryImpl implements HistoricoObjTramitavelRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	public void save(HistoricoObjTramitavel historicoTransicao) {
		this.entityManager.persist(historicoTransicao);
	}

}
