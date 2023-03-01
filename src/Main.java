package src;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import src.vistas.VistaPrincipal;

public class Main{

	public static void main(String[] args) {
		//Te creas tu modelo
		AlgoritmoGenetico ag = new AlgoritmoGenetico(100, 100, 0.7, 0.01);
		//Se lo pasas a la vista
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeLater(() -> new VistaPrincipal(ag));
			ag.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}