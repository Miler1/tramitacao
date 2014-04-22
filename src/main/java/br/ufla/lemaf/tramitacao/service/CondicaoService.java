package br.ufla.lemaf.tramitacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufla.lemaf.tramitacao.model.Condicao;
import br.ufla.lemaf.tramitacao.repository.CondicaoRepository;

@Service
public class CondicaoService {

	@Autowired(required = true)
	private CondicaoRepository repository;

	public List<Condicao> findByIdFluxo(Long idFluxo) {
		return repository.findByIdFluxo(idFluxo);
	}
	
}
