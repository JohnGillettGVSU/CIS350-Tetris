import java.awt.*;

import java.awt.event.*;
import java.io.IOException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;

public class MainMenu extends JFrame implements ActionListener {
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


    private Icon bgImage;
    private JLabel background;

    //Default Difficulty is Medium, or 2
    private int diffChoice = 2;


    private MainMenu() throws IOException, UnsupportedAudioFileException {
        menuMusic = new Sound();
        menuMusic.playSound(1);

        f = new JFrame("TETRIS 350");
        f.setSize(400, 600);
        f.getContentPane().setLayout(null);
        f.setResizable(false);
        f.setBackground(Color.black);

        Icon titleCard = new ImageIcon("src/Resources/Images/titlecard.png");
        JLabel title = new JLabel(titleCard);
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

    public static void main(String args[]) throws IOException, UnsupportedAudioFileException {
        new MainMenu();
    }
}
