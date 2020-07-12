import java.awt.*;

import java.awt.event.*;
import java.io.IOException;
import java.util.Random;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class MainMenu extends JPanel implements ActionListener {
    private JFrame f;
    private Sound menuMusic;

    //Main Menu Buttons
    private JButton startButton;
    private JButton diffButton;
    private JButton credButton;
    private JButton quitButton;

    //Difficulty Selection Buttons
    private JButton easyButton;
    private JButton medButton;
    private JButton hardButton;
    private JButton backButton;

    //Toggles Music Mute
    private JButton muteButton;
    private Icon muteIcon;
    private Icon unmuteIcon;

    private JSlider volume;

    //Needed to use graphics methods
    private Graphics g;

    private Icon bgImage;
    private JLabel background;
    private JLabel title;

    //Default Difficulty is Medium, or 2
    private int diffChoice = 2;

    //Array holding tetris pieces that are on the board or next in line
    private TetrisPiece[] pieces;

    //timer for the game cycle
    private Timer timer;

    //flags
    private boolean isFalling = false;

    //board array
    private boolean[][] board;


    private MainMenu() throws IOException, UnsupportedAudioFileException {
        menuMusic = new Sound();
        menuMusic.playSound(1);

        f = new JFrame("TETRIS 350");
        f.setSize(400, 600);
        f.getContentPane().setLayout(null);
        f.setResizable(false);
        f.setBackground(Color.black);

        Icon titleCard = new ImageIcon("src/Resources/Images/titlecard.png");
        title = new JLabel(titleCard);
        title.setBounds(75,0,250,200);

        //JPanel Background
        bgImage = new ImageIcon("src/Resources/Images/spacebackground.gif");
        background = new JLabel(bgImage);
        background.setBounds(0,0,400,600);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JMenuBar jmb = new JMenuBar();

        //Starts game with default normal difficulty
        startButton = new JButton("START");
        startButton.setBounds(100,200,200,30);
        startButton.setBackground( new Color( 0, 0, 0, 200 ) );
        startButton.setForeground( new Color(255, 175, 20) );

        //Takes user to difficulty selection pane
        diffButton = new JButton("DIFFICULTY");
        diffButton.setBounds(100,250,200,30);
        diffButton.setBackground( new Color( 0, 0, 0, 200 ) );
        diffButton.setForeground( new Color(255, 42, 134) );

        //Displays game credits
        credButton = new JButton("CREDITS");
        credButton.setBounds(100,300,200,30);
        credButton.setBackground( new Color( 0, 0, 0, 200 ) );
        credButton.setForeground( new Color(255, 17, 69) );

        //Exits game
        quitButton = new JButton("QUIT");
        quitButton.setBounds(100,350,200,30);
        quitButton.setBackground( new Color( 0, 0, 0, 200 ) );
        quitButton.setForeground( new Color(255, 9, 7) );


        //Draws Difficulty Menu

        easyButton = new JButton("EASY");
        easyButton.setBounds(100,200,200,30);
        easyButton.setBackground( new Color( 0, 0, 0, 200 ) );
        easyButton.setForeground( new Color(255, 175, 20) );

        medButton = new JButton("MEDIUM");
        medButton.setBounds(100,250,200,30);
        medButton.setBackground( new Color( 0, 0, 0, 200 ) );
        medButton.setForeground( new Color(255, 42, 134) );

        hardButton = new JButton("HARD");
        hardButton.setBounds(100,300,200,30);
        hardButton.setBackground( new Color( 0, 0, 0, 200 ) );
        hardButton.setForeground( new Color(255, 17, 69) );

        backButton = new JButton("BACK");
        backButton.setBounds(100,350,200,30);
        backButton.setBackground( new Color( 0, 0, 0, 200 ) );
        backButton.setForeground( new Color(255, 9, 7) );

        //Draws Audio Buttons

        muteButton = new JButton();
        volume = new JSlider(JSlider.VERTICAL,100);

        muteIcon = new ImageIcon("src/Resources/Images/muteButton.png");
        unmuteIcon = new ImageIcon("src/Resources/Images/unmuteButton.png");

        muteButton.setBounds(300,500,20,20);
        muteButton.setIcon(muteIcon);
        muteButton.setBackground( new Color( 0, 0, 0, 0) );
        muteButton.setForeground( new Color(255, 9, 7) );
        muteButton.setBorderPainted(false);

        //Create array of tetris pieces
        pieces = new TetrisPiece[7];
        board = new boolean[10][22];
        boardInit();


//        volume.addChangeListener(this);
//
//        public void stateChanged(ChangeEvent e) {
//            JSlider source = (JSlider)e.getSource();
//            if (!source.getValueIsAdjusting()) {
//                int fps = (int)source.getValue();
//                if (fps == 0) {
//                    if (!frozen) stopAnimation();
//                } else {
//                    delay = 1000 / fps;
//                    timer.setDelay(delay);
//                    timer.setInitialDelay(delay * 10);
//                    if (frozen) startAnimation();
//                }
//            }
//        }

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
        diffButton.addActionListener(this);
        credButton.addActionListener(this);
        quitButton.addActionListener(this);

        //Difficulty Menu Action Listeners
        easyButton.addActionListener(this);
        medButton.addActionListener(this);
        hardButton.addActionListener(this);
        backButton.addActionListener(this);

        //Mute Button Listener
        muteButton.addActionListener(this);

        f.setVisible(true);

        //Array that holds each tetris piece. slot 0 holds active piece,
        //slot 1 holds next piece, slot 2 piece after that, etc
        pieces = new TetrisPiece[7];
        fillPieces();
    }

    private void boardInit(){
        for(int x = 0; x <= 9; ++x){
            for(int y = 0; y <= 21; y++){
                board[x][y] = false;
            }
        }
    }

    private void updateBoard(){
        for(int i = 0; i <= 3; ++i) {
            //set each square of the board where a piece lands to true
            board[pieces[0].getPosition(i, 0)][pieces[0].getPosition(i, 1)] = true;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        drawTetrisGrid(g);
        drawPiece(g);
        clearLines();
        //doDrawing(g);
    }

    private void drawTetrisGrid(Graphics g){
        g.setColor(Color.LIGHT_GRAY);
        //draw vertical lines
        for(int x = 50; x < 300; x += 25){
            for(int y = 60; y < 560; y += 25){
                g.drawRect(x, y, 25, 25);
            }
        }
    }

    private void drawPiece(Graphics g){
        g.setColor(Color.BLUE);
        for(int i = 0; i <= 3; ++i){
            g.fillRect(pieces[0].getPosition(i,0)*25+51,
                    600 - (pieces[0].getPosition(i,1)*25+64),23,23);
        }
        for(int x = 0; x <= 9; ++x){
            for(int y = 0; y <= 21; y++){
                if(board[x][y]){
                    g.fillRect(x * 25 + 51, 600 - (y * 25 + 64), 23, 23);
                }
            }
        }
    }

    private void movePieces(){
        for(int i = 0; i <= 5; ++i){
            pieces[i] = pieces[i+1];
        }
        Random rand = new Random();
        createNewPiece(6);
    }

    private void createNewPiece(int i){
        Random rand = new Random();
        int r;
        r = rand.nextInt(7);
        if(r == 0){
            pieces[i] = new TetrisPiece("linePiece");
        }
        else if(r == 1){
            pieces[i] = new TetrisPiece("squarePiece");
        }
        else if(r == 2){
            pieces[i] = new TetrisPiece("T_Piece");
        }
        else if(r == 3){
            pieces[i] = new TetrisPiece("Z_Piece");
        }
        else if(r == 4){
            pieces[i] = new TetrisPiece("S_Piece");
        }
        else if(r == 5){
            pieces[i] = new TetrisPiece("L_Piece");
        }
        else if(r == 6){
            pieces[i] = new TetrisPiece("mirroredL_Piece");
        }
    }

    private void fillPieces(){
        for(int i = 0; i <= 6; ++i){
            createNewPiece(i);
        }
    }

    private void tryMovePieceDown(){
        int curX, curY;
        for(int i = 0; i <= 3; ++i) {
            curX = pieces[0].getPosition(i, 0);
            curY = pieces[0].getPosition(i, 1);
            if (curY == 0 || board[curX][curY - 1]){
                updateBoard();
                movePieces();
                return;
            }
        }
        pieces[0].downOneLine();
    }

    private void tryMoveLeft(){
        int curX;
        int curY;

        //check to make sure piece isn't at left edge
        for(int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            if (curX == 0){
                return;
            }
        }

        //check to make sure there isn't another piece in the way
        for(int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            curY = pieces[0].getPosition(x, 1);
            if (board[curX - 1][curY]){
                return;
            }
        }

        //move each square of the piece after making sure it can be moved
        for(int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            pieces[0].setPosition(x, 0, curX - 1);
        }
    }

    private void tryMoveRight(){
        int curX;
        int curY;

        //check to make sure piece isn't at right edge
        for(int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            if (curX == 9){
                return;
            }
        }

        //check to make sure there isn't another piece in the way
        for(int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            curY = pieces[0].getPosition(x, 1);
            if (board[curX + 1][curY]){
                return;
            }
        }

        //move each square of the piece after making sure it can be moved
        for(int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            pieces[0].setPosition(x, 0, curX + 1);
        }
    }

    private void tryMoveDown(){
        int curX;
        int curY;

        //check to make sure piece isn't at bottom edge
        for(int x = 0; x <= 3; ++x) {
            curY = pieces[0].getPosition(x, 1);
            if (curY == 0){
                return;
            }
        }

        //check to make sure there isn't another piece in the way
        for(int x = 0; x <= 3; ++x) {
            curX = pieces[0].getPosition(x, 0);
            curY = pieces[0].getPosition(x, 1);
            if (board[curX][curY - 1]){
                return;
            }
        }

        //move each square of the piece after making sure it can be moved
        for(int x = 0; x <= 3; ++x) {
            curY = pieces[0].getPosition(x, 1);
            pieces[0].setPosition(x, 1, curY - 1);
        }
    }

    private void clearLines(){
        boolean clear;
        for(int y = 21; y >= 0; --y){
            clear = true;
            for(int x = 0; x <= 9; x++){
                if(!board[x][y]){
                    clear = false;
                    break;
                }
            }
            if(clear){
                moveDown(y);
                repaint();
            }
        }
    }

    private void moveDown(int stop){
        for(int y = stop; y < 21; ++y){
            for(int x = 0; x <= 9; ++x){
                board[x][y] = board[x][y+1];
            }
        }
    }

    private void rotateLeft(){
        if(pieces[0].getName().equals("squarePiece")){
            return;
        }
        //do stuff to make piece rotate
    }

    private void rotateRight(){
        if(pieces[0].getName().equals("squarePiece")){
            return;
        }
        //do stuff to make piece rotate
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource()!= startButton) {
            try {
                menuMusic.playSound(4);
            } catch (IOException | UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource()== startButton){
            /**PLACEHOLDER**/
            //Start game with assigned difficulty
            //startGame(diffChoice)
            background.setIcon(null);
            f.remove(title);
            f.remove(startButton);
            f.remove(diffButton);
            f.remove(credButton);
            f.remove(quitButton);
            repaint();
            f.setContentPane(this);
            timer = new Timer(300, new GameCycle());
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

        if (e.getSource()== diffButton){
            //Open Difficulty Menu

            f.remove(background);
            f.remove(startButton);
            f.add(easyButton);
            f.remove(diffButton);
            f.add(medButton);
            f.remove(credButton);
            f.add(hardButton);
            f.remove(quitButton);
            f.add(backButton);
            f.add(background);


        }

        if (e.getSource()== easyButton){
            /**FIXME**/
            //Set Difficulty to Easy, or Value 1
            diffChoice = 1;

            //This Code Changes the Background Image to Easy Mode Image
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

        if (e.getSource()== medButton){
            /**FIXME**/
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

        if (e.getSource()== hardButton){
            /**FIXME**/
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

        if (e.getSource()== backButton){
            //Return to Main Menu
            f.remove(background);
            f.remove(easyButton);
            f.add(startButton);
            f.remove(medButton);
            f.add(diffButton);
            f.remove(hardButton);
            f.add(credButton);
            f.remove(backButton);
            f.add(quitButton);
            f.add(background);
        }

        if (e.getSource()== credButton){
            f.remove(background);
            f.remove(startButton);
            f.remove(diffButton);
            f.remove(credButton);
            f.remove(quitButton);
            f.add(backButton);
            f.add(background);
        }

        if (e.getSource() == quitButton){
            System.exit(1);
        }

        if (e.getSource() == muteButton){
            menuMusic.muteSound();
            if (menuMusic.getMuteStatus()){
                muteButton.setIcon(unmuteIcon);
            }
            else{
                muteButton.setIcon(muteIcon);
            }
        }
    }

    /**FIXME**/
    //Code to change buttons/make sounds when buttons are hovered over
//    public void mouseEntered(MouseEvent m) throws IOException, UnsupportedAudioFileException {
//        //Plays Noise when
//        if (){
//
//        }
//    }

    private class GameCycle implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            isFalling = true;
            nextCycle();
            repaint();
        }
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            if(keycode == KeyEvent.VK_LEFT) {
                tryMoveLeft();
            }
            else if(keycode == KeyEvent.VK_RIGHT) {
                tryMoveRight();
            }
            else if(keycode == KeyEvent.VK_DOWN) {
                tryMoveDown();
            }
            else if(keycode == KeyEvent.VK_Z) {
                rotateLeft();
            }
            else if(keycode == KeyEvent.VK_X) {
                rotateRight();
            }
            repaint();
        }
    }

    private void nextCycle(){
        if(isFalling){
            isFalling = false;
            tryMovePieceDown();
        }
    }


    public static void main(String args[]) throws IOException, UnsupportedAudioFileException {
        new MainMenu();
    }
}
