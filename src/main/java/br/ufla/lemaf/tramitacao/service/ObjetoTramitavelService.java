package br.ufla.lemaf.tramitacao.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufla.lemaf.tramitacao.consts.SimNao;
import br.ufla.lemaf.tramitacao.consts.TipoImpedimento;
import br.ufla.lemaf.tramitacao.exception.TramiteRequestException;
import br.ufla.lemaf.tramitacao.model.Acao;
import br.ufla.lemaf.tramitacao.model.Impedimento;
import br.ufla.lemaf.tramitacao.model.ObjetoTramitavel;
import br.ufla.lemaf.tramitacao.model.Transicao;
import br.ufla.lemaf.tramitacao.repository.ObjetoTramitavelRepository;
import br.ufla.lemaf.tramitacao.repository.TransicaoRepository;

@Service
public class ObjetoTramitavelService {

	@Autowired( required = true )
	private ObjetoTramitavelRepository objetoTramitavelRepository;

	@Autowired( required = true )
	private TransicaoRepository transicaoRepository;

	private final Log log = LogFactory.getLog(getClass());

	@Transactional
	public void saveOrUpdate( ObjetoTramitavel objetoTramitavel ) {

		if ( objetoTramitavel.getId() == null ) {
			this.objetoTramitavelRepository.save( objetoTramitavel );
		} else {
			this.objetoTramitavelRepository.update( objetoTramitavel );
		}

	}

	@Transactional
	public void save( ObjetoTramitavel objetoTramitavel ) {
		
		try {

			this.objetoTramitavelRepository.save( objetoTramitavel );

		} catch ( Exception e ) {

			log.error("Erro ao criar o objeto tramitável.", e);
			throw new TramiteRequestException( "Erro ao criar o objeto tramitável." );
		}
	}

	@Transactional
	public void update( ObjetoTramitavel objetoTramitavel ) {
		this.objetoTramitavelRepository.update( objetoTramitavel );
	}

	public ObjetoTramitavel findById( Long id ) {
		return this.objetoTramitavelRepository.findById( id );
	}

	public List<ObjetoTramitavel> findAll() {
		return this.objetoTramitavelRepository.findAll();
	}

	public List<ObjetoTramitavel> findByIdFluxoAndIdUsuario( Long idFluxo, Long idUsuario ) {
		return objetoTramitavelRepository.findByIdFluxoAndIdUsuario( idFluxo, idUsuario );
	}

	public List<Acao> findAcoesTramitaveisDisponiveis( Long idObjetoTramitavel ) {
		return this.findAcoesDisponiveis( idObjetoTramitavel, SimNao.SIM );
	}

	public List<Acao> findAcoesDisponiveis( Long idObjetoTramitavel ) {
		return this.findAcoesDisponiveis( idObjetoTramitavel, null );
	}

	public List<ObjetoTramitavel> findAcoesDisponiveis( List<Long> idsObjetoTramitel ) {

		List<ObjetoTramitavel> objetosTramitaveis = new ArrayList<ObjetoTramitavel>();

		for ( Long idObjetoTramitavel : idsObjetoTramitel ) {

			ObjetoTramitavel objetoTramitavel = this.findById( idObjetoTramitavel );

			if ( objetoTramitavel == null )
				continue;

			objetoTramitavel.setAcoesDisponiveis( this.findAcoesDisponiveis( idObjetoTramitavel ) );

			objetosTramitaveis.add( objetoTramitavel );

		}

		return objetosTramitaveis;
	}

	public List<Acao> findAcoesDisponiveis( Long idObjetoTramitavel, Integer apenasAcoesTramitaveis ) {

		ObjetoTramitavel objetoTramitavel = this.objetoTramitavelRepository.findById( idObjetoTramitavel );

		if ( objetoTramitavel != null && objetoTramitavel.getStatus() != null ) {

			List<Acao> acoes = new ArrayList<Acao>();

			List<Transicao> transicoes =
					this.transicaoRepository.findByStatusInicial( objetoTramitavel.getStatus(), apenasAcoesTramitaveis );

			for ( Transicao transicao : transicoes ) {

				if ( transicaoEstaImpedidaPorSituacao( objetoTramitavel, transicao ) == null )
					acoes.add( transicao.getAcao() );
			}

			return acoes;
		}

		return null;
	}

	public Impedimento transicaoEstaImpedidaPorSituacao( ObjetoTramitavel objetoTramitavel, Transicao transicao ) {

		for ( Impedimento impedimento : transicao.getImpedimentos() ) {

			if ( TipoImpedimento.OBRIGATORIO.equals( impedimento.getTipo() ) ) {

				if ( objetoTramitavel.getSituacoes() == null ||
						!objetoTramitavel.getSituacoes().contains( impedimento.getSituacao() ) )

				return impedimento;

			} else {

				if ( objetoTramitavel.getSituacoes() != null &&
						objetoTramitavel.getSituacoes().contains( impedimento.getSituacao() ) )

				return impedimento;
			}
		}

		return null;
	}

	public List<ObjetoTramitavel> findByIdStatus( Long idStatus ) {
		return this.objetoTramitavelRepository.findByIdStatus( idStatus );
	}

}
