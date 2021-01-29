package ProjectStarterCode.controller;

import ProjectStarterCode.model.Model;
import ProjectStarterCode.view.View;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;

public class Controller {
    private BlockingQueue<Message> queue;
    private View view; // Direct reference to view
    private Model model; // Direct reference to model
    private GameInfo gameInfo; // Direct reference to the state of the Game/Application

    private List<Valve> valves = new LinkedList<Valve>();

    public Controller(View view, Model model, BlockingQueue<Message> queue) {
        this.view = view;
        this.model = model;
        this.queue = queue;
        valves.add(new DoPlayGameValve());
        valves.add(new DoReturnToMenuValve());
        valves.add(new DoHitValve());
    }

    /**
     * Executes the incoming valves messages from the BlockingQueue
     */
    public void mainLoop() {
        ValveResponse response = ValveResponse.EXECUTED;
        Message message = null;
        while (response != ValveResponse.FINISH) {
            try {
                message = queue.take(); // <--- take next message from the queue
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // Look for a Valve that can process a message
            for (Valve valve : valves) {
                response = valve.execute(message);
                // if successfully processed or game over, leave the loop
                if (response != ValveResponse.MISS) {
                    break;
                }
            }
        }
    }

    private interface Valve {
        /**
         * Performs certain action in response to message
         */
        ValveResponse execute(Message message);
    }

    /**
     * Checks to see if the valve message is to start the game
     */
    private class DoPlayGameValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != PlayGameMessage.class) {
                return ValveResponse.MISS;
            }
            // otherwise it means that it is a NewGameMessage message
            // actions in Model
            // actions in View
            return ValveResponse.EXECUTED;
        }
    }

    /**
     * Checks to see if the valve message is to return to the menu
     */
    private class DoReturnToMenuValve implements  Valve{
        @Override
        public ValveResponse execute(Message message){
            if (message.getClass() != ReturnToMenuMessage.class){
                return ValveResponse.MISS;
            }
            // otherwise it means that it is a ReturnToMenu message
            // actions in Model
            // actions in View
            return ValveResponse.EXECUTED;
        }

    }

    /**
     * Checks to see if the valve message has detected the Snake colliding
     */
    private class DoHitValve implements Valve {
        @Override
        public ValveResponse execute(Message message) {
            if (message.getClass() != HitMessage.class) {
                return ValveResponse.MISS;
            }
            // otherwise message is of HitMessage type
            // actions in Model and View
            return ValveResponse.EXECUTED;
        }
    }
}