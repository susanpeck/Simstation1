package simstation.plague;

import mvc.*;
import simstation.*;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class PlaugePanel extends WorldPanel implements ChangeListener, ActionListener {
    private JLabel infectedLabel;
    private JLabel probabilityLabel;
    private JLabel populationLabel;
    private JLabel fatalityLabel;


    private JSlider initialInfectedSlider;
    private JSlider infectionProbabilitySlider;
    private JSlider initialPopulationSlider;
    private JSlider fatalityTimeSlider;

    // no clue what this button does
    private JButton notFatalButton;

    public PlaugePanel(AppFactory factory) {
        super(factory);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
        sliderPanel.setBackground(Color.PINK);

        createComponents();
        addComponents(sliderPanel);

        controlPanel.add(sliderPanel);
    }

    public static void main(String[] args) {
        AppPanel panel = new PlaugePanel(new PlagueFactory());
        panel.display();
    }

    @Override
    public void stateChanged(ChangeEvent e) {

    }

    private void createComponents() {
        // Initial % Infected slider
        infectedLabel = new JLabel("Initial % Infected:");
        initialInfectedSlider = new JSlider(0, 100, 5);
        initialInfectedSlider.setMajorTickSpacing(10);
        initialInfectedSlider.setMinorTickSpacing(2);
        initialInfectedSlider.setPaintTicks(true);
        initialInfectedSlider.setPaintLabels(true);
        initialInfectedSlider.setOpaque(true);
        //initialInfectedSlider.addChangeListener();

        // Infection Probability slider
        probabilityLabel = new JLabel("Infection Probability:");
        infectionProbabilitySlider = new JSlider(0, 100, PlagueSim.VIRULENCE);
        infectionProbabilitySlider.setMajorTickSpacing(10);
        infectionProbabilitySlider.setMinorTickSpacing(2);
        infectionProbabilitySlider.setPaintTicks(true);
        infectionProbabilitySlider.setPaintLabels(true);
        infectionProbabilitySlider.setOpaque(true);

        // Initial Population Size slider
        populationLabel = new JLabel("Initial Population Size:");
        initialPopulationSlider = new JSlider(0, 200, PlagueSim.POPULATION);
        initialPopulationSlider.setMajorTickSpacing(20);
        initialPopulationSlider.setMinorTickSpacing(2);
        initialPopulationSlider.setPaintTicks(true);
        initialPopulationSlider.setPaintLabels(true);
        initialPopulationSlider.setOpaque(true);

        // Fatality/Recovery Time slider
        fatalityLabel = new JLabel("Fatality/Recovery Time:");
        fatalityTimeSlider = new JSlider(0, 500, PlagueSim.TIME);
        fatalityTimeSlider.setMajorTickSpacing(50);
        fatalityTimeSlider.setMinorTickSpacing(5);
        fatalityTimeSlider.setPaintTicks(true);
        fatalityTimeSlider.setPaintLabels(true);
        fatalityTimeSlider.setOpaque(true);

        // Not Fatal button
        notFatalButton = new JButton("Not Fatal");
        notFatalButton.addActionListener(this);

        // set labels and button alignments to center
        infectedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        probabilityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        populationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fatalityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        notFatalButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    private void addComponents(JPanel panel) {
        // Add them all to the slider panel
        panel.add(infectedLabel);
        panel.add(initialInfectedSlider);
        panel.add(Box.createVerticalStrut(25)); // adds spacing between sections

        panel.add(probabilityLabel);
        panel.add(infectionProbabilitySlider);
        panel.add(Box.createVerticalStrut(25));

        panel.add(populationLabel);
        panel.add(initialPopulationSlider);
        panel.add(Box.createVerticalStrut(25));

        panel.add(fatalityLabel);
        panel.add(fatalityTimeSlider);
        panel.add(Box.createVerticalStrut(25));

        panel.add(notFatalButton);
    }
}
