package br.ufla.lemaf.tramitacao.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import br.ufla.lemaf.tramitacao.model.Status;
import br.ufla.lemaf.tramitacao.service.StatusService;

@Controller
@RequestMapping("/status/**")
public class StatusController {
	
	@Autowired(required = true)
	private StatusService statusService;
	
	// TODO Esse servico sera retirado no futuro, ele devera ser transformado em uma view com os status e fluxos
	@RequestMapping(value = "/status/findByIdFluxo/{idFluxo}", method = RequestMethod.GET)
	public List<Status> findByIdFluxo(@PathVariable Long idFluxo) {
		return this.statusService.findByIdFluxo(idFluxo);
	}

}
