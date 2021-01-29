package ProjectStarterCode.model;

public class Tile {
    /**
     * Constitutes the tiles that will be used on the board of the game.
     * It will either contain the part of a snake, food, or nothing.
     */
    private String containing;
    /**
     * Will describe what the tile contains
     */
    private int row, col;  /** row and column of the tile */

    /**
     * Constructs are tile holding nothing by default.
     *
     * @param row the row in which the tile will be located.
     * @param col the column in which the tile will be located.
     */
    public Tile(int row, int col) {
        containing = "nothing";
        this.row = row;
        this.col = col;
    }

    /**
     * Gets what the tile contains
     *
     * @return containing
     */
    public String getInsideTile() {
        return containing;
    }

    /**
     * Sets what the tile contains.
     *
     * @param setting
     */
    public void setInsideTile(String setting) {
        containing = setting;
    }

    /**
     * Returns the row where the tile is located.
     *
     * @return row
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column where the tile is located
     *
     * @return col
     */
    public int getCol() {
        return col;
    }

    /**
     * Prints the tile contains, in addition to its row and column
     *
     * @return String
     */
    @Override
    public String toString() {
        return containing + "(" + row + ", " + col + ")";
    }
}