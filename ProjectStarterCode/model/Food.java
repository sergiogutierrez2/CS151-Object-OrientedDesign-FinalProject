package ProjectStarterCode.model;

public class Food {

    /**
     * Produces the food that the snake will eat, which
     * will be in one of the tiles of the board.
     */
    public Tile location;
    public Board board;

    /**
     * Produces a food object which will be placed on a tile of the board
     *
     * @param board the board in which the food will be placed
     */
    public Food(Board board) {
        this.board = board;
        this.location = null;
        spawnFood();
    }

    /**
     * Produces a food object which will be placed on a tile of the board
     *
     * @param board the board in which the food will be placed
     * @param x     is the x coordinate of where the food will be placed on the Board
     * @param y     is the y coordinate of where the food will be placed on the Board
     */
    public Food(Board board, int x, int y) {
        this.board = board;
        this.location = board.tiles[x][y];
        board.tiles[x][y].setInsideTile("food");
    }

    /**
     * Spawns food on a random tile that contains
     * nothing, if food is not on the board
     */
    public void spawnFood() {
        //needs to look at board and choose unoccupied tile
        if (!foodIsOnBoard()) {
            int x = (int) (Math.random() * board.tiles.length);
            int y = (int) (Math.random() * board.tiles.length);
            while (board.tiles[x][y].getInsideTile().compareTo("nothing") != 0) {
                x = (int) (Math.random() * board.tiles.length);
                y = (int) (Math.random() * board.tiles.length);
            }
            board.tiles[x][y].setInsideTile("food");
            location = board.tiles[x][y];
        }
    }

    /**
     * Checks whether food is on board
     *
     * @return true or false, depending if food is found or not on the board.
     */
    public boolean foodIsOnBoard() {
        //iterate through tiles of board and check if food is there
        for (int i = 0; i < board.tiles.length; i++) {
            for (int j = 0; j < board.tiles[0].length; j++) {
                if (board.tiles[i][j].getInsideTile().compareTo("food") == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns the String with the location of the food
     *
     * @return the String with the location of the food
     */
    @Override
    public String toString() {
        return location.toString();
    }
}