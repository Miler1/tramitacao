package br.ufla.lemaf.tramitacao.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufla.lemaf.tramitacao.consts.SimNao;
import br.ufla.lemaf.tramitacao.consts.TipoImpedimento;
import br.ufla.lemaf.tramitacao.exception.TramiteRequestException;
import br.ufla.lemaf.tramitacao.model.Acao;
import br.ufla.lemaf.tramitacao.model.ConfigSituacao;
import br.ufla.lemaf.tramitacao.model.Fluxo;
import br.ufla.lemaf.tramitacao.model.HistoricoObjTramitavel;
import br.ufla.lemaf.tramitacao.model.Impedimento;
import br.ufla.lemaf.tramitacao.model.ObjetoTramitavel;
import br.ufla.lemaf.tramitacao.model.Condicao;
import br.ufla.lemaf.tramitacao.model.TipoObjetoTramitavel;
import br.ufla.lemaf.tramitacao.model.Transicao;
import br.ufla.lemaf.tramitacao.model.usrgeocar.Usuario;
import br.ufla.lemaf.tramitacao.repository.ConfigImpedimentoRepository;
import br.ufla.lemaf.tramitacao.repository.FluxoRepository;
import br.ufla.lemaf.tramitacao.repository.TransicaoRepository;
import br.ufla.lemaf.tramitacao.repository.UsuarioInternoRepository;
import br.ufla.lemaf.tramitacao.util.ValidationUtil;
import br.ufla.lemaf.tramitacao.vo.InicioTramitacaoRequestTO;
import br.ufla.lemaf.tramitacao.vo.InicioTramitacaoRequestVO;
import br.ufla.lemaf.tramitacao.vo.InicioTramitacaoResponseTO;
import br.ufla.lemaf.tramitacao.vo.InicioTramitacaoResponseVO;
import br.ufla.lemaf.tramitacao.vo.TramiteRequestVO;
import br.ufla.lemaf.tramitacao.vo.TramiteResponseVO;
import br.ufla.lemaf.tramitacao.vo.TramiteVO;

@Service
public class TramiteService {

	@Autowired( required = true )
	private ObjetoTramitavelService objetoTramitavelService;

	@Autowired
	private FluxoRepository fluxoRepository;

	@Autowired
	private TransicaoRepository transicaoRepository;

	@Autowired
	private HistoricoObjTramitavelService historicoObjTramitavelService;

	@Autowired
	private UsuarioInternoRepository usuarioInternoRepository;

	@Autowired
	private ConfigImpedimentoRepository configImpedimentoRepository;

//	@Autowired
//	private GrupoUsuarioRepository grupoUsuarioRepository;
//	
//	@Autowired
//	private UnidadeAdministrativaRepository unidadeAdministrativaRepository;
//	
//	@Autowired
//	private OrgaoRepository orgaoRepository;
	
	@Transactional
	public InicioTramitacaoResponseTO iniciarTramitacao( InicioTramitacaoRequestTO request ) {
		
		ValidationUtil.notNull( request, "Não foi informado nenhum objeto para iniciar a tramitação!" );
		ValidationUtil.notNullNotEmpty( request.getNovosObjetosTramitaveis(), "Não foi informado nenhum objeto para iniciar a tramitação!" );
		
		InicioTramitacaoResponseTO response = new InicioTramitacaoResponseTO( InicioTramitacaoResponseTO.SUCESSO, "Objeto(s) tramitável(s) criado(s) com sucesso." );
		
		for (InicioTramitacaoRequestVO tramite : request.getNovosObjetosTramitaveis()) {
			
			InicioTramitacaoResponseVO novoTramite = this.iniciarTramitacao(tramite);
			
			response.addTramite(novoTramite);
		}

		return response;
	}
		
