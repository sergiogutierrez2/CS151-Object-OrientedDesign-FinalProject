package ProjectStarterCode.view;

import ProjectStarterCode.controller.Message;
import ProjectStarterCode.controller.PlayGameMessage;
import ProjectStarterCode.controller.ReturnToMenuMessage;
import ProjectStarterCode.model.Model;
import ProjectStarterCode.model.Tile;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.BlockingQueue;


public class View {
    private JFrame mainFrame;
    private BlockingQueue<Message> queue;
    private Model model;
    JLabel[][] grid;

    /**
     *
     * @param queue passages messages between the view and controller
     * @param model contains the program logic
     * @return connects the view with the queue and model
     */
    public static View init(BlockingQueue<Message> queue, Model model) {
        // Create object of type view
        return new View(queue, model);
    }

    /**
     *
     * @param queue passages messages between the view and controller
     * @param model contains the program logic
     */
    private View(BlockingQueue<Message> queue, Model model) {
        this.queue = queue;
        this.model = model;
        // TODO:
        // you should initalize JFrame and show it,
        // JFrame should be able to add Messages to queue
        // JFrame can be in a separate class or created JFrame with all the elements in this class
        // or you can make View a subclass of JFrame by extending it

        GameViews();
    }

    /**
     * Method to draw all the visual aspects of the game
     */
    public void GameViews() {
        mainFrame = new JFrame("Snake");

        int row = 9;
        int col = 9;

        /*
        ----------------------------------------------------------------------------------------------------------------------------------------------------------------
            JPanels, JButtons, JTextFields, ...
         ----------------------------------------------------------------------------------------------------------------------------------------------------------------
         */

        JPanel panelContent = new JPanel();     //Parent Panel
        JPanel menuPanel = new JPanel();        //Child Panel
        JPanel gamePanel = new JPanel();        //Child Panel
        JPanel deathPanel = new JPanel();        //Child Panel
        JPanel winPanel = new JPanel();        //Child Panel

        //Components used in more than one Child Panel

        //Menu Panel Components
        JPanel logoPanel = new JPanel();
        JPanel playButtonPanel = new JPanel();
        JPanel gameDescPanel = new JPanel();
        JPanel winConPanel = new JPanel();
        JButton playButton = new JButton("Play");
        JLabel logo = new JLabel("Snake");
        JLabel gameDesc = new JLabel("Eat Food to Grow in Size");
        JLabel winCon = new JLabel("*** Win condition: When Snake's length reaches half the size of the board size ***");

        //Game Panel Components
        JPanel snakeLenPanel = new JPanel();
        JPanel fieldPanel = new JPanel();
        JPanel directionPanel = new JPanel();
        JPanel left = new JPanel();
        JPanel right = new JPanel();
        JLabel snakeLen = new JLabel(" Snake Length: " + model.snake.getSize());

        //Death Panel Components
        JPanel top = new JPanel();
        JPanel interior = new JPanel();
        JPanel deathLeft = new JPanel();
        JPanel deathRight = new JPanel();
        JPanel deathBot = new JPanel();
        JLabel deathMes = new JLabel(" Unfortunately, Slither has died.");
        JButton playAgainButton = new JButton("Play Again");
        JButton menuButton = new JButton("Menu");

        //Win Panel Components
        JPanel winTop = new JPanel();
        JPanel winInterior = new JPanel();
        JPanel winLeft = new JPanel();
        JPanel winRight = new JPanel();
        JPanel winBot = new JPanel();
        JLabel winMes = new JLabel(" Congratulations, you fed Slither a lot! ");
        JButton winPlayAgainButtonButton = new JButton("Play Again");
        JButton winMenuButtonButton = new JButton("Menu");

        /*
        ----------------------------------------------------------------------------------------------------------------------------------------------------------------
            layouts
         ----------------------------------------------------------------------------------------------------------------------------------------------------------------
         */

        CardLayout cl = new CardLayout();
        panelContent.setLayout(cl);

        /*Menu Layouts**/
        menuPanel.setLayout(new GridLayout(4, 1));

        /*Game layouts**/
        gamePanel.setLayout(new BorderLayout());
        directionPanel.setLayout(new GridLayout(3, 3));
        fieldPanel.setLayout(new GridLayout(row, col, 3, 3));  //create a gridlayout 9x9 with vertical and horizontal gaps of 3
        fieldPanel.setBackground(Color.WHITE);  //set color of background to white to increase contrast

        /*Death Layout**/
        deathPanel.setLayout(new BorderLayout());
        interior.setLayout(new GridLayout(1, 2, 200, 0));
        top.setLayout(new GridBagLayout());

        /*Win Panel**/
        winPanel.setLayout(new BorderLayout());
        winInterior.setLayout(new GridLayout(1, 2, 200, 0));
        winTop.setLayout(new GridBagLayout());

         /*
        ----------------------------------------------------------------------------------------------------------------------------------------------------------------
            Panel Logic
         ----------------------------------------------------------------------------------------------------------------------------------------------------------------
         */

        /*Menu Panel Logic**/
        //logo and logoPanel properties
        logo.setForeground(Color.GREEN);
        logo.setFont(logo.getFont().deriveFont(200.0f));
        logoPanel.setBackground(Color.BLACK);
        logoPanel.add(logo);

        //playButton and playButtonPanel properties
        playButton.setForeground(Color.GREEN);
        playButton.setBackground(Color.BLACK);
        playButton.setFont(playButton.getFont().deriveFont(140.0f));
        playButtonPanel.setBackground(Color.BLACK);
        playButton.setBorder(new LineBorder(Color.WHITE, 5));
        playButtonPanel.add(playButton);

        //gameDesc and gameDecsPanel properties
        gameDesc.setForeground(Color.WHITE);
        gameDesc.setFont(gameDesc.getFont().deriveFont(20.0f));
        gameDescPanel.setBackground(Color.BLACK);
        gameDescPanel.add(gameDesc);

        //winCon and winConPanel properties
        winCon.setForeground(Color.WHITE);
        winCon.setFont(gameDesc.getFont().deriveFont(20.0f));
        winConPanel.setBackground(Color.BLACK);
        winConPanel.add(winCon);

        //add everything to frame
        menuPanel.add(logoPanel);
        menuPanel.add(playButtonPanel);
        menuPanel.add(gameDescPanel);
        menuPanel.add(winConPanel);


        /*Game Panel logic**/
        grid = new JLabel[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = new JLabel();
                grid[i][j].setBackground(Color.GRAY);
                grid[i][j].setOpaque(true);
                fieldPanel.add(grid[i][j]);
            }
        }

