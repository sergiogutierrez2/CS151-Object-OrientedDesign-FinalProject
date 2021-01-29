package ProjectStarterCode.model;

public class Board {
    /**
     * Constitutes the Board made out of tiles
     * in which the snake will move
     * and which will contain the food
     */

    public Tile[][] tiles;

    /**
     * Constructs a board with tiles with 9 rows and 9 columns
     */
    public Board() {
        //need to set dimensions of board
        tiles = new Tile[9][9];
        //creating tiles for each tile
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                tiles[i][j] = new Tile(i, j);
            }
        }
    }

    /**
     * Prints what each of the tiles in the board contain
     */
    public void printBoard() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(tiles[i][j].getInsideTile() + "\t");
            }
            System.out.println();
        }
    }
}
