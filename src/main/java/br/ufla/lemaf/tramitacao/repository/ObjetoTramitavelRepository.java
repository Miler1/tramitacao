package br.ufla.lemaf.tramitacao.repository;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import br.ufla.lemaf.tramitacao.model.ObjetoTramitavel;

public interface ObjetoTramitavelRepository {
	
	@Transactional
	void save(ObjetoTramitavel objetoTramitavel);
	
	@Transactional
	void update(ObjetoTramitavel objetoTramitavel);
	
	ObjetoTramitavel findById(Long id);
	
	List<ObjetoTramitavel> findAll();

	List<ObjetoTramitavel> findByIdFluxoAndIdUsuario(Long idFluxo, Long idUsuario);
	
	List<ObjetoTramitavel> findByIdStatus(Long idStatus);
	
}