	@Transactional
	private InicioTramitacaoResponseVO iniciarTramitacao( InicioTramitacaoRequestVO tramite ) {

		validarIniciarTramitacao( tramite );
		
//		ResponsavelObjetoTramitavel responsavel = validarERetornarResponsavel( 
//				tramite.getIdGrupoUsuarioDestino(), tramite.getIdUnidadeAdmDestino(), tramite.getIdOrgaoDestino() );
		
		Fluxo fluxo = fluxoRepository.findById( tramite.getIdFluxo() );

		ValidationUtil.idNotNull( fluxo, String.format("O fluxo de tramitação informado não foi encontrado![idFluxo=%d]" , tramite.getIdFluxo() ) );

		ObjetoTramitavel objetoTramitavel = new ObjetoTramitavel();

		objetoTramitavel.setFluxo( fluxo );
		objetoTramitavel.setTipo( new TipoObjetoTramitavel( tramite.getIdTipoObjetoTramitavel() ) );
		objetoTramitavel.setDataCriacao( new Date() );

		if ( tramite.getIdObjetoTramitavelPai() != null )
			objetoTramitavel.setPai( new ObjetoTramitavel( tramite.getIdObjetoTramitavelPai() ) );

		Transicao transicaoInicial = this.buscarTransicaoInicial( fluxo, new Acao( tramite.getIdAcao() ) );
		
		if ( transicaoInicial == null ) {

			objetoTramitavel.setStatus( fluxo.getStatusInicial() );
			objetoTramitavel.setEtapa( fluxo.getStatusInicial().getEtapa() );
//			objetoTramitavel.setResponsavel ( responsavel );
			
			if (tramite.possuiUsuarioDestino())
				objetoTramitavel.setUsuario( new Usuario( tramite.getIdUsuarioDestino() ) );
			
			objetoTramitavelService.save( objetoTramitavel );
			
		} else {

			objetoTramitavel.setStatus( Condicao.INITIAL_PSEUDO_STATE );
			
			objetoTramitavelService.save( objetoTramitavel );
			
			tramitar( objetoTramitavel, transicaoInicial.getAcao(), tramite.getIdUsuarioExecutor(), 
				tramite.getIdUsuarioDestino(), 
				//responsavel, 
				null );
		}

		InicioTramitacaoResponseVO response = new InicioTramitacaoResponseVO();
		response.setIdObjetoTramitavel( objetoTramitavel.getId() );
		response.setCodigo( tramite.getCodigo() );

		return response;
	}
	
	@Transactional
	private Transicao buscarTransicaoInicial( Fluxo fluxo, Acao acao ) {
		
		Transicao transicaoInicial = null;
		
		if ( acao == null || acao.getId() == null ) {
			
			transicaoInicial = this.transicaoRepository.findTransicaoInicial( fluxo );

		} else {
			
			transicaoInicial = this.transicaoRepository.findByStatusInicialAndAcao( Condicao.INITIAL_PSEUDO_STATE, acao );
			
			ValidationUtil.idNotNull(transicaoInicial, String.format("A ação informada para iniciar a tramitação não é válida![idAcao=%d]" , acao.getId() ));
		}
		
		return transicaoInicial;
	}

	@Transactional
	public TramiteResponseVO tramitar( TramiteRequestVO request ) {

		if ( request == null || !request.possuiTramites() )
			throw new TramiteRequestException( "A requisição não possui tramites." );

		for ( TramiteVO tramite : request.getTramites() )
			tramitar( tramite );

		return new TramiteResponseVO( TramiteResponseVO.SUCESSO, "Objetos tramitados com sucesso." );
	}

	@Transactional
	private void tramitar( TramiteVO tramite ) {

		validarTramitar( tramite );
		
//		ResponsavelObjetoTramitavel responsavel = validarERetornarResponsavel(
//				tramite.getIdGrupoUsuarioDestino(), tramite.getIdUnidadeAdmDestino(), tramite.getIdOrgaoDestino() );

		ObjetoTramitavel objetoTramitavel = objetoTramitavelService.findById( tramite.getIdObjetoTramitavel() );

		ValidationUtil.idNotNull( objetoTramitavel, "O objeto tramitável informado não foi encontrado!" );

		if ( tramite.isRetornoParaUltimoResponsavel() ) {

			Usuario ultimoResponsavel = objetoTramitavel.getResponsavelAnterior();

			tramite.setIdUsuarioDestino( (ultimoResponsavel == null) ? null : ultimoResponsavel.getId() );

//			ResponsavelObjetoTramitavel responsavelAnterior = objetoTramitavel.getResponsavelObjetoTramitavelAnterior();
//			
//			responsavel = (responsavelAnterior == null) ? null
//					: new ResponsavelObjetoTramitavel( objetoTramitavel.getResponsavelObjetoTramitavelAnterior() );
		}

		tramitar( objetoTramitavel, new Acao( tramite.getIdAcao() ), tramite.getIdUsuarioExecutor(), tramite.getIdUsuarioDestino(),
				//responsavel, 
				tramite.getObservacao() );
	}

