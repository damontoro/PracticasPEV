package src.mutacion;

import java.util.Random;

import src.individuo.Individuo;
import src.problema.Problema;
import src.problema.ProblemaRegSim.Symbol;
import src.utils.BinTree;

public class MutacionHoist implements IMutacion{

    private static final double probStop = 0.8;

    @Override
    public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
        if (rand.nextDouble() > probMutacion) return individuo;

        BinTree<Symbol> arbol = individuo.getGenotipo();
        BinTree<Symbol> aux = individuo.getGenotipo();

        while (!arbol.isLeaf() && rand.nextDouble() < probStop) {
            if(arbol.getLeftChild().isLeaf() && arbol.getRightChild().isLeaf())
                break;
            if(rand.nextDouble() < 0.5 && !arbol.getLeftChild().isLeaf())
                arbol = arbol.getLeftChild();
            else if(!arbol.getRightChild().isLeaf())
                arbol = arbol.getRightChild();
        }

        aux.setTree(arbol);

        return problema.build(individuo);
    }

    @Override
    public String toString() {
        return "Mutacion Hoist";
    }
    
}
