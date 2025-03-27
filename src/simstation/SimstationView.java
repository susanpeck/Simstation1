package simstation;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

import mvc.Model;
import mvc.View;

import javax.swing.*;
import java.awt.*;

public class SimstationView extends View{
    private final SimstationField mf;
    private final JLabel[][] grid;
    private final int size;

    public SimstationView(Model m){
        super(m);
        if (model == null || !(model instanceof SimstationField)) {
            throw new IllegalStateException("Model must be a MineField");
        }
        this.mf = (SimstationField) model;
        size = mf.size;
        this.setBackground(Color.GRAY);
        setLayout(new GridLayout(size, size));
        grid = new JLabel[size][size];
        initView();
    }

    private void initView() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                String state = mf.getState(r, c);
                JLabel cell = new JLabel(state, SwingConstants.CENTER);
                cell.setFont(new Font("Arial", Font.BOLD, 16));
                cell.setForeground(Color.DARK_GRAY);
                setBorderColor(cell, state);
                grid[r][c] = cell;
                this.add(cell);
            }
        }
        grid[mf.getRow()][mf.getCol()].setBorder(BorderFactory.createLineBorder(Color.BLUE));
        grid[size - 1][size - 1].setBorder(BorderFactory.createLineBorder(Color.GREEN));
    }

    @Override
    public void update(){
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                String state = mf.getState(r, c);
                JLabel cell = grid[r][c];
                cell.setText(state);

                // set the appropriate boarder color
                setBorderColor(cell, state);
            }
        }
        grid[mf.getRow()][mf.getCol()].setBorder(BorderFactory.createLineBorder(Color.BLUE));
        grid[size - 1][size - 1].setBorder(BorderFactory.createLineBorder(Color.GREEN));
        repaint();
    }

    private void setBorderColor(JLabel cell, String state){
        if (state.equals("?")) {
            cell.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        }
        else if (state.equals("M")) {
            cell.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            cell.setForeground(Color.RED);
        }
        else if (state.matches("[0-9]+")) {
            cell.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        }
    }

    public void setModel(Model newModel) {
        super.setModel(newModel);
        initView();
        repaint();
    }
}
