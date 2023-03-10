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
		for(int i = 0; i < padres.size(); i+=2){

			if(rand.nextDouble() < probCruce){
				hijos.add(padres.get(i));
				hijos.add(padres.get(i + 1));
				continue;
			}

            int puntoCruce1 = rand.nextInt(padres.get(i).getTamCromosoma());
            int puntoCruce2;

            do
                puntoCruce2 = rand.nextInt(padres.get(i).getTamCromosoma());
            while(puntoCruce2 == puntoCruce1);


            ArrayList<T> genotipo1 = padres.get(i).getGenotipo(); //Genotipo padre 1
            ArrayList<T> genotipo2 = padres.get(i + 1).getGenotipo();

            ArrayList<T> genotipoHijo1 = new ArrayList<T>(genotipo1.size()); //Genotipo hijo 2
            ArrayList<T> genotipoHijo2 = new ArrayList<T>(genotipo2.size());

            Set<T> setHijo1 = new HashSet<T>(); //Para comprobar si un numero esta en el intervalo
            Set<T> setHijo2 = new HashSet<T>();
            Map<T, Integer> mapPadre1 = new HashMap<T, Integer>(); //Para saber la posicion de un numero en el genotipo
            Map<T, Integer> mapPadre2 = new HashMap<T, Integer>();

            for(int j = puntoCruce1; j < puntoCruce2; j++){
                genotipoHijo1.set(j, genotipo2.get(j));
                setHijo1.add(genotipo2.get(j));
                mapPadre1.put(genotipo1.get(j), j);

                genotipoHijo2.set(j, genotipo1.get(j));
                setHijo2.add(genotipo1.get(j));
                mapPadre2.put(genotipo2.get(j), j);
            }

            for(int j = 0; j < genotipo1.size(); j++){ //for para el hijo1
                if(j == puntoCruce1)
                    j = puntoCruce2;
                if(setHijo1.contains(genotipo1.get(j))){
                    genotipoHijo1.set(j, genotipo2.get(mapPadre1.get(genotipo1.get(j))));
                }
                else
                    genotipoHijo1.set(j, genotipo1.get(j));
            }
            for(int j = 0; j < genotipo2.size(); j++){ //for para el hijo2
                if(j == puntoCruce1)
                    j = puntoCruce2;
                if(setHijo2.contains(genotipo2.get(j))){
                    genotipoHijo2.set(j, genotipo1.get(mapPadre2.get(genotipo2.get(j))));
                }
                else
                    genotipoHijo2.set(j, genotipo2.get(j));
            }

            hijos.add(problema.build(genotipoHijo1));
            hijos.add(problema.build(genotipoHijo2));
		}
		return hijos;
    }
    
}
