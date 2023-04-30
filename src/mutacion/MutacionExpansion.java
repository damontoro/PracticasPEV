package src.mutacion;

import java.util.Random;

import src.individuo.Individuo;
import src.individuo.IndividuoArboreo;
import src.problema.Problema;
import src.problema.ProblemaRegSim.Symbol;
import src.utils.BinTree;

public class MutacionExpansion implements IMutacion{

	@Override
	public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
		if(rand.nextDouble() > probMutacion) return individuo;

		BinTree<Symbol> arbol = individuo.getGenotipo();

		while(!arbol.isLeaf()){
			arbol = (rand.nextDouble() < 0.5) ? arbol.getLeftChild() : arbol.getRightChild();
		}
		int height = rand.nextInt(2, 6); //El maximo de profundidad es 5, le sumamos uno porque el limite superior es exclusivo
		arbol.setTree(((IndividuoArboreo)(individuo)).buildCreciente(0, height));

		return problema.build(individuo);
	}
	
	@Override
	public String toString() {
		return "Mutacion Expansion";
	}
}
