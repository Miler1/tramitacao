package br.ufla.lemaf.tramitacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.lemaf.tramitacao.model.Condicao;
import br.ufla.lemaf.tramitacao.service.CondicaoService;

@Controller
@RequestMapping("/condicao/**")
public class CondicaoController {
	
	@Autowired(required = true)
	private CondicaoService condicaoService;
	
	// TODO Esse servico sera retirado no futuro, ele devera ser transformado em uma view com os status e fluxos
	@RequestMapping(value = "/condicao/findByIdFluxo/{idFluxo}", method = RequestMethod.GET)
	public List<Condicao> findByIdFluxo(@PathVariable Long idFluxo) {
		return this.condicaoService.findByIdFluxo(idFluxo);
	}

}
