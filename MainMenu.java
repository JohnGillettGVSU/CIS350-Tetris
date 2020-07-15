
import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.Timer;

/****/
public final class MainMenu extends JPanel implements ActionListener, MouseListener {
    /****/
    private JFrame f;
    /****/
    private Sound menuMusic;
    /****/
    private JLabel title;

    //Main Menu Buttons
    /****/
    private JButton startButton;
    /****/
    private JButton diffButton;
    /****/
    private JButton credButton;
    /****/
    private JButton quitButton;

    //Difficulty Selection Buttons
    /****/
    private JButton easyButton;
    /****/
    private JButton medButton;
    /****/
    private JButton hardButton;
    /****/
    private JButton backButton;

    //Toggles Music Mute
    /****/
    private JButton muteButton;
    /****/
    private Icon muteIcon;
    /****/
    private Icon unmuteIcon;


    //Declare Background Image Icon
    /****/
    private Icon bgImage;

    //Declare Main Menu Icons
    /****/
    private Icon startIcon;
    /****/
    private Icon startIconGlow;
    /****/
    private Icon diffIcon;
    /****/
    private Icon diffIconGlow;
    /****/
    private Icon credIcon;
    /****/
    private Icon credIconGlow;
    /****/
    private Icon quitIcon;
    /****/
    private Icon quitIconGlow;

    //Declare Difficulty Menu Icons
    /****/
    private Icon easyIcon;
    /****/
    private Icon easyIconGlow;
    /****/
    private Icon medIcon;
    /****/
    private Icon medIconGlow;
    /****/
    private Icon hardIcon;
    /****/
    private Icon hardIconGlow;
    /****/
    private Icon backIcon;
    /****/
    private Icon backIconGlow;

    /****/
    private JLabel background;

    //Default Difficulty is Medium, or 2
    /****/
    private int diffChoice = 2;

    //Array holding tetris pieces that are on the board or next in line
    /****/
    private TetrisPiece[] pieces;

    /****/
    private static final int NUM_PIECES = 7;

    //timer for the game cycle
    /****/
    private Timer timer;

    //flags
    /****/
    private boolean isFalling = false;

    //board array
    /****/
    private boolean[][] board;

    /****/
    private static final int BOARD_WIDTH = 10;

    /****/
    private static final int BOARD_HEIGHT = 24;

    /****/
    private static final int FRAME_WIDTH = 400;

    /****/
    private static final int FRAME_HEIGHT = 600;

    /****/
    private static final int BUTTON_WIDTH = 200;

    /****/
    private static final int BUTTON_HEIGHT = 45;

    /****/
    private static final int TITLE_WIDTH = 250;

    /****/
    private static final int TITLE_HEIGHT = 200;

    /****/
    private static final int BUTTON_XPOS = 100;

    /****/
    private static final int BUTTON_YPOS = 200;

    /****/
    private static final int MUTE_SIZE = 20;

    /****/
    private static final int MUTE_XPOS = 300;

    /****/
    private static final int MUTE_YPOS = 500;

