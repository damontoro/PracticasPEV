package src.individuo;

import java.util.ArrayList;

public class IndividuoBinario extends Individuo{

	private ArrayList<Boolean> cromosoma;

	public IndividuoBinario(ArrayList<Double> min, ArrayList<Double> max, double precision) {
        super(min, max, precision);

        if(tamCromosoma == null){
            tamCromosoma = 0;
            for(int i = 0; i < min.size(); i++) {
                tamGenes.add(tamGen(min.get(i), max.get(i), precision));
                tamCromosoma += tamGenes.get(i);
            }
        }
		cromosoma = new ArrayList<Boolean>();

        for(int i = 0; i < tamCromosoma; i++)
            cromosoma.add(random.nextBoolean());
    }

	public IndividuoBinario(ArrayList<Double> min, ArrayList<Double> max, double precision, ArrayList<Boolean> valores) {
		super(min, max, precision);

        if(tamCromosoma == null){
            tamCromosoma = valores.size();
            for(int i = 0; i < min.size(); i++) {
                tamGenes.add(tamGen( min.get(i), max.get(i), precision));
            }
        }
	    cromosoma = valores;

	}

	@Override
    @SuppressWarnings("unchecked")
	public ArrayList<Boolean> getGenotipo() {
		return cromosoma;
	}

    @Override
    public int tamGen(double min, double max, double precision) {
		return (int) (Math.log10(((max - min) / precision) + 1) / Math.log10(2));
	}

    @Override
    public ArrayList<Double> getFenotipo(){
        ArrayList<Double> fenotipo = new ArrayList<Double>();
        for(int i = 0; i < tamGenes.size(); i++){
            fenotipo.add(getFenotipo(i));
        }
        return fenotipo;
    }

    private Double getFenotipo(int i){
       return min.get(i) + bin2dec(i) * ((max.get(i) - min.get(i)) / (Math.pow(2, tamGenes.get(i)) - 1));
    }

    private int bin2dec(int i){
        int dec = 0;
        int inicio = (i == 0) ? 0 : tamGenes.get(i - 1);

        for(int j = inicio; j < inicio + tamGenes.get(i); j++)
            dec += cromosoma.get(j) ? Math.pow(2, j - inicio) : 0;

        return dec;
    }
    
}
