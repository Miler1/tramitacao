package br.ufla.lemaf.tramitacao.vo;

import java.util.List;

public class ConjuntoTramites {

	private List<Tramite>	tramites;

	public ConjuntoTramites() {

	}

	public ConjuntoTramites(List<Tramite> tramites) {
		this.tramites = tramites;
	}

	public List<Tramite> getTramites() {
		return tramites;
	}

	public void setTramites(List<Tramite> tramites) {
		this.tramites = tramites;
	}

}
