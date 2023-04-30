package src.problema;

import java.util.ArrayList;
import java.util.Random;

import src.individuo.Individuo;
import src.problema.ProblemaRegSim.Symbol;
import src.utils.BinTree;

public class BloatingIntrones implements IBloating{

	@Override
	public void penalizar(ArrayList<Individuo> poblacion, Random rand) {
		for(Individuo individuo : poblacion){
			reduceIndividuo(individuo.getGenotipo());
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
			case INT: res =  arbol.getElem().getValue(); break;
			case SUM: res = left + right; break;
			case SUB: res = left - right; break;
			case MUL: res = left * right; break;
			case X: res = 3.5; break;
			default: res  = 0;
		}
		//Comprobamos que no sea un INT porque sabemos que la X nunca va a ser cero
		if(!arbol.isLeaf() && res == left)
			arbol.setTree(arbol.getLeftChild());
		else if(!arbol.isLeaf() && res == right)
			arbol.setTree(arbol.getRightChild());
		else if(res == 0)
			arbol.setTree(new BinTree<Symbol>(new Symbol(Symbol.Symbols.INT, 0)));

		return res;
	}


	@Override
	public String toString() {
		return "Bloating Intrones";
	}
	
}
