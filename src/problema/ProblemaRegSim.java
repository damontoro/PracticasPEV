package src.problema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import src.cruce.*;
import src.mutacion.*;
import src.seleccion.*;
import src.individuo.Individuo;
import src.individuo.IndividuoArboreo;
import src.problema.ProblemaRegSim.Symbol.Symbols;
import src.utils.TipoConst;
import src.utils.Pair;
import src.utils.BinTree;

import src.utils.TipoProblema;

public class ProblemaRegSim extends Problema{

	private static final int[] enterosPosibles = {-2, -1, 1, 2};
	private static final int MIN_DEPTH = 2;

	public ProblemaRegSim() {
		super();
		tipo = TipoProblema.MINIMIZACION;

		cruces.add(new CruceIntercambio());
		bloatings.add(new BloatingFundamentado());
		bloatings.add(new BloatingTarpeian());
		bloatings.add(new BloatingIntrones());
		bloatings.add(new BloatingCompactar());
		
		mutaciones.add(new MutacionTerminal());
		mutaciones.add(new MutacionFuncional());
		mutaciones.add(new MutacionExpansion());
		mutaciones.add(new MutacionContraccion());
		mutaciones.add(new MutacionPermutacion());
		mutaciones.add(new MutacionHoist());
		mutaciones.add(new MutacionSubArbol());

		selecciones.add(new SeleccionRanking());
		selecciones.add(new SeleccionRuleta());
		selecciones.add(new SeleccionTorneoDet());
		selecciones.add(new SeleccionTorneoProb());
		selecciones.add(new SeleccionTruncamiento());
		selecciones.add(new SeleccionRestos());
		selecciones.add(new SeleccionEstocastica());
	}

	public static List<Symbols> getLiterals() {
		return Arrays.asList(Symbols.INT, Symbols.X);
	}
	public static List<Symbols> getFunctions() {
		return Arrays.asList(Symbols.SUM, Symbols.SUB, Symbols.MUL);
	}
	public static int[] getEnterosPosibles() {
		return enterosPosibles;
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


	@Override
	public Individuo build(Individuo i) {
		return new IndividuoArboreo((IndividuoArboreo)i);
	}

	@Override
	public <T> Individuo build(ArrayList<T> valores) { //Esto recibe el inorden
		throw new UnsupportedOperationException("Unimplemented method 'build'");
	}

	@Override
	public ArrayList<Individuo> initPoblacion(int tamPoblacion, TipoConst tipo, int alturaMaxima){
		ArrayList<Individuo> poblacion = new ArrayList<Individuo>();

		if(tipo.equals(TipoConst.RAMPED_AND_HALF))
			return iniRampedAndHalf(tamPoblacion, alturaMaxima);

		for(int i = 0; i < tamPoblacion; i++)
			poblacion.add(new IndividuoArboreo(tipo, alturaMaxima));
		return poblacion;
	}

	private ArrayList<Individuo> iniRampedAndHalf(int tamPoblacion, int alturaMaxima) {
		ArrayList<Individuo> poblacion = new ArrayList<Individuo>();
		int numGrupos = alturaMaxima - 1;

		int numIndCompletos = (tamPoblacion / numGrupos) / 2;
		int numIndCrecientes = (tamPoblacion / numGrupos) - numIndCompletos;

		for(int profActual = MIN_DEPTH; profActual <= alturaMaxima; profActual++){
			for(int i = 0; i < numIndCompletos; i++)
				poblacion.add(new IndividuoArboreo(TipoConst.COMPLETO, profActual));
			for(int i = 0; i < numIndCrecientes; i++)
				poblacion.add(new IndividuoArboreo(TipoConst.CRECIENTE, profActual));
		}
		return poblacion;
	}

	@SuppressWarnings("unchecked")
	public Pair<ArrayList<Double>, ArrayList<Double>> getDataSet(Individuo i) {
		Pair<ArrayList<Double>, ArrayList<Double>> res = new Pair<ArrayList<Double>, ArrayList<Double>>(
			new ArrayList<Double>(), new ArrayList<Double>()
		);
		for (int j = 0; j < dataSet.getFirst().size(); j++) {
			res.getFirst().add(dataSet.getFirst().get(j));
			res.getSecond().add(calcArbol((BinTree<Symbol>)i.getGenotipo(), dataSet.getFirst().get(j)));
		}
		return res;
	}

	@Override
	@SuppressWarnings("unchecked")
	public <T> double evaluar(Individuo i) {
		try{
			double fitness = 0;

			for(int j = 0; j < dataSet.getFirst().size(); j++){
				double valueInd;
				valueInd = calcArbol((BinTree<Symbol>)i.getGenotipo(), dataSet.getFirst().get(j));
				fitness += Math.pow(dataSet.getSecond().get(j) - valueInd, 2);
			}
			fitness = Math.sqrt(fitness);
			if (fitness < 0.0000000001) 
				fitness = 0;
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

	@Override
	public String toString() {
		return "Regresion simbolica";
	}

}
