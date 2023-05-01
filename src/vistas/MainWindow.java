package src.vistas;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import src.AlgoritmoGenetico;

public class MainWindow extends JFrame{

    private final int INI_WIDTH = (int)(Toolkit.getDefaultToolkit().getScreenSize().getWidth() * 75/100);
    private final int INI_HEIGHT = (int)(Toolkit.getDefaultToolkit().getScreenSize().getHeight() * 75/100);
    private  AlgoritmoGenetico ag;

    public MainWindow(AlgoritmoGenetico ag) {
        super("Súper evolucionador");
        this.ag = ag;
        initGUI();
        this.setSize(new Dimension(INI_WIDTH, INI_HEIGHT));
        this.setMinimumSize(new Dimension(633, 480));
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
        InfoGrafica.add(new DataView(ag, INI_WIDTH * 8/10, INI_HEIGHT - 25));
        
        JScrollPane sc = new JScrollPane(new PanelInfo(this.ag, INI_WIDTH * 8/10, 20));
        sc.setPreferredSize(new Dimension(INI_WIDTH * 8/10, 45));
        sc.getHorizontalScrollBar().setUnitIncrement(16);

        InfoGrafica.add(sc);
        mainPanel.add(InfoGrafica);
    }
    
}