	@Transactional
	private void tramitar( ObjetoTramitavel objetoTramitavel, Acao acao, Long idUsuarioExecutor, Long idUsuarioDestino,
			//ResponsavelObjetoTramitavel responsavelDestino, 
			String observacao ) {

		Transicao transicao = transicaoRepository.findByStatusInicialAndAcao( objetoTramitavel.getStatus(), acao );

		ValidationUtil.idNotNull( transicao, "A ação informada não está disponível para tramitação para esse objeto tramitável." );

		verificarImpedimentosTramite( objetoTramitavel, transicao );
		
		adicionarRemoverSituacoesAoTramitar( objetoTramitavel, transicao );

		Usuario usuarioExecutor = ( idUsuarioExecutor != null ) ? usuarioInternoRepository.findById( idUsuarioExecutor ) : null;
		
		if (transicao.isRetornarFluxoAnterior()) {

			objetoTramitavel.setObjetoParaRetornarParaFluxoAnterior();

		} else {
			
			objetoTramitavel.setStatus( transicao.getStatusFinal() );
			
			setUsuarioDestino( objetoTramitavel, idUsuarioDestino );
		}

		historicoObjTramitavelService.save(new HistoricoObjTramitavel(objetoTramitavel,
			transicao.getAcao(),
			transicao.getStatusInicial(),
			objetoTramitavel.getStatus(),
			usuarioExecutor,
			objetoTramitavel.getUsuario(),
			//objetoTramitavel.getResponsavel(),
			new Date(),
			observacao));
		
		objetoTramitavelService.update( objetoTramitavel );
		
	}
	
	@Transactional
	private void setUsuarioDestino( ObjetoTramitavel objetoTramitavel, Long idUsuarioDestino ) {
		
		if (idUsuarioDestino == null)
			return;
		
		Usuario usuarioDestino = ( idUsuarioDestino != null ) ? usuarioInternoRepository.findById( idUsuarioDestino ) : null;
		objetoTramitavel.setUsuario( usuarioDestino );
		
	}

	@Transactional
	private void verificarImpedimentosTramite( ObjetoTramitavel objetoTramitavel, Transicao transicao ) {

		Impedimento impedimento = this.objetoTramitavelService.transicaoEstaImpedidaPorSituacao( objetoTramitavel, transicao );

		if ( impedimento != null ) {

			String errorMsg = ( TipoImpedimento.OBRIGATORIO.equals( impedimento.getTipo() ) )
					? "A ação '" + transicao.getAcao().getDescricao() + "' está impedida de ser executada, pois o objeto tramitável NÃO possui a seguinte situação: " + impedimento.getSituacao().getDescricao()
					: "A ação '" + transicao.getAcao().getDescricao() + "' está impedida de ser executada, pois o objeto tramitável se encontra na seguinte situação: " + impedimento.getSituacao().getDescricao();

			throw new TramiteRequestException( errorMsg );
		}
	}

	@Transactional
	private void adicionarRemoverSituacoesAoTramitar( ObjetoTramitavel objetoTramitavel, Transicao transicao ) {

		List<ConfigSituacao> configuracoesSituacao = this.configImpedimentoRepository.findBy( transicao );

		if ( configuracoesSituacao == null || configuracoesSituacao.isEmpty() )
			return;

		for ( ConfigSituacao configSituacao : configuracoesSituacao ) {

			ObjetoTramitavel objetoASerConfigurado = objetoTramitavel;

			// caso for configurar as situacoes do objeto pai
			if ( SimNao.SIM.equals( configSituacao.getPai() ) ) {
			
				if ( objetoTramitavel.getPai() == null )
					continue;

				objetoTramitavel.setPai( this.objetoTramitavelService.findById( objetoTramitavel.getPai().getId() ) );
				
				objetoASerConfigurado = objetoTramitavel.getPai();
			}

			if ( objetoASerConfigurado == null )
				continue;

			boolean adicionarSituacaoAoObjeto = SimNao.SIM.equals( configSituacao.getAdicionar() );

			if ( adicionarSituacaoAoObjeto )
				objetoASerConfigurado.adicionarSituacao( configSituacao.getSituacao() );
			else
				objetoASerConfigurado.removerSituacao( configSituacao.getSituacao() );

		}
	}

