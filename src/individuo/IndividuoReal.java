package src.individuo;

import java.util.ArrayList;

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

	public <T> IndividuoReal(ArrayList<Double> min, ArrayList<Double> max, int dimension, ArrayList<T> valores) {
		super(min, max);
		
		if(tamCromosoma == null){
			tamCromosoma = dimension;
			for(int i = 0; i < dimension; i++)
				tamGenes.add(1);
		}
		cromosoma = new ArrayList<Double>((ArrayList<Double>)valores);
	}

	public IndividuoReal(IndividuoReal ind) {
		super(ind);
		cromosoma = new ArrayList<Double>(ind.cromosoma);
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