        //snakeLen properties
        snakeLen.setForeground(Color.WHITE);
        snakeLen.setFont(snakeLen.getFont().deriveFont(20.0f));
        snakeLenPanel.add(snakeLen);
        snakeLenPanel.setBackground(Color.BLACK);

        //remaining panel properties
        directionPanel.setBackground(Color.BLACK);
        right.setBackground(Color.BLACK);
        left.setBackground(Color.BLACK);

        //directionPanel buttons
        JButton upButton = new JButton("Up");
        upButton.setForeground(Color.GREEN);
        upButton.setBackground(Color.BLACK);
        upButton.setFont(upButton.getFont().deriveFont(50.0f));
        upButton.setBorder(new LineBorder(Color.WHITE, 5));
        JButton leftButton = new JButton("Left");
        leftButton.setForeground(Color.GREEN);
        leftButton.setBackground(Color.BLACK);
        leftButton.setFont(leftButton.getFont().deriveFont(50.0f));
        leftButton.setBorder(new LineBorder(Color.WHITE, 5));
        JButton rightButton = new JButton("Right");
        rightButton.setForeground(Color.GREEN);
        rightButton.setBackground(Color.BLACK);
        rightButton.setFont(rightButton.getFont().deriveFont(50.0f));
        rightButton.setBorder(new LineBorder(Color.WHITE, 5));
        JButton downButton = new JButton("Down");
        downButton.setForeground(Color.GREEN);
        downButton.setBackground(Color.BLACK);
        downButton.setFont(downButton.getFont().deriveFont(50.0f));
        downButton.setBorder(new LineBorder(Color.WHITE, 5));
        directionPanel.add(new JLabel());
        directionPanel.add(upButton);
        directionPanel.add(new JLabel());
        directionPanel.add(leftButton);
        directionPanel.add(new JLabel());
        directionPanel.add(rightButton);
        directionPanel.add(new JLabel());
        directionPanel.add(downButton);
        directionPanel.add(new JLabel());

