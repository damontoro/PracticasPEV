package src.problema;

import java.util.ArrayList;


import src.cruce.ICruce;
import src.individuo.Individuo;
import src.mutacion.IMutacion;
import src.seleccion.ISeleccion;
import src.utils.Pair;
import src.utils.BinTree;
import src.utils.BinTree.Node;

import src.utils.TipoProblema;

public class ProblemaRegSim extends Problema{


	public static enum Literals {
		INT(0), SUM(1), SUB(2), MUL(3), X(4);
		
		private final int id;
		private Literals(int id) {this.id = id;}
		public final int getValue() {return id;}
	} //Int solo puede ser -2, -1, 0, 1, 2

	//x, f(x)
	private ArrayList<Pair<Integer, Integer>> dataSet;

	public ProblemaRegSim() {
		super();
		tipo = TipoProblema.MINIMIZACION;
	}


	private double calcArbol(BinTree arbol, double value) {
		switch(arbol.getRoot().getId()) {
			case INT: return arbol.getRoot().getValue();
			case SUM: return calcArbol(arbol.getLeftChild(), value) + calcArbol(arbol.getRightChild(), value);
			case SUB: return calcArbol(arbol.getLeftChild(), value) - calcArbol(arbol.getRightChild(), value);
			case MUL: return calcArbol(arbol.getLeftChild(), value) * calcArbol(arbol.getRightChild(), value);
			case X: return value;
			default: return 0;
		}
	}

	@Override
	public Individuo build() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'build'");
	}

	@Override
	public Individuo build(Individuo i) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'build'");
	}

	@Override
	public <T> Individuo build(ArrayList<T> valores) { //Esto recibe el inorden
		throw new UnsupportedOperationException("Unimplemented method 'build'");
	}


	@Override
	public <T> double evaluar(Individuo i) {
		double fitness = 0;

		for(int j = 0; j < dataSet.size(); j++){
			double valueInd;
			valueInd = calcArbol((BinTree)i.getGenotipo(), dataSet.get(j).getFirst());
			fitness += Math.pow(Math.abs(valueInd - dataSet.get(j).getSecond()), 2);
		}
		fitness = Math.sqrt(fitness);
		return fitness;
	}

	@Override
	public ArrayList<IMutacion> getMutaciones() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getMutaciones'");
	}

	@Override
	public ArrayList<ICruce> getCruces() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getCruces'");
	}

	@Override
	public ArrayList<ISeleccion> getSelecciones() {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getSelecciones'");
	}
	



}
