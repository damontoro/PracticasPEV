package src.cruce;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;

import src.individuo.Individuo;
import src.problema.Problema;

public class CrucePMX implements ICruce{

    @Override
    public <T> ArrayList<Individuo> cruzar(ArrayList<Individuo> padres, Problema problema, Random rand,
            double probCruce) {
        ArrayList<Individuo> hijos = new ArrayList<Individuo>();
		for(int i = 0; i < padres.size() - (padres.size() % 2); i+=2){

			if(rand.nextDouble() > probCruce){
				hijos.add(padres.get(i));
				hijos.add(padres.get(i + 1));
				continue;
			}
			int puntoCruce1 = rand.nextInt(0, padres.get(i).getTamCromosoma() - 1);
            int puntoCruce2;

            do
                puntoCruce2 = rand.nextInt(0, padres.get(i).getTamCromosoma() - 1);
            while(puntoCruce2 == puntoCruce1);

			if (puntoCruce1 > puntoCruce2){
				int aux = puntoCruce1;
				puntoCruce1 = puntoCruce2;
				puntoCruce2 = aux;
			}

            ArrayList<T> genotipoPadre1 = padres.get(i).getGenotipo(); //Genotipo padre 1
            ArrayList<T> genotipoPadre2 = padres.get(i + 1).getGenotipo();

            ArrayList<T> genotipoHijo1 = new ArrayList<T>(); //Genotipo hijo 2
            ArrayList<T> genotipoHijo2 = new ArrayList<T>();

			for(int j = 0; j < genotipoPadre1.size(); j++){
				genotipoHijo1.add(null);
				genotipoHijo2.add(null);
			}

            Set<T> setHijo1 = new HashSet<T>(); //Para comprobar si un numero esta en el intervalo
            Set<T> setHijo2 = new HashSet<T>();
            Map<T, Integer> mapPadre1 = new HashMap<T, Integer>(); //Para saber la posicion de un numero en el genotipo
            Map<T, Integer> mapPadre2 = new HashMap<T, Integer>();

            for(int j = puntoCruce1; j < puntoCruce2; j++){
                genotipoHijo1.set(j, genotipoPadre2.get(j));
                setHijo1.add(genotipoPadre2.get(j));
                mapPadre2.put(genotipoPadre2.get(j), j);

                genotipoHijo2.set(j, genotipoPadre1.get(j));
                setHijo2.add(genotipoPadre1.get(j));
                mapPadre1.put(genotipoPadre1.get(j), j);
            }
            T val;
            for(int j = 0; j < genotipoPadre1.size(); j++){ //for para el hijo1
                if(j == puntoCruce1)
                    j = puntoCruce2;
                val =  genotipoPadre1.get(j);
                while(setHijo1.contains(val)){
                    val = genotipoPadre1.get(mapPadre2.get(val));
                }
                genotipoHijo1.set(j, val);
            }
            for(int j = 0; j < genotipoPadre2.size(); j++){ //for para el hijo2
                if(j == puntoCruce1)
                    j = puntoCruce2;
                val = genotipoPadre2.get(j);
                while(setHijo2.contains(val)){
                    val = genotipoPadre2.get(mapPadre1.get(val));
                }
                genotipoHijo2.set(j, val);
            }
            

            hijos.add(problema.build(genotipoHijo1));
            hijos.add(problema.build(genotipoHijo2));
		}
		return hijos;
    }
    
	@Override
	public String toString() {
		return "Cruce PMX";
	}
}
