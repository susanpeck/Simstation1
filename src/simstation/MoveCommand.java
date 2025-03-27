package simstation;

import mvc.*;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class MoveCommand extends Command {
    private int rowChange;
    private int colChange;

    public MoveCommand(Model model, int col, int row) {
        super(model);
        rowChange = row;
        colChange = col;
    }

    public void execute() throws Exception {
        if (!(model instanceof SimstationField)) {
            throw new Exception("Model must be a MineField.");
        }
        SimstationField simstationField = (SimstationField) model;

        try {
            // game has not been won
            if(simstationField.getGameWon()){
                Utilities.inform("You won! Start a new game.");
            }
            // game is not over
            else if(simstationField.getGameOver()){
                Utilities.inform("The game is over. Start a new game.");
            }
            else {
                simstationField.move(colChange, rowChange);
            }
        }
        catch (Exception e) {
            Utilities.error(e.getMessage());
        }
    }
}