        //add everything to gamePanel and initialize
        gamePanel.add(snakeLenPanel, BorderLayout.NORTH);
        snakeLenPanel.setPreferredSize(new Dimension(1000, 50));
        gamePanel.add(fieldPanel, BorderLayout.CENTER);
        gamePanel.add(directionPanel, BorderLayout.SOUTH);
        directionPanel.setPreferredSize(new Dimension(1000, 450));
        gamePanel.add(right, BorderLayout.EAST);
        right.setPreferredSize(new Dimension(150, 1000));
        gamePanel.add(left, BorderLayout.WEST);
        left.setPreferredSize(new Dimension(150, 1000));
        gamePanel.setVisible(true);

        /*Death Panel Logic********/
        //deathMes and top panel properties
        deathMes.setForeground(Color.WHITE);
        deathMes.setFont(deathMes.getFont().deriveFont(40.0f));
        top.setBackground(Color.BLACK);
        top.add(deathMes);

        //playAgainButton properties
        playAgainButton.setBackground(Color.BLACK);
        playAgainButton.setForeground(Color.GREEN);
        playAgainButton.setBorder(new LineBorder(Color.WHITE, 5));
        playAgainButton.setFont(playAgainButton.getFont().deriveFont(35.0f));

        //menuButton properties
        menuButton.setBackground(Color.BLACK);
        menuButton.setForeground(Color.GREEN);
        menuButton.setBorder(new LineBorder(Color.WHITE, 5));
        menuButton.setFont(menuButton.getFont().deriveFont(35.0f));

        //interior panel properties
        interior.setBackground(Color.BLACK);
        interior.add(playAgainButton);
        interior.add(menuButton);

        //remaining panel properties
        deathBot.setBackground(Color.BLACK);
        deathRight.setBackground(Color.BLACK);
        deathLeft.setBackground(Color.BLACK);

        /*add everything to mainframe**/
        deathPanel.add(top, BorderLayout.NORTH);
        top.setPreferredSize(new Dimension(1000, 400));
        deathPanel.add(interior, BorderLayout.CENTER);
        interior.setPreferredSize(new Dimension(800, 100));
        deathPanel.add(deathLeft, BorderLayout.WEST);
        deathLeft.setPreferredSize(new Dimension(100, 100));
        deathPanel.add(deathRight, BorderLayout.EAST);
        deathRight.setPreferredSize(new Dimension(100, 100));
        deathPanel.add(deathBot, BorderLayout.SOUTH);
        deathBot.setPreferredSize(new Dimension(1000, 500));

        /*Win Panel Logic******************************************/
        winInterior.setBackground(Color.BLACK);
        winMes.setForeground(Color.WHITE);
        winMes.setFont(winMes.getFont().deriveFont(20.0f));
        winTop.setBackground(Color.BLACK);
        winTop.add(winMes);

        //winPlayAgainButtonButton properties
        winPlayAgainButtonButton.setBackground(Color.BLACK);
        winPlayAgainButtonButton.setForeground(Color.GREEN);
        winPlayAgainButtonButton.setBorder(new LineBorder(Color.WHITE, 5));
        winPlayAgainButtonButton.setFont(playAgainButton.getFont().deriveFont(35.0f));
        winInterior.add(winPlayAgainButtonButton);

        //menuButton properties
        winMenuButtonButton.setBackground(Color.BLACK);
        winMenuButtonButton.setForeground(Color.GREEN);
        winMenuButtonButton.setBorder(new LineBorder(Color.WHITE, 5));
        winMenuButtonButton.setFont(menuButton.getFont().deriveFont(35.0f));
        winInterior.add(winMenuButtonButton);

        //remaining panel properties
        winBot.setBackground(Color.BLACK);
        winRight.setBackground(Color.BLACK);
        winLeft.setBackground(Color.BLACK);

        //add everything to frame
        winPanel.add(winTop, BorderLayout.NORTH);
        winTop.setPreferredSize(new Dimension(1000, 400));
        winPanel.add(winInterior, BorderLayout.CENTER);
        winInterior.setPreferredSize(new Dimension(800, 100));
        winPanel.add(winLeft, BorderLayout.WEST);
        winLeft.setPreferredSize(new Dimension(100, 100));
        winPanel.add(winRight, BorderLayout.EAST);
        winRight.setPreferredSize(new Dimension(100, 100));
        winPanel.add(winBot, BorderLayout.SOUTH);
        winBot.setPreferredSize(new Dimension(1000, 500));

         /*
        ----------------------------------------------------------------------------------------------------------------------------------------------------------------
            Remaining Initialization
         ----------------------------------------------------------------------------------------------------------------------------------------------------------------
         */

