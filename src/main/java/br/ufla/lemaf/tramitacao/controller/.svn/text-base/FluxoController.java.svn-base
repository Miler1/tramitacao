package br.ufla.lemaf.tramitacao.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufla.lemaf.tramitacao.graphviz.GraphViz;
import br.ufla.lemaf.tramitacao.service.FluxoService;

@Controller
@RequestMapping("/fluxo/**")
public class FluxoController {
	
	@Autowired(required = true)
	private FluxoService fluxoService;
	
	@RequestMapping(value = "/fluxo/{id}/graph", method = RequestMethod.GET)
	public void generateGraphByIdFluxo(HttpServletResponse response, @PathVariable Long id,
										@RequestParam(defaultValue="false") String printDotFile) throws IOException {
		
		boolean printDotFileBoolean = printDotFile != null && "true".equals(printDotFile.toLowerCase());
		
		byte[] bytes = fluxoService.generateGraph(id, printDotFileBoolean);
		
		response.setContentType(GraphViz.OUTPUT_TYPE);
		response.setHeader("Content-Disposition","attachment;filename=" + id + "." + GraphViz.OUTPUT_TYPE);
		
		ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
		OutputStream outputStream = response.getOutputStream();

		int c;
		while ((c = inputStream.read()) != -1)
			outputStream.write(c);
	}

}
