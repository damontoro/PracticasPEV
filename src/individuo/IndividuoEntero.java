package src.individuo;

import java.util.ArrayList;

public class IndividuoEntero extends Individuo{

	private ArrayList<Character> genotipo;

	public IndividuoEntero(int tamGenes){
		this.genotipo = new ArrayList<>();
		for(int i = 0; i < tamGenes; i++){
			genotipo.add((char)random.nextInt(256));
		}
	}

	public IndividuoEntero(IndividuoEntero i) {
		super(i);
		this.genotipo = new ArrayList<Character>();
		this.genotipo.addAll(i.getGenotipo());
	}

	public IndividuoEntero(ArrayList<Character> genotipo) {
		this.genotipo = new ArrayList<>();
		this.genotipo.addAll(genotipo);
	}


	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Character> getFenotipo() {
		//TODO
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Character> getGenotipo() {
		return genotipo;
	}
	
}
