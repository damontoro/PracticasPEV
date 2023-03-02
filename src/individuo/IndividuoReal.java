package src.individuo;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class IndividuoReal extends Individuo{

	private ArrayList<Double> cromosoma;

	public IndividuoReal(ArrayList<Double> min, ArrayList<Double> max, int dimension) {
		super(min, max);

		if(tamCromosoma == null){
			tamCromosoma = dimension;
			for(int i = 0; i < dimension; i++)
				tamGenes.add(1);
		}
		cromosoma = new ArrayList<Double>();

		for(int i = 0; i < tamCromosoma; i++)
			cromosoma.add(random.nextDouble(min.get(0), max.get(0)));
	}

	public IndividuoReal(ArrayList<Double> min, ArrayList<Double> max, int dimension, ArrayList<Double> valores) {
		super(min, max);
		
		if(tamCromosoma == null){
			tamCromosoma = dimension;
			for(int i = 0; i < dimension; i++)
				tamGenes.add(1);
		}
		cromosoma = valores;
	}

	@Override
	public int tamGen(double min, double max, double precision) {
		return 1;
	}

	@Override
	public ArrayList<Double> getFenotipo() {
		return cromosoma;
	}

	
	@Override
	public <T> ArrayList<T> getGenotipo() {
		return (ArrayList<T>) cromosoma;
	}
	
}
