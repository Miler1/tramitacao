package br.ufla.lemaf.tramitacao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufla.lemaf.tramitacao.model.HistoricoObjTramitavel;
import br.ufla.lemaf.tramitacao.repository.HistoricoObjTramitavelRepository;

@Service
public class HistoricoObjTramitavelService {
	
	@Autowired(required = true)
	private HistoricoObjTramitavelRepository historicoObjTramitavelRepository;
	
	public void save(HistoricoObjTramitavel historicoObjetoTramitavel) {
		this.historicoObjTramitavelRepository.save(historicoObjetoTramitavel);
	}

}
