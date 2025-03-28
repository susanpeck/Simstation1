package simstation;

import mvc.*;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class MoveCommand extends Command {
    // this is code copied from MineField
    // needs to be changed to logic for SimStation

    private int rowChange;
    private int colChange;

    public MoveCommand(Model model, int col, int row) {
        super(model);
        rowChange = row;
        colChange = col;
    }

    public void execute() throws Exception {
        if (!(model instanceof World)) {
            throw new Exception("Model must be a Simstation.");
        }
        World simstationField = (World) model;

        try {
            // game has not been won
            if(1 > 0){
                //something
            }
            // game is not over
            else if( 2 < 5){
                // something
            }
            else {
                // call the method in the model
                //simstationField.move(colChange, rowChange);
            }
        }

        // catch and throw exceptions to the AppPanel ?
        catch (Exception e) {
            Utilities.error(e.getMessage());
        }
    }
}
