package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;


public class SeleccionTorneoProb implements ISeleccion, Cloneable{
	final private int tamTorneo = 3;

	@Override
	public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand, Problema p) {
		
		ArrayList<Individuo> selected = new ArrayList<Individuo>();

		while(selected.size() < poblacion.size()){
			ArrayList<Individuo> torneo = new ArrayList<Individuo>();
			for(int i = 0; i < tamTorneo; i++){
				torneo.add(poblacion.get(rand.nextInt(poblacion.size())));
			}

			//Ordenamos el torneo por fitness de mayor a menor
			torneo.sort((a, b) -> Double.compare(b.getFitness(), a.getFitness()));
			selected.add(
				rand.nextDouble() > 0.5 ? p.build(torneo.get(0)) : p.build(torneo.get(torneo.size() - 1))
			);
		}

		return selected;
	}

	@Override
	public SeleccionTorneoDet clone() { 
		try {
			return (SeleccionTorneoDet)super.clone();
		} catch (CloneNotSupportedException e) {
			throw new IllegalArgumentException(e);
		} 
	}

	@Override
	public String toString() {
		return "Seleccion Torneo Prob";
	}
}
