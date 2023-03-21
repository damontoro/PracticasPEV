package src.individuo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class IndividuoBinario extends Individuo{

	private ArrayList<Boolean> cromosoma;

	public IndividuoBinario(ArrayList<Double> min, ArrayList<Double> max, double precision) {
        super(min, max);

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

	public <T> IndividuoBinario(ArrayList<Double> min, ArrayList<Double> max, double precision, ArrayList<T> valores) {
		super(min, max);

        if(tamCromosoma == null){
            tamCromosoma = valores.size();
            for(int i = 0; i < min.size(); i++) {
                tamGenes.add(tamGen( min.get(i), max.get(i), precision));
            }
        }
        cromosoma = copiaProfunda((ArrayList<Boolean>) valores);
	}

    public IndividuoBinario(IndividuoBinario ind) {
        super(ind);
        cromosoma = copiaProfunda(ind.cromosoma);
    }

    private ArrayList<Boolean> copiaProfunda(ArrayList<Boolean> valores) {
        List<Boolean> copia = valores.stream().collect(Collectors.toList());
	    ArrayList<Boolean> resul = new ArrayList<Boolean>();
        for(int i = 0; i < tamCromosoma; i++)
            resul.add((Boolean) copia.remove(random.nextInt(copia.size())));
        return resul;
    }

	@Override
	public ArrayList<Boolean> getGenotipo() {
		return cromosoma;
	}

    public int tamGen(double min, double max, double precision) {
		return (int) (Math.log10(((max - min) / precision) + 1) / Math.log10(2));
	}

    public double calcPrecision(double min, double max, int tamGen) {
        return (max - min) / (Math.pow(2, tamGen) - 1);
    }

    @Override
    public ArrayList<Double> getFenotipo(){
		int posAct = 0;
        ArrayList<Double> fenotipo = new ArrayList<Double>();
        for(int i = 0; i < tamGenes.size(); i++){
            fenotipo.add(getFenotipo(i, posAct));
			posAct += tamGenes.get(i);
        }
        return fenotipo;
    }

    private Double getFenotipo(int i, int posAct){
       return min.get(i) + bin2dec(i, posAct) * ((max.get(i) - min.get(i)) / (Math.pow(2, tamGenes.get(i)) - 1));
    }

    private int bin2dec(int i, int iniPos){
        int dec = 0;

        for(int j = iniPos; j < iniPos + tamGenes.get(i); j++)
            dec += cromosoma.get(j) ? Math.pow(2, j - iniPos) : 0;

        return dec;
    }
    
}
