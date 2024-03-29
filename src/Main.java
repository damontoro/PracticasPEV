package src;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import src.vistas.MainWindow;

public class Main{

	public static void main(String[] args) {
		//Te creas tu modelo
		AlgoritmoGenetico ag = new AlgoritmoGenetico();
		//Se lo pasas a la vista
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            SwingUtilities.invokeLater(() -> new MainWindow(ag));
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

}