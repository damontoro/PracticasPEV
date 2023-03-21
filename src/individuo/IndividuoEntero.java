package src.individuo;

import src.problema.ProblemaTSP.Ciudad;
import java.util.ArrayList;
import java.util.Collections;

public class IndividuoEntero extends Individuo{

	private ArrayList<Integer> genotipo;

	public IndividuoEntero(int numCiudades) {
		if(tamCromosoma == null)
			tamCromosoma = numCiudades;

		genotipo = new ArrayList<Integer>();
		for (int i = 0; i < Individuo.tamCromosoma; i++) {
			if(i == Ciudad.MADRID.getValue()) ++i;
			genotipo.add(i);
		}
		Collections.shuffle(genotipo);
	}

	public IndividuoEntero(IndividuoEntero i) {
		super(i);
		this.genotipo = new ArrayList<Integer>(i.genotipo);
	}

	public IndividuoEntero(ArrayList<Integer> genotipo) {
		this.genotipo = new ArrayList<Integer> (genotipo);
	}


	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getFenotipo() {
		return genotipo;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Integer> getGenotipo() {
		return genotipo;
	}
	
}
