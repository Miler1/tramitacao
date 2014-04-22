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
import br.ufla.lemaf.tramitacao.model.Condicao;
import br.ufla.lemaf.tramitacao.model.Transicao;
import br.ufla.lemaf.tramitacao.repository.FluxoRepository;
import br.ufla.lemaf.tramitacao.repository.TransicaoRepository;

@Service
public class FluxoService {
	
	private static final int MAX_LINE_SIZE_LABEL_CONDICAO_GRAPH = 18;
	
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
		
		Collection<Condicao> condicaoSet = getCondicaoFromTransicoes(transicoes);
		
		GraphViz graph = generateGraph(transicoes, condicaoSet, printDotFile);

		return graph.getGraph( graph.getDotSource(), GraphViz.OUTPUT_TYPE );
	}
	
	private Collection<Condicao> getCondicaoFromTransicoes(List<Transicao> transicoes) {
		
		Set<Condicao> condicaoSet = new HashSet<Condicao>();
		
		for (Transicao transicao : transicoes) {
			condicaoSet.add(transicao.getCondicaoInicial());
			condicaoSet.add(transicao.getCondicaoFinal());
		}
		
		return condicaoSet;
	}
	
	private GraphViz generateGraph(Collection<Transicao> transicoes, Collection<Condicao> condicaoList, boolean printDotFile) {
		
		GraphViz graph = new GraphViz();
		graph.addln(graph.start_graph());
		
		// Estilos do grafico
		graph.addln("bgcolor = \"#EEEEEE\";");		// cor de fundo
		graph.addln("rankdir = LR;");				// direcao do grafico: esq > dir
		graph.addln("node [color=orange, peripheries=2, fontname=Verdana, width=.75, regular=true];");	// estilo de cada condicao (no)
		graph.addln("edge [style=bold];");			// estilo das acoes (arestas)
		
		// Gera os condicao (nos)
		for (Condicao condicao : condicaoList) {
			
			String condicaoProp = (Condicao.INITIAL_PSEUDO_STATE.equals(condicao))
					? "[color=green, label=\"" + splitLines(condicao.getNome(), MAX_LINE_SIZE_LABEL_CONDICAO_GRAPH) + "\"]" // estilo do condicao inicial
					: "[label=\"" + splitLines(condicao.getNome(), MAX_LINE_SIZE_LABEL_CONDICAO_GRAPH) + "\"]";
			
			graph.addln("" + condicao.getId() + " " + condicaoProp + ";");
		}
		
		// Gera as acoes (arestas)
		for (Transicao transicao : transicoes) {
			
			graph.addln("" + transicao.getCondicaoInicial().getId() + " -> " + transicao.getCondicaoFinal().getId() +
					" [label=\"" + splitLines(transicao.getAcao().getDescricao(), MAX_LINE_SIZE_LABEL_CONDICAO_GRAPH) + "\"];");
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
