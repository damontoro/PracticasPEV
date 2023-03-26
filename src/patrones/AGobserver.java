package src.patrones;

import src.AlgoritmoGenetico;

public interface AGobserver {
	void onInit(AlgoritmoGenetico ag);
	void onChange(AlgoritmoGenetico ag);
	void onError(String err);
	void onEnd(AlgoritmoGenetico ag);
}
