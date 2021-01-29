import ProjectStarterCode.controller.Controller;
import ProjectStarterCode.controller.Message;
import ProjectStarterCode.model.Model;
import ProjectStarterCode.view.View;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Game {
    /**
     * Builds a game, with a Board where the user controls a snake (up, down, left, right),
     * which has the objective of consuming food until it exceeds the size of
     * 40 tiles.
     */

    private static BlockingQueue<Message> queue = new LinkedBlockingQueue<>();
    private static View view; // Direct reference to view
    private static Model model; // Direct reference to model

    public static void main(String[] args) {

        model = new Model();
        view = View.init(queue, model);
        model.attach(view);
        Controller game = new Controller(view, model, queue);
        game.mainLoop();
        view.dispose();
        queue.clear();

    }

}
