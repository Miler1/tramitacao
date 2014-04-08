package br.ufla.lemaf.tramitacao.repository.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;

import br.ufla.lemaf.tramitacao.model.ConfigSituacao;
import br.ufla.lemaf.tramitacao.model.Transicao;
import br.ufla.lemaf.tramitacao.repository.ConfigImpedimentoRepository;

@Repository
public class ConfigImpedimentoRepositoryImpl implements ConfigImpedimentoRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<ConfigSituacao> findBy(Transicao transicao) {
		
		List<String> conditions = new ArrayList<String>();
		Map<String, Object> parameters = new HashMap<String, Object>();
		
		String jpql = "SELECT configImpedimento FROM " + ConfigSituacao.class.getSimpleName() + " configImpedimento";
		
		if (transicao != null && transicao.getId() != null) {
			conditions.add("configImpedimento.transicao = :transicao");
			parameters.put("transicao", transicao);
		}
		
		if (conditions.size() > 0) {
			
			jpql += " WHERE " + conditions.get(0);
			
			for (int i = 1; i < conditions.size(); i++)
				jpql += " AND " + conditions.get(i);
		}
		
		Query query = this.entityManager.createQuery(jpql);
		
		for (Entry<String , Object> parameter : parameters.entrySet()) {
			
			query.setParameter(parameter.getKey(), parameter.getValue());
			
		}
		
		try {
			
			return (List<ConfigSituacao>) query.getResultList();
			
		} catch ( EmptyResultDataAccessException e ) {
			return null;
		} catch ( NoResultException e ) {
			return null;
		}
	}

}
