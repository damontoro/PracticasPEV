package src.problema;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.ProblemaRegSim.Symbol;
import src.utils.BinTree;

public class BloatingFundamentado implements IBloating{

	@Override
	public void penalizar(ArrayList<Individuo> poblacion, Random rand) {
		double coeficiente;
		double[] fitness = new double[poblacion.size()];
		double[] profundidad = new double[poblacion.size()];

		for(int i = 0; i < poblacion.size(); i++){
			fitness[i] = poblacion.get(i).getFitness();
			profundidad[i] = ((BinTree<Symbol>)poblacion.get(i).getGenotipo()).getHeight();
		}

		coeficiente = calcularCovarianza(fitness, profundidad) / calcularVarianza(fitness);

		for(Individuo i : poblacion){
			i.setFitness(i.getFitness() + coeficiente * ((BinTree<Symbol>)i.getGenotipo()).getHeight());
		}
	}

	//Hecho por chatGPT (no vamos a mentir)
	private static double calcularVarianza(double[] valores) {
		int n = valores.length;
		double media = 0.0;
		double sumatoria = 0.0;
		
		// Calcular la media
		for (int i = 0; i < n; i++) {
			media += valores[i];
		}
		media /= n;
		
		// Calcular la sumatoria
		for (int i = 0; i < n; i++) {
			sumatoria += Math.pow((valores[i] - media), 2);
		}
		
		// Calcular la varianza
		double varianza = sumatoria / (n - 1);
		return varianza;
	}

	//Este tambien está hecho por chatGPT juju
	private static double calcularCovarianza(double[] valores1, double[] valores2) {
		int n = valores1.length;
		double media1 = 0.0;
		double media2 = 0.0;
		double sumatoria = 0.0;
		
		// Calcular las medias
		for (int i = 0; i < n; i++) {
			media1 += valores1[i];
			media2 += valores2[i];
		}
		media1 /= n;
		media2 /= n;
		
		// Calcular la sumatoria
		for (int i = 0; i < n; i++) {
			sumatoria += (valores1[i] - media1) * (valores2[i] - media2);
		}
		
		// Calcular la covarianza
		double covarianza = sumatoria / (n - 1);
		return covarianza;
	}
	
}
