package src.individuo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
		cromosoma = copiaProfunda((ArrayList<Double>) valores);
	}

	public IndividuoReal(IndividuoReal ind) {
		super(ind);
		cromosoma = copiaProfunda(ind.cromosoma);
	}

	private ArrayList<Double> copiaProfunda(ArrayList<Double> valores) {
		List<Double> copia = valores.stream().collect(Collectors.toList());
		ArrayList<Double> resul = new ArrayList<Double>();
		for(int i = 0; i < tamCromosoma; i++)
			resul.add((Double) copia.remove(random.nextInt(copia.size())));
		return resul;
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
