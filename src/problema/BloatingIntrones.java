package src.problema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.ProblemaRegSim.Symbol;
import src.utils.BinTree;

public class BloatingIntrones implements IBloating{

	@Override
	public void penalizar(ArrayList<Individuo> poblacion, Random rand) {
		for(Individuo individuo : poblacion){
			reduceIndividuo(((BinTree<Symbol>)individuo.getGenotipo()).getRightChild());
			reduceIndividuo(((BinTree<Symbol>)individuo.getGenotipo()).getLeftChild());
		}
	}

	public double reduceIndividuo(BinTree<Symbol> arbol){
		double res;
		double left = 0;
		double right = 0;
		if(!arbol.isLeaf()){
			left = reduceIndividuo(arbol.getLeftChild());
			right = reduceIndividuo(arbol.getRightChild());
		}
		switch(arbol.getElem().getSymbol()) {
			case INT: res = arbol.getElem().getValue(); break;
			case SUM: res = left + right; break;
			case SUB: res = manageSub(left, right); break;
			case MUL: res = manageMul(left, right); break;
			case X: res = Double.NaN; break;
			default: res  = 0;
		}
		//Comprobamos que no sea un INT porque sabemos que la X nunca va a ser cero
		if(res == 0)
			arbol.setTree(new BinTree<Symbol>(new Symbol(Symbol.Symbols.INT, 0)));
		else if(Double.compare(res, Double.NaN) != 0 && Arrays.asList(ProblemaRegSim.getEnterosPosibles()).contains((int)res))
			arbol.setTree(new BinTree<Symbol>(new Symbol(Symbol.Symbols.INT, (int)res)));

		return res;
	}

	private double manageSub(double left, double right) {
		if(Double.compare(left, Double.NaN) == 0 && Double.compare(right, Double.NaN) == 0)
			return 0;
		return left - right;
	}

	private double manageMul(double left, double right) {
		if(left == 0 || right == 0)
			return 0;
		else
			return left * right;
	}


	@Override
	public String toString() {
		return "Bloating Intrones";
	}
	
}
