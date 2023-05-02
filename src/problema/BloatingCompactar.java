package src.problema;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;

public class BloatingCompactar implements IBloating{

	@Override
	public void penalizar(double ejecucionActual, double numGeneraciones, ArrayList<Individuo> poblacion, Random rand) {
		new BloatingIntrones().penalizar(ejecucionActual, numGeneraciones, poblacion, rand);
		new BloatingFundamentado().penalizar(ejecucionActual, numGeneraciones, poblacion, rand);
	}
	
	@Override
	public String toString() {
		return "Intrones + Fundamentado";
	}
}