    /****/
    private MainMenu() throws IOException, UnsupportedAudioFileException  {
        menuMusic = new Sound();
        menuMusic.playSound(1);

        f = new JFrame("TETRIS 350");
        f.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        f.getContentPane().setLayout(null);
        f.setResizable(false);
        f.setBackground(Color.black);

        Icon titleCard = new ImageIcon("src/Resources/Images/titlecard.png");
        title = new JLabel(titleCard);
        title.setBounds(75, 0, TITLE_WIDTH, TITLE_HEIGHT);

        //JPanel Background
        bgImage = new ImageIcon("src/Resources/Images/spacebackground.gif");
        background = new JLabel(bgImage);
        background.setBounds(0, 0, FRAME_WIDTH,  FRAME_HEIGHT);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Starts game with default normal difficulty
        startIcon = new ImageIcon("src/Resources/Images/start.png");
        startIconGlow = new ImageIcon("src/Resources/Images/startglow.png");
        startButton = new JButton();
        startButton.setBounds(BUTTON_XPOS, BUTTON_YPOS, BUTTON_WIDTH, BUTTON_HEIGHT);
        startButton.setIcon(startIcon);
        startButton.setBorderPainted(false);
        startButton.setBackground(new Color(0, 0, 0, 0));

        //Takes user to difficulty selection pane
        diffIcon = new ImageIcon("src/Resources/Images/difficulty.png");
        diffIconGlow = new ImageIcon("src/Resources/Images/difficultyglow.png");
        diffButton = new JButton();
        diffButton.setBounds(BUTTON_XPOS, BUTTON_YPOS + 50, BUTTON_WIDTH, BUTTON_HEIGHT);
        diffButton.setIcon(diffIcon);
        diffButton.setBorderPainted(false);
        diffButton.setBackground(new Color(0, 0, 0, 0));

        //Displays game credits
        credIcon = new ImageIcon("src/Resources/Images/credits.png");
        credIconGlow = new ImageIcon("src/Resources/Images/creditsglow.png");
        credButton = new JButton();
        credButton.setBounds(BUTTON_XPOS, BUTTON_YPOS + 100, BUTTON_WIDTH, BUTTON_HEIGHT);
        credButton.setIcon(credIcon);
        credButton.setBorderPainted(false);
        credButton.setBackground(new Color(0, 0, 0, 0));

        //Exits game
        quitIcon = new ImageIcon("src/Resources/Images/quit.png");
        quitIconGlow = new ImageIcon("src/Resources/Images/quitglow.png");
        quitButton = new JButton();
        quitButton.setBounds(BUTTON_XPOS, BUTTON_YPOS + 150, BUTTON_WIDTH, BUTTON_HEIGHT);
        quitButton.setIcon(quitIcon);
        quitButton.setBorderPainted(false);
        quitButton.setBackground(new Color(0, 0, 0, 0));


        //Draws Difficulty Menu
        easyIcon = new ImageIcon("src/Resources/Images/easy.png");
        easyIconGlow = new ImageIcon("src/Resources/Images/easyglow.png");
        easyButton = new JButton();
        easyButton.setBounds(BUTTON_XPOS, BUTTON_YPOS, BUTTON_WIDTH, BUTTON_HEIGHT);
        easyButton.setIcon(easyIcon);
        easyButton.setBorderPainted(false);
        easyButton.setBackground(new Color(0, 0, 0, 0));

        medIcon = new ImageIcon("src/Resources/Images/medium.png");
        medIconGlow = new ImageIcon("src/Resources/Images/mediumglow.png");
        medButton = new JButton();
        medButton.setBounds(BUTTON_XPOS, BUTTON_YPOS + 50, BUTTON_WIDTH, BUTTON_HEIGHT);
        medButton.setIcon(medIcon);
        medButton.setBorderPainted(false);
        medButton.setBackground(new Color(0, 0, 0, 0));

        hardIcon = new ImageIcon("src/Resources/Images/hard.png");
        hardIconGlow = new ImageIcon("src/Resources/Images/hardglow.png");
        hardButton = new JButton();
        hardButton.setBounds(BUTTON_XPOS, BUTTON_YPOS + 100, BUTTON_WIDTH, BUTTON_HEIGHT);
        hardButton.setIcon(hardIcon);
        hardButton.setBorderPainted(false);
        hardButton.setBackground(new Color(0, 0, 0, 0));

        backIcon = new ImageIcon("src/Resources/Images/back.png");
        backIconGlow = new ImageIcon("src/Resources/Images/backglow.png");
        backButton = new JButton();
        backButton.setBounds(BUTTON_XPOS, BUTTON_YPOS + 150, BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.setIcon(backIcon);
        backButton.setBorderPainted(false);
        backButton.setBackground(new Color(0, 0, 0, 0));

        //Draws Audio Buttons

        muteButton = new JButton();

        muteIcon = new ImageIcon("src/Resources/Images/muteButton.png");
        unmuteIcon = new ImageIcon("src/Resources/Images/unmuteButton.png");

        muteButton.setBounds(MUTE_XPOS, MUTE_YPOS, MUTE_SIZE, MUTE_SIZE);
        muteButton.setIcon(muteIcon);
        muteButton.setBackground(new Color(0, 0, 0, 0));
        muteButton.setBorderPainted(false);

        pieces = new TetrisPiece[NUM_PIECES];
        board = new boolean[BOARD_WIDTH][BOARD_HEIGHT];
        boardInit();


        //Adds Main Menu Buttons
        f.add(startButton);
        f.add(diffButton);
        f.add(credButton);
        f.add(quitButton);

        //Adds Mute Button
        f.add(muteButton);

        //Adds Title Icon
        f.add(title);

        //Adds Scrolling Background Label
        f.add(background);

        //Main Menu Action Listeners
        startButton.addActionListener(this);
        startButton.addMouseListener(this);
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent mouseEvent) {
                startButton.setIcon(startIconGlow);
            }
            @Override
            public void mouseExited(final MouseEvent mouseEvent) {
                startButton.setIcon(startIcon);
            }
        });

        diffButton.addActionListener(this);
        diffButton.addMouseListener(this);
        diffButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent mouseEvent) {
                diffButton.setIcon(diffIconGlow);
            }
            @Override
            public void mouseExited(final MouseEvent mouseEvent) {
                diffButton.setIcon(diffIcon);
            }
        });

        credButton.addActionListener(this);
        credButton.addMouseListener(this);
        credButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent mouseEvent) {
                credButton.setIcon(credIconGlow);
            }
            @Override
            public void mouseExited(final MouseEvent mouseEvent) {
                credButton.setIcon(credIcon);
            }
        });

        quitButton.addActionListener(this);
        quitButton.addMouseListener(this);
        quitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent mouseEvent) {
                quitButton.setIcon(quitIconGlow);
            }
            @Override
            public void mouseExited(final MouseEvent mouseEvent) {
                quitButton.setIcon(quitIcon);
            }
        });

        //Difficulty Menu Action Listeners
        easyButton.addActionListener(this);
        easyButton.addMouseListener(this);
        easyButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent mouseEvent) {
                easyButton.setIcon(easyIconGlow);
            }
            @Override
            public void mouseExited(final MouseEvent mouseEvent) {
                easyButton.setIcon(easyIcon);
            }
        });

        medButton.addActionListener(this);
        medButton.addMouseListener(this);
        medButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent mouseEvent) {
                medButton.setIcon(medIconGlow);
            }
            @Override
            public void mouseExited(final MouseEvent mouseEvent) {
                medButton.setIcon(medIcon);
            }
        });

        hardButton.addActionListener(this);
        hardButton.addMouseListener(this);
        hardButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent mouseEvent) {
                hardButton.setIcon(hardIconGlow);
            }
            @Override
            public void mouseExited(final MouseEvent mouseEvent) {
                hardButton.setIcon(hardIcon);
            }
        });

        backButton.addActionListener(this);
        backButton.addMouseListener(this);
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(final MouseEvent mouseEvent) {
                backButton.setIcon(backIconGlow);
            }
            @Override
            public void mouseExited(final MouseEvent mouseEvent) {
                backButton.setIcon(backIcon);
            }
        });


        //Mute Button Listener
        muteButton.addActionListener(this);

        f.setVisible(true);

        pieces = new TetrisPiece[NUM_PIECES];
        fillPieces();
    }

    /****/
    private void boardInit() {
        for (int x = 0; x <= 9; ++x) {
            for (int y = 0; y <= 21; y++) {
                board[x][y] = false;
            }
        }
    }

    /****/
    private void updateBoard() {
        for (int i = 0; i <= 3; ++i) {
            //set each square of the board where a piece lands to true
            board[pieces[0].getPosition(i, 0)][pieces[0].getPosition(i, 1)] = true;
        }
    }

    /**@param g **/
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        drawTetrisGrid(g);
        drawPiece(g);
        clearLines();
        //doDrawing(g);
    }

    /**@param g **/
    private void drawTetrisGrid(final Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        //draw vertical lines
        for (int x = 50; x < 300; x += 25) {
            for (int y = 60; y < 560; y += 25) {
                g.drawRect(x, y, 25, 25);
            }
        }
    }

    /**@param g **/
    private void drawPiece(final Graphics g) {
        g.setColor(Color.BLUE);
        for (int i = 0; i <= 3; ++i) {
            g.fillRect(pieces[0].getPosition(i, 0) * 25 + 51,
                    600 - (pieces[0].getPosition(i, 1) * 25 + 64), 23, 23);
        }
        for (int x = 0; x <= 9; ++x) {
            for (int y = 0; y <= 21; y++) {
                if (board[x][y]) {
                    g.fillRect(x * 25 + 51, 600 - (y * 25 + 64), 23, 23);
                }
            }
        }
    }

    /****/
    private void movePieces() {
        for (int i = 0; i <= 5; ++i) {
            pieces[i] = pieces[i + 1];
        }
        Random rand = new Random();
        createNewPiece(6);
    }

    /**@param i **/
    private void createNewPiece(final int i) {
        Random rand = new Random();
        int r;
        r = rand.nextInt(7);
        if (r == 0) {
            pieces[i] = new TetrisPiece("linePiece");
        } else if (r == 1) {
            pieces[i] = new TetrisPiece("squarePiece");
        } else if (r == 2) {
            pieces[i] = new TetrisPiece("T_Piece");
        } else if (r == 3) {
            pieces[i] = new TetrisPiece("Z_Piece");
        } else if (r == 4) {
            pieces[i] = new TetrisPiece("S_Piece");
        } else if (r == 5) {
            pieces[i] = new TetrisPiece("L_Piece");
        } else if (r == 6) {
            pieces[i] = new TetrisPiece("mirroredL_Piece");
        }
    }

    /****/
    private void fillPieces() {
        for (int i = 0; i <= 6; ++i) {
            createNewPiece(i);
        }
    }

    /****/
    private void tryMovePieceDown() {
        int curX;
        int curY;
        for (int i = 0; i <= 3; ++i) {
            curX = pieces[0].getPosition(i, 0);
            curY = pieces[0].getPosition(i, 1);
            if (curY == 0 || board[curX][curY - 1]) {
                updateBoard();
                movePieces();
                return;
            }
        }
        pieces[0].downOneLine();
    }

    /****/
    private void tryMoveLeft() {
        int curX;
        int curY;

        //check to make sure piece isn't at left edge
        for (int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            if (curX == 0) {
                return;
            }
        }

        //check to make sure there isn't another piece in the way
        for (int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            curY = pieces[0].getPosition(x, 1);
            if (board[curX - 1][curY]) {
                return;
            }
        }

        //move each square of the piece after making sure it can be moved
        for (int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            pieces[0].setPosition(x, 0, curX - 1);
        }
    }

    /****/
    private void tryMoveRight() {
        int curX;
        int curY;

        //check to make sure piece isn't at right edge
        for (int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            if (curX == 9) {
                return;
            }
        }

        //check to make sure there isn't another piece in the way
        for (int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            curY = pieces[0].getPosition(x, 1);
            if (board[curX + 1][curY]) {
                return;
            }
        }

        //move each square of the piece after making sure it can be moved
        for (int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            pieces[0].setPosition(x, 0, curX + 1);
        }
    }

    /****/
    private void tryMoveDown() {
        int curX;
        int curY;

        //check to make sure piece isn't at bottom edge
        for (int x = 0; x <= 3; ++x) {
            curY = pieces[0].getPosition(x, 1);
            if (curY == 0) {
                return;
            }
        }

        //check to make sure there isn't another piece in the way
        for (int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            curY = pieces[0].getPosition(x, 1);
            if (board[curX][curY - 1]) {
                return;
            }
        }

        //move each square of the piece after making sure it can be moved
        for (int x = 0; x <= 3; ++x) {
            curY = pieces[0].getPosition(x, 1);
            pieces[0].setPosition(x, 1, curY - 1);
        }
    }

    /****/
    private void clearLines() {
        boolean clear;
        for (int y = 21; y >= 0; --y) {
            clear = true;
            for (int x = 0; x <= 9; x++) {
                if (!board[x][y]) {
                    clear = false;
                    break;
                }
            }
            if (clear) {
                moveDown(y);
                repaint();
            }
        }
    }

    /**@param stop **/
    private void moveDown(final int stop) {
        for (int y = stop; y < 21; ++y) {
            for (int x = 0; x <= 9; ++x) {
                board[x][y] = board[x][y + 1];
            }
        }
    }

    /**@param e **/
    public void actionPerformed(final ActionEvent e) {
        if (e.getSource() != startButton) {
            try {
                menuMusic.playSound(4);
            } catch (IOException | UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == startButton) {
            //FIXME
            //Start game with assigned difficulty
            background.setIcon(null);
            clearMainMenu();
            repaint();

            f.setContentPane(this);
            switch (diffChoice) {
                case 1:
                    timer = new Timer(1000, new GameCycle());
                case 2:
                    timer = new Timer(750, new GameCycle());
                case 3:
                    timer = new Timer(300, new GameCycle());
                default:
                    timer = new Timer(750, new GameCycle());
            }

            timer.start();
            //setFocusable(true);
            addKeyListener(new TAdapter());
            requestFocus();

            try {
                menuMusic.stopSound();
                menuMusic.playSound(5);
            } catch (IOException | UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == diffButton) {
            //Open Difficulty Menu
            clearMainMenu();
            drawDiffMenu();


        }

        if (e.getSource() == easyButton) {
            //FIXME
            //Set Difficulty to Easy, or Value 1
            diffChoice = 1;

            //This Code Changes the Background Image to East Mode Image
            bgImage = new ImageIcon("src/Resources/Images/vaporTrainTrimmed.gif");
            background.setIcon(bgImage);
            background.validate();
            background.repaint();

            //This Code Changes the Music to Easy Mode Theme
            try {
                menuMusic.stopSound();
                menuMusic.playSound(2);
            } catch (IOException | UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == medButton) {
            //FIXME
            //Set Difficulty to Medium, or Value 2
            diffChoice = 2;

            //This Code Changes the Background Image to Medium Mode Image
            bgImage = new ImageIcon("src/Resources/Images/waveSpiralTrimmed.gif");
            background.setIcon(bgImage);
            background.validate();
            background.repaint();

            //This Code Changes the Music to Medium Mode/Menu Theme
            try {
                menuMusic.stopSound();
                menuMusic.playSound(1);
            } catch (IOException | UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == hardButton) {
            //FIXME
            //Set Difficulty to Hard, or Value 3
            diffChoice = 3;

            //This Code Changes the Background Image to Hard Mode Image
            bgImage = new ImageIcon("src/Resources/Images/trippyPyramidTrimmed.gif");
            background.setIcon(bgImage);
            background.validate();
            background.repaint();

            //This Code Changes the Music to Hard Mode Theme
            try {
                menuMusic.stopSound();
                menuMusic.playSound(3);
            } catch (IOException | UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            }
        }

        if (e.getSource() == backButton) {
            //Return to Main Menu
            clearDiffMenu();
            drawMainMenu();
        }

        if (e.getSource() == credButton) {
            f.remove(background);
            f.remove(startButton);
            f.remove(diffButton);
            f.remove(credButton);
            f.remove(quitButton);
            f.add(backButton);
            f.add(background);
        }

        if (e.getSource() == quitButton) {
            System.exit(1);
        }

        if (e.getSource() == muteButton) {
            menuMusic.muteSound();
            if (menuMusic.getMuteStatus()) {
                muteButton.setIcon(unmuteIcon);
            } else {
                muteButton.setIcon(muteIcon);
            }
        }
    }

    /****/
    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(final ActionEvent e) {

            isFalling = true;
            nextCycle();
            repaint();
        }
    }

    /****/
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(final KeyEvent e) {
            int keycode = e.getKeyCode();
            if (keycode == KeyEvent.VK_LEFT) {
                tryMoveLeft();
            } else if (keycode == KeyEvent.VK_RIGHT) {
                tryMoveRight();
            } else if (keycode == KeyEvent.VK_DOWN) {
                tryMoveDown();
            } else if (keycode == KeyEvent.VK_Z) {
                pieces[0].rotateLeft(board);
            } else if (keycode == KeyEvent.VK_X) {
                pieces[0].rotateRight(board);
            }
            repaint();
        }
    }

    private void nextCycle() {
        if (isFalling) {
            isFalling = false;
            tryMovePieceDown();
        }
    }

    //Code to make icons glow and play sounds when buttons are hovered over
    /**@param m **/
    public void mouseEntered(final MouseEvent m) {
        Component c = m.getComponent();
        try {
            menuMusic.playSound(6);
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void mouseClicked(final MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(final MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(final MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(final MouseEvent mouseEvent) {

    }

    /****/
    void clearMainMenu() {
        f.remove(title);
        f.remove(startButton);
        f.remove(diffButton);
        f.remove(credButton);
        f.remove(quitButton);
    }

    /****/
    void clearDiffMenu() {
        f.remove(easyButton);
        f.remove(medButton);
        f.remove(hardButton);
        f.remove(backButton);
    }

    /****/
    void drawMainMenu() {
        f.remove(background);
        f.add(title);
        f.add(startButton);
        f.add(diffButton);
        f.add(credButton);
        f.add(quitButton);
        f.add(background);
    }

    /****/
    void drawDiffMenu() {
        f.remove(background);
        f.add(title);
        f.add(easyButton);
        f.add(medButton);
        f.add(hardButton);
        f.add(backButton);
        f.add(background);
    }

    /**@param args **/
    public static void main(final String[] args) throws IOException, UnsupportedAudioFileException {
        new MainMenu();
    }
}
