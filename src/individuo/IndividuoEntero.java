package src.individuo;

import java.util.ArrayList;

import src.problema.ProblemaGramEvo;
import src.utils.UnsignedByte;

public class IndividuoEntero extends Individuo{

	private ArrayList<UnsignedByte> genotipo;

	public IndividuoEntero(int tamGenes){
		if(tamCromosoma == null)
			tamCromosoma = tamGenes;
		this.genotipo = new ArrayList<>();
		for(int i = 0; i < tamGenes; i++){
			genotipo.add(new UnsignedByte(random.nextInt(256)));
		}
	}

	public IndividuoEntero(IndividuoEntero i) {
		super(i);
		this.genotipo = new ArrayList<UnsignedByte>();
		for(UnsignedByte b : i.getGenotipo())
			this.genotipo.add(new UnsignedByte(b));

		if(tamCromosoma == null)
			tamCromosoma = i.getGenotipo().size();
	}

	public IndividuoEntero(ArrayList<UnsignedByte> genotipo) {
		this.genotipo = new ArrayList<>();
		for(UnsignedByte b : genotipo)
			this.genotipo.add(new UnsignedByte(b));

		if(tamCromosoma == null)
			tamCromosoma = genotipo.size();
	}


	@Override
	@SuppressWarnings("unchecked")
	public String getFenotipo() {
		return toString();
	}

	@Override
	public String toString() {
		return ProblemaGramEvo.decode(genotipo);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<UnsignedByte> getGenotipo() {
		return genotipo;
	}
	
}
