package simstation;

import mvc.Model;
import mvc.Utilities;

import java.io.Serializable;
import java.util.Random;

/*
Susan Peck, Evalynna Ong, Jiajun Zheng
SimStation Group 1 CS151 Spring 2025
 */

public class SimstationField extends Model {
    // keeps track of if the game is over
    private Boolean gameOver = false;
    private Boolean gameWon = false;

    // keeps track of the players location on the grid
    private int locationRow = 0;
    private int locationCol = 0;

    // grid stuff
    public final static int DEFAULT_SIZE = 20;
    public final static int DEFAULT_PERCENT = 10;
    public final int size;
    private int mineCount;
    private final Cell[][] cells;

    // creates a default minefield of size 20x20 with 5% mines
    public SimstationField() {
        this(DEFAULT_SIZE, DEFAULT_PERCENT);
    }

    public SimstationField(int size, int percent) {
        this.size = size;
        double minePercentage = 0.01 * percent;
        mineCount = (int) (size * size * minePercentage);
        cells = new Cell[size][size];
        initializeGrid();
        placeMines();
        calculateAdjacentMines();
        cells[0][0].revealState();
    }

    /**
     * a general move method that will add the value of increaseCol to the
     * increaseCol coordinate of the current position and the value of increaseRow
     * to the increaseRow coordinate of the current position
     *    the move method will check if the player has moved off the grid
     *    the move method will check if the player has stepped on a mine
     *
     * @param increaseCol an integer value to increase the column value
     * @param increaseRow an integer value to increase the row value
     */
    public void move(int increaseCol, int increaseRow) throws Exception{
        locationRow = locationRow + increaseRow; // increases the row number by increaseRow amount
        locationCol = locationCol + increaseCol; // increase the column number by increaseCol amount

        //need to check if off grid
        if(locationCol > size - 1 || locationCol < 0 || locationRow > size - 1 || locationRow < 0){
            // return to previous location
            locationRow = locationRow - increaseRow;
            locationCol = locationCol - increaseCol;
            throw new Exception("Can't move off the grid, try a new direction");
        }

        //update new cell to show adjacent mines
        cells[locationRow][locationCol].revealState();

        changed();

        //need to check if hits a mine
        if(cells[locationRow][locationCol].getHasMine()){
            gameOver = true;
            Utilities.inform("Game over!");
        }

        // check to see if won!
        if(locationCol == size - 1 && locationRow == size - 1){
            gameWon = true;
            // locationRow = locationRow + increaseRow;
            // locationCol = locationCol + increaseCol;
            throw new Exception("Congrats You won!");
        }
    }

    public boolean getGameOver(){
        return gameOver;
    }
    public boolean getGameWon(){
        return gameWon;
    }

    public int getRow() {
        return locationRow;
    }

    public int getCol() {
        return locationCol;
    }

    public String getState(int row, int col) {
        return cells[row][col].getState();
    }

    private void initializeGrid() {
        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                cells[r][c] = new Cell();
            }
        }
    }

    private void placeMines() {
        Random rand = new Random();
        int placed = 0;
        int maxMineCount = size * size - 2;
        mineCount = Math.min(maxMineCount, mineCount);
        while (placed < mineCount) {
            int r = rand.nextInt(size);
            int c = rand.nextInt(size);
            if (!(c == 0 && r == 0) && !(c == size - 1 && r == size - 1) && !cells[r][c].hasMine) {
                cells[r][c].setHasMine(true);
                placed++;
            }
        }
    }

    private void calculateAdjacentMines() {
        int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
        int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

        for (int r = 0; r < size; r++) {
            for (int c = 0; c < size; c++) {
                if (cells[r][c].hasMine)
                    continue;

                int count = 0;
                for (int i = 0; i < 8; i++) {
                    int nr = r + dx[i], nc = c + dy[i];
                    if (nr >= 0 && nr < size && nc >= 0 && nc < size && cells[nr][nc].hasMine) {
                        count++;
                    }
                }
                cells[r][c].setAdjacentMines(count);
            }
        }
    }

    /**
     * Helps keep track of the state and mine info of each cell.
     * "?" - unexplored cell.
     * "M" - revealed to be a mine.
     * int - number of adjacent mines
     */
    private class Cell implements Serializable {
        private boolean hasMine;
        private int adjacentMines;
        private String state;

        public Cell() {
            this(false, 0);
        }

        public Cell(boolean hasMine, int adjacentMines) {
            this.hasMine = hasMine;
            this.adjacentMines = adjacentMines;
            state = "?";
        }

        public boolean getHasMine(){
            return hasMine;
        }

        public void setHasMine(boolean hasMine){
            this.hasMine = hasMine;
        }

        public int getAdjacentMines(){
            return adjacentMines;
        }

        public void setAdjacentMines(int adjacentMines){
            this.adjacentMines = adjacentMines;
        }

        public String getState(){
            return state;
        }

        public void revealState(){
            if (hasMine){
                state = "M";
            }
            else {
                state = Integer.toString(adjacentMines);
            }
        }
    }
}
