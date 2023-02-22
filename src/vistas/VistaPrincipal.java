package src.vistas;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import src.AlgoritmoGenetico;
import src.seleccion.ISeleccion;
import src.seleccion.SeleccionRuleta;
import src.utils.ConfigPanel;
import src.utils.ConfigPanel.ChoiceOption;
import src.utils.ConfigPanel.ConfigListener;
import src.utils.ConfigPanel.DoubleOption;
import src.utils.ConfigPanel.InnerOption;
import src.utils.ConfigPanel.IntegerOption;
import src.utils.ConfigPanel.StrategyOption;

public class VistaPrincipal extends JFrame{
	
	private static final Cloneable selecciones[] = {
		new SeleccionRuleta()
	};

	public VistaPrincipal(AlgoritmoGenetico ag){
		setTitle("Algoritmo Genético");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		setLayout(new BorderLayout());

		final ConfigPanel<AlgoritmoGenetico> cp = crearPanelConfig();
		cp.setTarget(ag); //Aqui le dices a tu config que su objetivo es el modelo
		cp.initialize(); //Inicializas los valores de tu config a los que hay en el modelo
		add(cp, BorderLayout.WEST);

		final String textoTodoValido = "Todos los campos OK";
		final String textoHayErrores = "Hay errores en algunos campos";
		final JLabel valido = new JLabel(textoHayErrores);
		// este evento se lanza cada vez que la validez cambia
		cp.addConfigListener(new ConfigListener() {
			@Override
			public void configChanged(boolean isConfigValid) {
				valido.setText(isConfigValid ? textoTodoValido: textoHayErrores);				
			}
		});
		add(valido, BorderLayout.SOUTH);
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
		.addOption( new DoubleOption<AlgoritmoGenetico>(
			"Precision", 
			"Numero de decimales que se van a tener en cuenta", 
			"precision",
			0, 1))
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
		.addOption(new StrategyOption<AlgoritmoGenetico>(
			"Selección", 
			"Algoritmo de selección", 
			"seleccion",
			selecciones))
		.beginInner(new InnerOption<AlgoritmoGenetico, AlgoritmoGenetico>(  
			"Probabilidad Selección",							 // titulo del sub-panel
			"opciones del circulo",				 // tooltip asociado
			"seleccion",							 // campo
			ISeleccion.class))						 // tipo que debe de tener ese campo para que se active el sub-panel
				.addInner(new DoubleOption<AlgoritmoGenetico>(
					"% Cruce", "Probabilidad de cruce", "probCruce", 0, 1))
				.endInner()				
		
		.endOptions();
		


		return panel;
	}
	
}
