package src.individuo;

import java.util.ArrayList;

import src.problema.ProblemaRegSim;
import src.utils.BinTree;
import src.utils.BinTree.Node;
import src.utils.TipoConst;

public class IndividuoArboreo extends Individuo{

	private static final int MIN_DEPTH = 2;
	private static final int MAX_DEPTH = 5;
	private int ini_depth = MAX_DEPTH;
	private BinTree genotipo;
	

	public IndividuoArboreo(TipoConst tipo) {
		super();

		while(tipo.equals(TipoConst.RANDOM))
			tipo = TipoConst.values()[(int) (Math.random() * TipoConst.values().length)];

		switch(tipo) {
			case CRECIENTE: //buildCreciente(); 
				break;
			case COMPLETO: genotipo = buildCompleto(0); 
				break;
			case RAMPED_AND_HALF: //buildRampedAndHalf(); 
				break;
			default: break;
		}
	}

	public IndividuoArboreo(IndividuoArboreo i) {
		super();
		genotipo = new BinTree(i.getGenotipo());
	}

	private BinTree buildCompleto(int height){
		if(height == ini_depth)
			return new BinTree(ProblemaRegSim.getLiterals().get(random.nextInt(0, 1)),
					          random.nextInt(-2,2));

		return new BinTree(
				buildCompleto(height + 1), 
				buildCompleto(height + 1), 
				ProblemaRegSim.getFunctions().get(random.nextInt(0, 3)));
	}


	@Override
	@SuppressWarnings("unchecked")
	public ArrayList<String> getFenotipo() {
		throw new UnsupportedOperationException();
	}

	@Override
	@SuppressWarnings("unchecked")
	public BinTree getGenotipo() {
		return genotipo;
	}
	
}