        //add components to corresponding children panels

        //set background color of children panels
        menuPanel.setBackground(Color.BLACK);
        gamePanel.setBackground(Color.BLACK);
        deathPanel.setBackground(Color.BLACK);
        winPanel.setBackground(Color.BLACK);

        //add children panels to parent panel
        panelContent.add(menuPanel, "1");
        panelContent.add(gamePanel, "2");
        panelContent.add(deathPanel, "3");
        panelContent.add(winPanel, "4");

        //select default panel
        cl.show(panelContent, "1");

        //add parent panel to frame
        mainFrame.add(panelContent);
        mainFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        mainFrame.pack();
        mainFrame.setVisible(true);
        /*
        ----------------------------------------------------------------------------------------------------------------------------------------------------------------
             Action listeners
         ----------------------------------------------------------------------------------------------------------------------------------------------------------------
         */
        //timer to update snake position
        Timer timer = new Timer(500, event -> {
            if (model.snake.isAlive()) {
                model.updateModel();
                snakeLen.setText(" Snake Length: " + model.snake.getSize());
            }
        });

        //timer to check if snake is dead or has reached the required size and stops the timer
        final Timer endConditionCheck = new Timer(500, event -> {
            if (!model.snake.isAlive()) {
                cl.show(panelContent, "3");
                timer.stop();
                ((Timer) (event.getSource())).stop();
            } else {
                if (model.snake.checkWinCondition()) {
                    cl.show(panelContent, "4");
                    timer.stop();
                    ((Timer) (event.getSource())).stop();
                }
            }
        });

        /** Play button action listener*/
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cl.show(panelContent, "2");
                    model.reset();
                    model.updateModel();
                    timer.start();
                    endConditionCheck.start();
                    queue.put(new PlayGameMessage());
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });

        /** Play again button action listener*/
        playAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cl.show(panelContent, "2");
                    model.reset();
                    model.updateModel();
                    timer.start();
                    endConditionCheck.start();
                    queue.put(new PlayGameMessage());
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });

        /** Menu button action listener*/
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cl.show(panelContent, "1");
                    queue.put(new ReturnToMenuMessage());
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });

        /** Win play again button action listener*/
        winPlayAgainButtonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cl.show(panelContent, "2");
                    model.reset();
                    model.updateModel();
                    timer.start();
                    endConditionCheck.start();
                    queue.put(new PlayGameMessage());
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });

        /** Win Menu Button action listener*/
        winMenuButtonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cl.show(panelContent, "1");
                    queue.put(new ReturnToMenuMessage());
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
            }
        });

        /** Action listerner for UpButton*/
        upButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.snake.goUp();
            }
        });

        /** Action listener for LeftButton*/
        leftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.snake.goLeft();
            }
        });

        /** Action listener for RightButton*/
        rightButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.snake.goRight();
            }
        });

        /** Action listener for Down Button*/
        downButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                model.snake.goDown();
            }
        });
    }

    /**
     * Method to checks the board provided and updates the displayed grid accordingly
     * @param fullGrid the tiles to be updated
     */
    public void updateGrid(Tile[][] fullGrid) {
        for (int i = 0; i < fullGrid.length; i++) {
            for (int j = 0; j < fullGrid[0].length; j++) {
                if (fullGrid[i][j].getInsideTile().equals("nothing")) {
                    grid[i][j].setBackground(Color.GRAY);
                } else if (fullGrid[i][j].getInsideTile().equals("snake")) {
                    grid[i][j].setBackground(Color.GREEN);
                } else if (fullGrid[i][j].getInsideTile().equals("food")) {
                    grid[i][j].setBackground(Color.RED);
                }
            }
        }
    }

    public void change() {
        // TODO: do all the updates and repaint
        //gameFrame.repaint();
    }

    public void dispose() {
        // TODO: clear all the resources
        mainFrame.dispose();
    }
}

//resources used
/***
 * https://docs.oracle.com/javase/tutorial/uiswing/layout/grid.html
 *
 * https://docs.oracle.com/javase/7/docs/api/java/awt/BorderLayout.html
 *
 *https://stackoverflow.com/questions/36159929/printing-a-2d-array-of-jlabels-to-a-gridlayout
 *
 * https://stackoverflow.com/questions/9829319/centering-a-jlabel-in-a-jpanel
 *
 * https://stackoverflow.com/questions/3858920/stop-a-swing-timer-from-inside-the-action-listener
 */
