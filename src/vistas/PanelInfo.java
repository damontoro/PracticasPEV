package src.vistas;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.AlgoritmoGenetico;
import src.patrones.AGobserver;

public class PanelInfo extends JPanel implements AGobserver {

    private static final long serialVersionUID = 1L;

    private JLabel fenotipo, fitness;

    public PanelInfo(AlgoritmoGenetico ag, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(new JLabel("Mejor fitness: "));
        this.add(fitness = new JLabel());
        this.add(Box.createRigidArea(new Dimension(20, 0)));
        this.add(new JLabel("Fenotipo: "));
        this.add(fenotipo = new JLabel());

        ag.addObserver(this);
    }

    @Override
    public void onInit(AlgoritmoGenetico ag) {
        this.fenotipo.setText("");
        this.fitness.setText("");
    }

    @Override
    public void onChange(AlgoritmoGenetico ag) {
        this.fenotipo.setText(ag.getMejorAbs().getFenotipo().toString());
        this.fitness.setText(String.valueOf(ag.getMejorAbs().getFitness()));
    }

    @Override
    public void onError(String err) {
        // TODO Auto-generated method stub
    }

}
