package src.vistas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import src.AlgoritmoGenetico;
import src.cruce.CruceMonopunto;
import src.cruce.CrucePMX;
import src.cruce.CruceUniforme;
import src.cruce.CruceAritmetico;
import src.cruce.CruceBLXa;
import src.cruce.CruceERX;
import src.seleccion.SeleccionEstocastica;
import src.seleccion.SeleccionRestos;
import src.seleccion.SeleccionRuleta;
import src.seleccion.SeleccionTorneoDet;
import src.seleccion.SeleccionTorneoProb;
import src.seleccion.SeleccionTruncamiento;
import src.utils.ConfigPanel;
import src.utils.ConfigPanel.ConfigListener;
import src.utils.ConfigPanel.DoubleOption;
import src.utils.ConfigPanel.InnerOption;
import src.utils.ConfigPanel.IntegerOption;
import src.utils.ConfigPanel.StrategyOption;

public class VistaOpciones extends JPanel{
	
	private static final Cloneable selecciones[] = {
		new SeleccionRuleta(),
		new SeleccionTorneoDet(),
		new SeleccionTorneoProb(),
		new SeleccionEstocastica(),
		new SeleccionTruncamiento(),
		new SeleccionRestos()
	};
	private static final Cloneable cruces[] = {
		new CruceMonopunto(),
		new CruceUniforme(),
		new CruceAritmetico(),
		new CruceBLXa()
	};


	public VistaOpciones(AlgoritmoGenetico ag, JLabel valido) {
		//Te creas tu config
		final ConfigPanel<AlgoritmoGenetico> cp = crearPanelConfig();
		cp.setTarget(ag); //Aqui le dices a tu config que su objetivo es el modelo
		cp.initialize(); //Inicializas los valores de tu config a los que hay en el modelo
		add(cp, BorderLayout.WEST);

		final String textoTodoValido = "Todos los campos OK";
		final String textoHayErrores = "Hay errores en algunos campos";
		valido.setText(textoTodoValido);
		// este evento se lanza cada vez que la validez cambia
		cp.addConfigListener(new ConfigListener() {
			JLabel val;
			@Override
			public void configChanged(boolean isConfigValid) {
				val.setText(isConfigValid ? textoTodoValido: textoHayErrores);
			}
			public ConfigListener init(JLabel valido) {
				this.val = valido;
				return this;
			}
		}.init(valido));
	}

	private ConfigPanel<AlgoritmoGenetico> crearPanelConfig() {

		ConfigPanel<AlgoritmoGenetico> panel = new ConfigPanel<AlgoritmoGenetico>();
		
		//El fieldName de las opciones es el nombre de la variable en el modelo
		//Yo supongo que obtiene el getter a raiz del fieldName y sabe lo que va a devolver el getter por el tipo de option
		panel.addOption(new IntegerOption<AlgoritmoGenetico>(
			"Tamaño de la población", 
			"Numero de individuos en la población", 
			"tamPoblacion",
			0, 1000))
		.addOption(new IntegerOption<AlgoritmoGenetico>(
			"Número de generaciones", 
			"Numero de generaciones que se van a ejecutar", 
			"numGeneraciones",
			0, 1000))
		.addOption(new DoubleOption<AlgoritmoGenetico>(
			"Probabilidad de cruce", 
			"Probabilidad de que dos individuos se crucen", 
			"probCruce",
			0, 1))
		.addOption(new DoubleOption<AlgoritmoGenetico>(
			"Probabilidad de mutación", 
			"Probabilidad de que un individuo mute", 
			"probMutacion",
			0, 1))
		.addOption(new DoubleOption<AlgoritmoGenetico>(
			"Porcentaje de Elitismo", 
			"Porciento de la población que se va a mantener en cada generación", 
			"elitismo",
			0, 1))
		.endOptions();
		
		return panel;
	}
}
