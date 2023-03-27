package src.vistas;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.Box;
import javax.swing.JLabel;
import javax.swing.JPanel;

import src.AlgoritmoGenetico;
import src.individuo.Individuo;
import src.patrones.AGobserver;

public class PanelInfo extends JPanel implements AGobserver {

    private static final long serialVersionUID = 1L;

    private JLabel fenotipo, fitness, ejecucion;
    private Individuo mejorAbs;

    public PanelInfo(AlgoritmoGenetico ag, int width, int height) {
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.add(new JLabel("Mejor fitness: "));
        this.add(fitness = new JLabel());
        this.add(Box.createRigidArea(new Dimension(20, 0)));
        this.add(new JLabel("Fenotipo: "));
        this.add(fenotipo = new JLabel());
        this.add(Box.createRigidArea(new Dimension(20, 0)));
        this.add(ejecucion = new JLabel());
        ejecucion.setVisible(false);

        ag.addObserver(this);
    }

    @Override
    public void onInit(AlgoritmoGenetico ag) {
        this.fenotipo.setText("");
        this.fitness.setText("");
        mejorAbs = null;
        this.ejecucion.setVisible(ag.getIntervalos());
    }

    @Override
    public void onChange(AlgoritmoGenetico ag) {
        if(mejorAbs == null || (ag.getProblema().compare(ag.getMejorAbs(), mejorAbs) < 0)){
            mejorAbs = ag.getMejorAbs();
            this.fenotipo.setText(mejorAbs.getFenotipo().toString());
            this.fitness.setText(String.valueOf(mejorAbs.getFitness()));
        }
        if(ag.getIntervalos()){
            this.ejecucion.setText(ag.getTituloEjeX() + ": " + ag.getEjecucionActual());
        }
    }

    @Override
    public void onError(String err) {
    }

    @Override
    public void onEnd(AlgoritmoGenetico ag) {
    }

}
