package ProjectStarterCode.model;

import java.util.LinkedList;

public class Snake {
    /**
     * Represents the snake that will be moving
     * throughout the board.
     */
    private boolean alive;
    /**
     * it is used to check if the Snake is alive
     */
    private int size;
    /**
     * it is used to check the length of the Snake
     */
    public Direction direction;
    /**
     * This is the direction taken by the Snake
     */
    public LinkedList<Tile> location;

    /**
     * it is used to keep track of the Snake's location
     */

    // Directions will be up, down, left and right
    enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    /**
     * Creates a Snake the length of three tiles.
     * It'll go down by default.
     *
     * @param board the board in which the Snake will be placed
     */
    public Snake(Board board) {
        alive = true;
        direction = Direction.DOWN;
        location = new LinkedList<Tile>();
        location.add(board.tiles[4][4]);
        board.tiles[4][4].setInsideTile("snake");
        location.add(board.tiles[3][4]);
        board.tiles[3][4].setInsideTile("snake");
        location.add(board.tiles[2][4]);
        board.tiles[2][4].setInsideTile("snake");
        size = location.size();
    }

    /**
     * Will make the Snake be dead by changing the status of alive
     *
     * @return alive
     */
    public boolean toggleAlive() {
        alive = !alive;
        return alive;
    }

    /**
     * Gets the status on whether the snake is alive or not
     *
     * @return alive
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * Gets the size of the Snake
     *
     * @return size of the Snake
     */
    public int getSize() {
        return size;
    }

    /**
     * Checks if the size of the Snake has exceeded the length of 40 tiles
     * which is the win condition for the game
     *
     * @return whether size is greater than 40
     */
    public boolean checkWinCondition() {
        return size > 40;
    }

    /**
     * Updates the size of the snake
     */
    public void updateSize() {
        size = location.size();
    }

    /**
     * Makes the Snake go left
     */
    public void goLeft() {
        if (!direction.equals(Direction.RIGHT)) {
            direction = Direction.LEFT;
        }
    }

    /**
     * Makes the Snake go Right
     */
    public void goRight() {
        if (!direction.equals(Direction.LEFT)) {
            direction = Direction.RIGHT;
        }
    }

    /**
     * Makes the Snake go Up
     */
    public void goUp() {
        if (!direction.equals(Direction.DOWN)) {
            direction = Direction.UP;
        }
    }

    /**
     * Makes the Snake go Down
     */
    public void goDown() {
        if (!direction.equals(Direction.UP)) {
            direction = Direction.DOWN;
        }
    }
}