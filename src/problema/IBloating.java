package src.problema;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;

public interface IBloating {
	public void penalizar(ArrayList<Individuo> poblacion, Random rand);
}