	@Transactional
	private void validarIniciarTramitacao( InicioTramitacaoRequestVO tramite ) {

		ValidationUtil.notNull( tramite, "Nenhum campo foi informado para iniciar a tramitação." );
		ValidationUtil.notNull( tramite.getIdFluxo(), "O identificador do fluxo de tramitação não foi informado corretamente." );
		ValidationUtil.notNull( tramite.getIdTipoObjetoTramitavel(), "O identificador do tipo do objeto tramitável não foi informado corretamente." );
	}
	
//	@Transactional
//	private ResponsavelObjetoTramitavel validarERetornarResponsavel(Long idGrupoUsuario, Long idUnidadeAdm, Long idOrgao) {
//		
//		if ( idGrupoUsuario == null && idUnidadeAdm == null && idOrgao == null )
//			return null;
//		
//		ResponsavelObjetoTramitavel responsavel = new ResponsavelObjetoTramitavel();
//		
//		if ( idGrupoUsuario != null ) {
//			
//			GrupoUsuario grupoUsuario = grupoUsuarioRepository.findById( idGrupoUsuario );
//			ValidationUtil.idNotNull( grupoUsuario, "Não foi possível encontrar o grupo de usuário responsável informado." );
//			
//			responsavel.setGrupoUsuario(grupoUsuario);
//		}
//		
//		if ( idUnidadeAdm != null ) {
//			
//			UnidadeAdministrativa unidadeAdministrativa = unidadeAdministrativaRepository.findById( idUnidadeAdm );
//			ValidationUtil.idNotNull( unidadeAdministrativa, "Não foi possível encontrar a unidade administrativa responsável informada." );
//			
//			responsavel.setUnidadeAdministrativa(unidadeAdministrativa);
//		}
//		
//		if ( idOrgao != null ) {
//			
//			Orgao orgao = orgaoRepository.findById( idOrgao );
//			ValidationUtil.idNotNull( orgao, "Não foi possível encontrar o órgão responsável informado." );
//			
//			responsavel.setOrgao(orgao);
//		}
//		
//		return responsavel;
//	}

	@Transactional
	private void validarTramitar( TramiteVO tramite ) {

		ValidationUtil.notNull( tramite, "Nenhum campo foi informado para tramitação." );
		ValidationUtil.notNull( tramite.getIdObjetoTramitavel(), "O identificador do objeto a ser tramitado não foi informado corretamente." );
		ValidationUtil.notNull( tramite.getIdAcao(), "O identificador da ação a ser executada não foi informado corretamente." );
//		ValidationUtil.notNull( tramite.getIdUsuarioExecutor(), "O identificador do usuário executor não foi informado corretamente." );

		if(tramite.getIdUsuarioExecutor() != null) {
			Usuario usuarioExecutor = usuarioInternoRepository.findById( tramite.getIdUsuarioExecutor() );
			ValidationUtil.idNotNull( usuarioExecutor, "Não foi possível encontrar o usuário responsável executor informado." );
		}
		
		if ( tramite.possuiUsuarioDestino() && ( tramite.possuiGrupoUsuarioDestino()
				|| tramite.possuiUnidadeAdmDestino() || tramite.possuiOrgaoDestino() ) )
			throw new TramiteRequestException( "Caso o usuário interno responsável destino for informado, " +
					"não é possível informar grupo de usuário, unidade administrativa ou órgão responsáveis destino." );

		if ( tramite.possuiUnidadeAdmDestino() && tramite.possuiOrgaoDestino() )
			throw new TramiteRequestException( "Não é possível informar unidade administrativa e órgão juntos como responsáveis destino pelo objeto." );
		
		if ( tramite.possuiUsuarioDestino() ) {
			
			Usuario usuarioResponsavel = usuarioInternoRepository.findById( tramite.getIdUsuarioDestino() );
			ValidationUtil.idNotNull( usuarioResponsavel, "Não foi possível encontrar o usuário responsável destino informado." );
		}
	}

}
