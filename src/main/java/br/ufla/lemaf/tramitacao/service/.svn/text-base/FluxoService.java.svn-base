package br.ufla.lemaf.tramitacao.service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufla.lemaf.tramitacao.graphviz.GraphViz;
import br.ufla.lemaf.tramitacao.model.Fluxo;
import br.ufla.lemaf.tramitacao.model.Status;
import br.ufla.lemaf.tramitacao.model.Transicao;
import br.ufla.lemaf.tramitacao.repository.FluxoRepository;
import br.ufla.lemaf.tramitacao.repository.TransicaoRepository;

@Service
public class FluxoService {
	
	private static final int MAX_LINE_SIZE_LABEL_STATUS_GRAPH = 18;
	
	private final Log log =  LogFactory.getLog(getClass());

	@Autowired
	private FluxoRepository fluxoRepository;
	
	@Autowired
	private TransicaoRepository transicaoRepository;
	
	public Fluxo findById(Long id) {
		return fluxoRepository.findById(id);
	}
	
	public byte[] generateGraph(Long idFluxo, boolean printDotFile) {
		
		List<Transicao> transicoes = transicaoRepository.findByIdFluxo(idFluxo);
		
		if (transicoes == null || transicoes.isEmpty())
			return null;
		
		Collection<Status> statusSet = getStatusFromTransicoes(transicoes);
		
		GraphViz graph = generateGraph(transicoes, statusSet, printDotFile);

		return graph.getGraph( graph.getDotSource(), GraphViz.OUTPUT_TYPE );
	}
	
	private Collection<Status> getStatusFromTransicoes(List<Transicao> transicoes) {
		
		Set<Status> statusSet = new HashSet<Status>();
		
		for (Transicao transicao : transicoes) {
			statusSet.add(transicao.getStatusInicial());
			statusSet.add(transicao.getStatusFinal());
		}
		
		return statusSet;
	}
	
	private GraphViz generateGraph(Collection<Transicao> transicoes, Collection<Status> statusList, boolean printDotFile) {
		
		GraphViz graph = new GraphViz();
		graph.addln(graph.start_graph());
		
		// Estilos do grafico
		graph.addln("bgcolor = \"#EEEEEE\";");		// cor de fundo
		graph.addln("rankdir = LR;");				// direcao do grafico: esq > dir
		graph.addln("node [color=orange, peripheries=2, fontname=Verdana, width=.75, regular=true];");	// estilo de cada status (no)
		graph.addln("edge [style=bold];");			// estilo das acoes (arestas)
		
		// Gera os status (nos)
		for (Status status : statusList) {
			
			String statusProp = (Status.INITIAL_PSEUDO_STATE.equals(status))
					? "[color=green, label=\"" + splitLines(status.getNome(), MAX_LINE_SIZE_LABEL_STATUS_GRAPH) + "\"]" // estilo do status inicial
					: "[label=\"" + splitLines(status.getNome(), MAX_LINE_SIZE_LABEL_STATUS_GRAPH) + "\"]";
			
			graph.addln("" + status.getId() + " " + statusProp + ";");
		}
		
		// Gera as acoes (arestas)
		for (Transicao transicao : transicoes) {
			
			graph.addln("" + transicao.getStatusInicial().getId() + " -> " + transicao.getStatusFinal().getId() +
					" [label=\"" + splitLines(transicao.getAcao().getDescricao(), MAX_LINE_SIZE_LABEL_STATUS_GRAPH) + "\"];");
		}
		
		graph.addln(graph.end_graph());
		
		if (printDotFile)
			log.info(graph.getDotSource());
		
		graph.increaseDpi();   // 106 dpi
		
		return graph;
	}
	
	static String splitLines(String label, int maxLineSize) {
		
		String[] splitedArr = label.split("(?<=\\G.{" + maxLineSize + "})");
		String splited = "";
		
		for (int i=0; i < splitedArr.length; i++)
			splited += (i == 0) ? splitedArr[i] : splitedArr[i].replaceFirst(" ", "\n");
		
		return splited;
	}
}
