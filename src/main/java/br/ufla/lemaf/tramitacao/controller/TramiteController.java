package br.ufla.lemaf.tramitacao.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.lemaf.tramitacao.exception.TramitacaoException;
import br.ufla.lemaf.tramitacao.service.TramiteService;
import br.ufla.lemaf.tramitacao.vo.InicioTramitacaoRequestTO;
import br.ufla.lemaf.tramitacao.vo.InicioTramitacaoResponseTO;
import br.ufla.lemaf.tramitacao.vo.TramiteRequestVO;
import br.ufla.lemaf.tramitacao.vo.TramiteResponseVO;

@Controller
@RequestMapping("/tramitar/**")
public class TramiteController {
	
	private final Log log =  LogFactory.getLog(getClass());
	
	@Autowired(required = true)
	private TramiteService tramiteService;
	
	@RequestMapping(value = "/tramitar/iniciar", method = RequestMethod.POST)
	public InicioTramitacaoResponseTO iniciarTramitacao(@RequestBody InicioTramitacaoRequestTO tramite) {
		
		InicioTramitacaoResponseTO response;
		
		try {
			
			log.info("Serviço de iniciar tramitação consumido!");
			response = this.tramiteService.iniciarTramitacao(tramite);
			
		} catch (TramitacaoException e) {
			
			log.error(e.getTextoLog(), e);
			response = new InicioTramitacaoResponseTO(InicioTramitacaoResponseTO.ERRO, e.getTextoMensagem());
			
		} catch (Exception e) {
			
			log.error(e.getMessage(), e);
			response = new InicioTramitacaoResponseTO(InicioTramitacaoResponseTO.ERRO, "Não foi possivel iniciar a tramitação dos objetos.");
		}
		
		log.info(response.getMensagem());
		return response;
	}
	
	@RequestMapping(value = "/tramitar", method = RequestMethod.POST)
	public TramiteResponseVO tramitar(@RequestBody TramiteRequestVO tramite) {
		
		TramiteResponseVO response;
		
		try {
			
			log.info("Serviço para tramitar consumido!");
			response = this.tramiteService.tramitar(tramite);
		
		} catch (TramitacaoException e) {
			
			log.error(e.getTextoLog(), e);
			response = new TramiteResponseVO(InicioTramitacaoResponseTO.ERRO, e.getTextoMensagem());
			
		} catch (Exception e) {
			
			log.error(e.getMessage(), e);
			response = new TramiteResponseVO(InicioTramitacaoResponseTO.ERRO, "Não foi possivel tramitar.");
		}
		
		log.info(response.getMensagem());
		return response;
	}

}
