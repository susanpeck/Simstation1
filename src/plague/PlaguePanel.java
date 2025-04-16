package plague;

import mvc.AppFactory;
import mvc.Model;
import simstation.WorldPanel;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */


// there is a bug where pressing new doesn't update sliders

public class PlaguePanel extends WorldPanel {
    private JLabel infectedLabel;
    private JLabel probabilityLabel;
    private JLabel populationLabel;
    private JLabel fatalityLabel;

    private JSlider initialInfectedSlider;
    private JSlider infectionProbabilitySlider;
    private JSlider initialPopulationSlider;
    private JSlider fatalityTimeSlider;

    private JButton notFatalButton;
    private JPanel sliderPanel;
    private PlagueSim p;

    public PlaguePanel(AppFactory factory) {
        super(factory);

        sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));
        sliderPanel.setBackground(Color.PINK);

        if (!(model instanceof PlagueSim)) {
            throw new IllegalArgumentException("Model must be a PlagueSim.");
        }

        p = (PlagueSim) model;

        // Initial % Infected slider
        infectedLabel = new JLabel("Initial % Infected:");
        initialInfectedSlider = new JSlider(0, 100, p.getInfectedPercent());
        initialInfectedSlider.setMajorTickSpacing(10);
        initialInfectedSlider.setMinorTickSpacing(2);
        initialInfectedSlider.setPaintTicks(true);
        initialInfectedSlider.setPaintLabels(true);
        initialInfectedSlider.setOpaque(true);
        initialInfectedSlider.addChangeListener(e -> {
            if (!initialInfectedSlider.getValueIsAdjusting()) {
                p.setInfectedPercent(initialInfectedSlider.getValue());
            }
        });

        // Infection Probability slider
        probabilityLabel = new JLabel("Infection Probability:");
        infectionProbabilitySlider = new JSlider(0, 100, p.getVIRULENCE());
        infectionProbabilitySlider.setMajorTickSpacing(10);
        infectionProbabilitySlider.setMinorTickSpacing(2);
        infectionProbabilitySlider.setPaintTicks(true);
        infectionProbabilitySlider.setPaintLabels(true);
        infectionProbabilitySlider.setOpaque(true);
        infectionProbabilitySlider.addChangeListener(e -> {
            if (!infectionProbabilitySlider.getValueIsAdjusting()) {
                p.setVIRULENCE(infectionProbabilitySlider.getValue());
            }
        });

        // Initial Population Size slider
        populationLabel = new JLabel("Initial Population Size:");
        initialPopulationSlider = new JSlider(0, 200, p.getPopulation());
        initialPopulationSlider.setMajorTickSpacing(20);
        initialPopulationSlider.setMinorTickSpacing(2);
        initialPopulationSlider.setPaintTicks(true);
        initialPopulationSlider.setPaintLabels(true);
        initialPopulationSlider.setOpaque(true);
        initialPopulationSlider.addChangeListener(e -> {
            if (!initialPopulationSlider.getValueIsAdjusting()) {
                p.setPopulation(initialPopulationSlider.getValue());
            }
        });

        // Fatality/Recovery Time slider
        fatalityLabel = new JLabel("Fatality/Recovery Time:");
        fatalityTimeSlider = new JSlider(0, 500, p.getRecoveryORFatalityTime());
        fatalityTimeSlider.setMajorTickSpacing(50);
        fatalityTimeSlider.setMinorTickSpacing(5);
        fatalityTimeSlider.setPaintTicks(true);
        fatalityTimeSlider.setPaintLabels(true);
        fatalityTimeSlider.setOpaque(true);
        fatalityTimeSlider.addChangeListener(e -> {
            if (!fatalityTimeSlider.getValueIsAdjusting()) {
                p.setRecoveryORFatalityTime(fatalityTimeSlider.getValue());
            }
        });

        // Not Fatal button
        notFatalButton = new JButton("Not Fatal");
        notFatalButton.addActionListener(this);

        // set labels and button alignments to center
        infectedLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        probabilityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        populationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        fatalityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        notFatalButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add them all to the slider panel
        sliderPanel.add(infectedLabel);
        sliderPanel.add(initialInfectedSlider);
        sliderPanel.add(Box.createVerticalStrut(25)); // adds spacing between sections

        sliderPanel.add(probabilityLabel);
        sliderPanel.add(infectionProbabilitySlider);
        sliderPanel.add(Box.createVerticalStrut(25));

        sliderPanel.add(populationLabel);
        sliderPanel.add(initialPopulationSlider);
        sliderPanel.add(Box.createVerticalStrut(25));

        sliderPanel.add(fatalityLabel);
        sliderPanel.add(fatalityTimeSlider);
        sliderPanel.add(Box.createVerticalStrut(25));

        sliderPanel.add(notFatalButton);

        controlPanel.add(sliderPanel);
    }

    public void update() {
        initialInfectedSlider.setValue(p.getInfectedPercent());
        infectionProbabilitySlider.setValue(p.getVIRULENCE());
        initialPopulationSlider.setValue(p.getPopulation());
        fatalityTimeSlider.setValue(p.getRecoveryORFatalityTime());
        repaint();
    }

    @Override
    public void setModel(Model newModel) {
        super.setModel(newModel);
        if (!(newModel instanceof PlagueSim)) {
            throw new IllegalArgumentException("Model must be a PlagueSim.");
        }
        p = (PlagueSim) newModel;
        initialInfectedSlider.setValue(p.getInfectedPercent());
        infectionProbabilitySlider.setValue(p.getVIRULENCE());
        initialPopulationSlider.setValue(p.getPopulation());
        fatalityTimeSlider.setValue(p.getRecoveryORFatalityTime());
    }

    public static void main(String[] args) {
        PlaguePanel panel = new PlaguePanel(new PlagueFactory());
        panel.display();
    }
}
