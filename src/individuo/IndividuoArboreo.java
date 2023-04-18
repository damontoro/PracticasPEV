package src.individuo;

import java.util.ArrayList;


import src.problema.ProblemaRegSim.Symbols;
import src.problema.ProblemaRegSim;
import src.utils.BinTree;
import src.utils.BinTree.Node;
import src.utils.TipoConst;

public class IndividuoArboreo extends Individuo{

	private static final int MIN_DEPTH = 2;
	private static final int MAX_DEPTH = 5;
	private int ini_depth;
	private BinTree genotipo;
	

	public IndividuoArboreo(TipoConst tipo) {
		super();
		ini_depth = random.nextInt(MIN_DEPTH, MAX_DEPTH + 1);

		while(tipo.equals(TipoConst.RANDOM))
			tipo = TipoConst.values()[(int) (Math.random() * TipoConst.values().length)];

		switch(tipo) {
			case CRECIENTE: genotipo = buildCreciente(0); 
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
		if(height == ini_depth - 1)
			return new BinTree(ProblemaRegSim.getLiterals().get(random.nextInt(0, 2)),
					          random.nextInt(-2,3));

		return new BinTree(
				buildCompleto(height + 1), 
				buildCompleto(height + 1), 
				ProblemaRegSim.getFunctions().get(random.nextInt(0, 3)),
				null);
	}

	private BinTree buildCreciente(int height){
		if(height == ini_depth - 1)
			return new BinTree(ProblemaRegSim.getLiterals().get(random.nextInt(0, 2)),
					          random.nextInt(-2,3));
		Symbols s = Symbols.values()[random.nextInt(0, Symbols.values().length)];
		if(ProblemaRegSim.getLiterals().contains(s))
			return new BinTree(s, random.nextInt(-2,3));

		return new BinTree(
				buildCreciente(height + 1), 
				buildCreciente(height + 1), 
				s,
				null);
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
