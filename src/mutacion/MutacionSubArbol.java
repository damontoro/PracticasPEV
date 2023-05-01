package src.mutacion;

import java.util.Random;

import src.individuo.Individuo;
import src.individuo.IndividuoArboreo;
import src.problema.Problema;
import src.problema.ProblemaRegSim.Symbol;
import src.utils.BinTree;

public class MutacionSubArbol implements IMutacion{

    private static double probStop = 0.5;

    @Override
    public Individuo mutar(Individuo individuo, Problema problema, Random rand, double probMutacion) {
        if (rand.nextDouble() > probMutacion) return individuo;

        BinTree<Symbol> arbol = individuo.getGenotipo();
        if (arbol.isLeaf()) return individuo;
        int altura = arbol.getHeight();
        int incremento = (1 / altura) / 2;

        arbol = (rand.nextDouble() < 0.5) ? arbol.getLeftChild() : arbol.getRightChild();

        while (!arbol.isLeaf() && rand.nextDouble() < probStop) {
            arbol = (rand.nextDouble() < 0.5) ? arbol.getLeftChild() : arbol.getRightChild();
            probStop += incremento;
        }

        int height = rand.nextInt(2, altura + 1);
        arbol.setTree(((IndividuoArboreo) (individuo)).buildCreciente(0, height));

        return problema.build(individuo);
    }

    @Override
    public String toString() {
        return "Mutacion Subarbol";
    }
    
}
