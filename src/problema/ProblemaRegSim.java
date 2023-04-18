package src.problema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import src.cruce.ICruce;
import src.individuo.Individuo;
import src.individuo.IndividuoArboreo;
import src.mutacion.IMutacion;
import src.seleccion.ISeleccion;
import src.utils.TipoConst;
import src.utils.Pair;
import src.utils.BinTree;
import src.utils.BinTree.Node;

import src.utils.TipoProblema;

public class ProblemaRegSim extends Problema{


	public static enum Symbols {
		INT(0), SUM(1), SUB(2), MUL(3), X(4);
		
		private final int id;
		private Symbols(int id) {this.id = id;}
		public final int getValue() {return id;}

		@Override
		public String toString() {
			switch(id) {
				case 0: return Integer.valueOf(getValue()).toString();
				case 1: return "+";
				case 2: return "-";
				case 3: return "*";
				case 4: return "x";
				default: return "ERROR";
			}
		}


	} //Int solo puede ser -2, -1, 0, 1, 2

	//x, f(x)
	private ArrayList<Pair<Double, Double>> dataSet;

	public ProblemaRegSim() {
		super();
		tipo = TipoProblema.MINIMIZACION;
		dataSet = new ArrayList<Pair<Double, Double>>();

		loadDataSet();
	}

	public static List<Symbols> getLiterals() {
		return Arrays.asList(Symbols.INT, Symbols.X);
	}
	public static List<Symbols> getFunctions() {
		return Arrays.asList(Symbols.SUM, Symbols.SUB, Symbols.MUL);
	}

	private double calcArbol(BinTree arbol, double value) {
		switch(arbol.getId()) {
			case INT: return arbol.getValue();
			case SUM: return calcArbol(arbol.getLeftChild(), value) + calcArbol(arbol.getRightChild(), value);
			case SUB: return calcArbol(arbol.getLeftChild(), value) - calcArbol(arbol.getRightChild(), value);
			case MUL: return calcArbol(arbol.getLeftChild(), value) * calcArbol(arbol.getRightChild(), value);
			case X: return value;
			default: return 0;
		}
	}

	private void loadDataSet() {
		for (double ini = -1.0; ini <= 1.0 ; ini += 0.02) {
			dataSet.add(new Pair<Double, Double>(ini,
				Math.pow(ini, 4) + 
				Math.pow(ini, 3) +
				Math.pow(ini, 2) +
				ini + 1)
			);
		}
	}


	@Override
	public Individuo build(TipoConst tipo) {
		return new IndividuoArboreo(tipo);
	}

	@Override
	public Individuo build(Individuo i) {
		return new IndividuoArboreo((IndividuoArboreo)i);
	}

	@Override
	public <T> Individuo build(ArrayList<T> valores) { //Esto recibe el inorden
		throw new UnsupportedOperationException("Unimplemented method 'build'");
	}


	@Override
	public <T> double evaluar(Individuo i) {
		try{
			double fitness = 0;

			for(int j = 0; j < dataSet.size(); j++){
				double valueInd;
				valueInd = calcArbol((BinTree)i.getGenotipo(), dataSet.get(j).getFirst());
				fitness += Math.pow(dataSet.get(j).getSecond() - valueInd, 2);
			}
			fitness = Math.sqrt(fitness);
			return fitness;
		}catch(Exception e) {
			System.out.println("Error en evaluar");
			return 0;
		}
	}

	@Override
	public ArrayList<IMutacion> getMutaciones() {
		return new ArrayList<>();
	}

	@Override
	public ArrayList<ICruce> getCruces() {
		return new ArrayList<>();
	}

	@Override
	public ArrayList<ISeleccion> getSelecciones() {
		return new ArrayList<>();
	}
	



}
