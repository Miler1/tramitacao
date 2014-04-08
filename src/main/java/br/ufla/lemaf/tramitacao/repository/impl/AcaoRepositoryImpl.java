package br.ufla.lemaf.tramitacao.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.ufla.lemaf.tramitacao.consts.SimNao;
import br.ufla.lemaf.tramitacao.model.Acao;
import br.ufla.lemaf.tramitacao.model.ObjetoTramitavel;
import br.ufla.lemaf.tramitacao.model.Transicao;
import br.ufla.lemaf.tramitacao.repository.AcaoRepository;

@Repository
public class AcaoRepositoryImpl implements AcaoRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<Acao> findAll() {
		
		try {
			
			return (List<Acao>) this.entityManager.createQuery("SELECT acao FROM " +
					Acao.class.getSimpleName() + " acao").getResultList();
			
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Acao> findByObjetoTramitavel(ObjetoTramitavel objetoTramitavel) {
		
		try {
			
			return (List<Acao>) this.entityManager.createQuery(
					"SELECT acao FROM " + Transicao.class.getSimpleName() + " transicao" +
						" INNER JOIN transicao.acao AS acao" +
						" WHERE transicao.statusInicial = :statusInicial" +
						"   AND transicao.statusInicial.ativo = :ativoStatusInicial" +
						"   AND transicao.acao.ativo = :ativoAcao"
						).setParameter("statusInicial", objetoTramitavel.getStatus())
						 .setParameter("ativoStatusInicial", SimNao.SIM )
						 .setParameter("ativoAcao", SimNao.SIM )
						 .getResultList();
			
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}
		
	}

}
