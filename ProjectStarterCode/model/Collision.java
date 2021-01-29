package ProjectStarterCode.model;

public class Collision {
    /**
     * Constitutes the Collision of the snake
     * with itself or food
     */
    public Snake snake;
    public Board board;

    /**
     * Creates a collision object with
     * a board and snake objects
     *
     * @param board
     * @param snake
     */
    public Collision(Board board, Snake snake) {
        this.board = board;
        this.snake = snake;
    }

    /**
     * Updates the movement of the snake, which
     * can go up, down, left or right
     */
    public void snakeCollision() {
        //stuff for checking collision
        Tile nextTile = null;
        if (snake.isAlive()) {
            switch (snake.direction) {
                case UP:
                    try {
                        nextTile = board.tiles[snake.location.getFirst().getRow() - 1][snake.location.getFirst().getCol()];
                        nextTileReaction(nextTile);
                    } catch (IndexOutOfBoundsException e) {
                        snake.toggleAlive();
                    }
                    break;
                case DOWN:
                    try {
                        nextTile = board.tiles[snake.location.getFirst().getRow() + 1][snake.location.getFirst().getCol()];
                        nextTileReaction(nextTile);
                    } catch (IndexOutOfBoundsException e) {
                        snake.toggleAlive();
                    }
                    break;
                case LEFT:
                    try {
                        nextTile = board.tiles[snake.location.getFirst().getRow()][snake.location.getFirst().getCol() - 1];
                        nextTileReaction(nextTile);
                    } catch (IndexOutOfBoundsException e) {
                        snake.toggleAlive();
                    }
                    break;
                case RIGHT:
                    try {
                        nextTile = board.tiles[snake.location.getFirst().getRow()][snake.location.getFirst().getCol() + 1];
                        nextTileReaction(nextTile);
                    } catch (IndexOutOfBoundsException e) {
                        snake.toggleAlive();
                    }
            }
        }
    }

    /**
     * Checks what is inside the next tile,
     * and depending on what is found
     * either does nothing, makes the snake grow, or marks snake as dead
     */
    private void nextTileReaction(Tile nextTile) {
        if (nextTile.getInsideTile().equals("snake")) {
            snake.toggleAlive();
        } else if (nextTile.getInsideTile().equals("food")) {
            nextTile.setInsideTile("snake");
            snake.location.addFirst(nextTile);
            snake.updateSize();
        } else if (nextTile.getInsideTile().equals("nothing")) {
            nextTile.setInsideTile("snake");
            snake.location.addFirst(nextTile);
            snake.location.removeLast().setInsideTile("nothing");
        }
    }
}