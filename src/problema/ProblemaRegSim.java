package src.problema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import src.cruce.ICruce;
import src.individuo.Individuo;
import src.individuo.IndividuoArboreo;
import src.mutacion.IMutacion;
import src.mutacion.MutacionTerminal;
import src.problema.ProblemaRegSim.Symbol.Symbols;
import src.seleccion.ISeleccion;
import src.utils.TipoConst;
import src.utils.Pair;
import src.utils.BinTree;

import src.utils.TipoProblema;

public class ProblemaRegSim extends Problema{

	//x, f(x)
	private ArrayList<Pair<Double, Double>> dataSet;

	public ProblemaRegSim() {
		super();
		tipo = TipoProblema.MINIMIZACION;
		dataSet = new ArrayList<Pair<Double, Double>>();
		
		mutaciones.add(new MutacionTerminal());

		loadDataSet();
	}

	public static List<Symbols> getLiterals() {
		return Arrays.asList(Symbols.INT, Symbols.X);
	}
	public static List<Symbols> getFunctions() {
		return Arrays.asList(Symbols.SUM, Symbols.SUB, Symbols.MUL);
	}

	private double calcArbol(BinTree<Symbol> arbol, double value) {
		switch(arbol.getElem().symbol) {
			case INT: return arbol.getElem().getValue();
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
	@SuppressWarnings("unchecked")
	public <T> double evaluar(Individuo i) {
		try{
			double fitness = 0;

			for(int j = 0; j < dataSet.size(); j++){
				double valueInd;
				valueInd = calcArbol((BinTree<Symbol>)i.getGenotipo(), dataSet.get(j).getFirst());
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
		return mutaciones;
	}

	@Override
	public ArrayList<ICruce> getCruces() {
		return cruces;
	}

	@Override
	public ArrayList<ISeleccion> getSelecciones() {
		return selecciones;
	}

	public static class Symbol{

		private Symbols symbol;
		private Integer value;

		public Symbol(Symbols symbol, Integer value) {
			this.symbol = symbol;
			this.value = value;
		}

		public static enum Symbols {
			INT(0), SUM(1), SUB(2), MUL(3), X(4);

			private final Integer id;
			private Symbols(Integer id) {this.id = id;}
			public final int getValue() {return this.id;}

		} //Int solo puede ser -2, -1, 0, 1, 2

		@Override
		public String toString() {
			switch(this.symbol) {
				case INT: return Integer.valueOf(getValue()).toString();
				case SUM: return "+";
				case SUB: return "-";
				case MUL: return "*";
				case X: return "x";
				default: return "ERROR";
			}
		}

		public static Symbol getSymbol(String s) {
			switch(s) {
				case "+": return new Symbol(Symbols.SUM, null);
				case "-": return new Symbol(Symbols.SUB, null);
				case "*": return new Symbol(Symbols.MUL, null);
				case "x": return new Symbol(Symbols.X, null);
				default: return new Symbol(Symbols.INT, Integer.valueOf(s));
			}
		}

		public Symbols getSymbol() {
			return symbol;
		}

		public void setSymbol(Symbols symbol) {
			this.symbol = symbol;
		}

		public Integer getValue() {
			return value;
		}

		public void setValue(Integer value) {
			this.value = value;
		}

	} 



}
