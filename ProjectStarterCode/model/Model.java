package ProjectStarterCode.model;

import ProjectStarterCode.view.View;

public class Model {
    View view;
    public Board board;
    public Snake snake;
    public Food food;
    public Collision collision;

    public Model() {
        board = new Board();
        snake = new Snake(board);
        food = new Food(board);
        collision = new Collision(board, snake);
    }

    public void attach(View view) {
        this.view = view;
    }

    //resets the board, snake, food and collision
    public void reset() {
        board = new Board();
        snake = new Snake(board);
        food = new Food(board);
        collision = new Collision(board, snake);
    }

    //moves the snake, spawns food if necessary and tells view to update itself
    public void updateModel() {
        //snake movement is calculated
        collision.snakeCollision();
        //spawns food if there isn't one
        food.spawnFood();
        //updates view with new board of tiles
        view.updateGrid(board.tiles);
    }
}