package br.ufla.lemaf.tramitacao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufla.lemaf.tramitacao.model.Status;
import br.ufla.lemaf.tramitacao.repository.StatusRepository;

@Service
public class StatusService {

	@Autowired(required = true)
	private StatusRepository repository;

	public List<Status> findByIdFluxo(Long idFluxo) {
		return repository.findByIdFluxo(idFluxo);
	}
	
}
