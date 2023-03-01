package src.seleccion;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;

public interface ISeleccion {
	
	public ArrayList<Individuo> select(ArrayList<Individuo> poblacion, Random rand);
}
