package simstation;

import mvc.Model;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class SimstationField extends Model {
    // keeps track of if the game is over
    private Boolean gameOver = false;
    private Boolean gameWon = false;


    // default constructor
    public SimstationField() {

    }

    // specialized constructor
    public SimstationField(int size, int percent) {

    }

    // the method that the command class will use
    // probably need to change the name
    // might need more than one method depending on command (thinking stats)
    public void move(int increaseCol, int increaseRow) throws Exception{

        changed();

    }
}
