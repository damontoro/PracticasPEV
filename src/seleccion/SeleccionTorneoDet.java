package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.utils.TipoProblema;

public class SeleccionTorneoDet implements ISeleccion, Cloneable{

	final private int tamTorneo = 3;

	@Override
	public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand, TipoProblema tipo) {
		
		ArrayList<Individuo> selected = new ArrayList<Individuo>();

		while(selected.size() < poblacion.size()){
			ArrayList<Individuo> torneo = new ArrayList<Individuo>();

			for(int i = 0; i < tamTorneo; i++){
				torneo.add(poblacion.get(rand.nextInt(poblacion.size())));
			}

			//Ordenamos la poblacion por fitness
			torneo.sort((a, b) -> (tipo == TipoProblema.MAXIMIZACION) ? 
				Double.compare(b.getFitness(), a.getFitness()) : //De mayor a menor
				Double.compare(a.getFitness(), b.getFitness())); //De menor a mayor

			selected.add(torneo.get(0));
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
		return "Seleccion Torneo Det";
	}
	
}
