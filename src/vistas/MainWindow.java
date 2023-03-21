package src.vistas;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import src.AlgoritmoGenetico;

public class MainWindow extends JFrame{

    private final int INI_WIDTH = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 6/10);
    private final int INI_HEIGHT = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 7/10);
    private  AlgoritmoGenetico ag;

    public MainWindow(AlgoritmoGenetico ag) {
        super("Súper evolucionador");
        this.ag = ag;
        initGUI();
        this.setSize(new Dimension(INI_WIDTH, INI_HEIGHT));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void initGUI() {
        JPanel mainPanel = new JPanel();
        this.setContentPane(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.LINE_AXIS));
        mainPanel.add(new PanelOpciones(this.ag, INI_WIDTH * 2/10, INI_HEIGHT));
        JPanel InfoGrafica = new JPanel();
        InfoGrafica.setLayout(new BoxLayout(InfoGrafica, BoxLayout.PAGE_AXIS));
        InfoGrafica.add(new VistaGrafica(this.ag, INI_WIDTH * 8/10, INI_HEIGHT - 25));
        InfoGrafica.add(new PanelInfo(this.ag, INI_WIDTH * 8/10, 25));
        mainPanel.add(InfoGrafica);
    }
    
}
