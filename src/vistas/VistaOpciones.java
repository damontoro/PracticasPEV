package src.vistas;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;

import src.AlgoritmoGenetico;
import src.cruce.CruceMonopunto;
import src.seleccion.SeleccionRuleta;
import src.mutacion.MutacionBinaria;
import src.problema.Problema;
import src.problema.Problema1;
import src.problema.Problema2;
import src.problema.Problema3;
import src.problema.Problema4A;
import src.problema.Problema4B;
import src.utils.ConfigPanel;
import src.utils.ConfigPanel.ConfigListener;
import src.utils.ConfigPanel.DoubleOption;
import src.utils.ConfigPanel.InnerOption;
import src.utils.ConfigPanel.IntegerOption;
import src.utils.ConfigPanel.StrategyOption;
import src.utils.MyInteger;

public class VistaOpciones extends JPanel{
	
	private static final Cloneable selecciones[] = {
		new SeleccionRuleta()
	};
	private static final Cloneable cruces[] = {
		new CruceMonopunto()
	};
	private static final Cloneable mutaciones[] = {
		new MutacionBinaria()
	};
	private static final Cloneable problemas[] = {
		new Problema1(),
		new Problema2(),
		new Problema3(),
		new Problema4A(2),
		new Problema4B(2)
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
				//ag.stop();
				//ag.reset();
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
		.addOption(new DoubleOption<AlgoritmoGenetico>(
			"Porcentaje de Elitismo", 
			"Porciento de la población que se va a mantener en cada generación", 
			"elitismo",
			0, 1))
		.addOption(new StrategyOption<AlgoritmoGenetico>(
			"Selección", 
			"Algoritmo de selección", 
			"seleccion",
			selecciones))
		.addOption(new StrategyOption<AlgoritmoGenetico>(
			"Cruce", 
			"Algoritmo de Cruce", 
			"cruce",
			cruces))
		.addOption(new StrategyOption<AlgoritmoGenetico>(
			"Mutacion", 
			"Algoritmo de Mutacion", 
			"mutacion",
			mutaciones))
		.addOption(new StrategyOption<AlgoritmoGenetico>(
			"Problema", 
			"Problema a resolver", 
			"problema",
			problemas))
		.beginInner(new InnerOption<AlgoritmoGenetico, Problema>( 
			"Maximización/Minimicación", "Como quieres optimizar la funcion", "problema", Problema.class))
				.addInner(new StrategyOption<MyInteger>(
					"Optimización", 
					"Optimización del problema",
					"maxmin", 
					new MyInteger[] {new MyInteger(1), new MyInteger(-1)}))
				.endInner()
		.beginInner(new InnerOption<AlgoritmoGenetico, Problema>( 
		"Opciones", "Opciones del problema", "problema", Problema4A.class))
			.addInner(new IntegerOption<Problema>(
				"Dimension", "Dimension del problema", "dimension", 2, Integer.MAX_VALUE))
			.endInner()
		.endOptions();
		


		return panel;
	}
}
