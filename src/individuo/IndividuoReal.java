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

	public <T> IndividuoReal(ArrayList<T> valores) {

		ArrayList<Double> aux = new ArrayList<Double>();
		for(int i = 0; i < valores.size(); i++)
			aux.add((Double)valores.get(i));

		cromosoma = new ArrayList<Double>(aux);
	}

	public IndividuoReal(IndividuoReal ind) {
		super(ind);
		cromosoma = new ArrayList<Double>(ind.cromosoma);
	}

	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Double> getFenotipo() {
		return cromosoma;
	}

	
	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<Double> getGenotipo() {
		return cromosoma;
	}
	
}
