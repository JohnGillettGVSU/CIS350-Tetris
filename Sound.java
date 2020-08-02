import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;




public class Sound {


    /****/
    private final JFXPanel fxPanel = new JFXPanel();


    //Main Menu/Medium Mode Music
    /** Gives the file name and location of our main menu music.**/
    private String menuMusic = ("src/Resources/Music/mainmenu.mp3");
    /****/
    private Media menu = new Media(new File(menuMusic).toURI().toString());

    //Easy Mode Music
    /****/
    private String easyMusic = ("src/Resources/Music/easymode.mp3");
    /****/
    private Media easy = new Media(new File(easyMusic).toURI().toString());

    //Hard Mode Music
    /****/
    private String hardMusic = ("src/Resources/Music/hardmode.mp3");
    /****/
    private Media hard = new Media(new File(hardMusic).toURI().toString());


    /****/
    private MediaPlayer mediaPlayer;

    //private AudioInputStream audioStream;
    /**@param num Accepts an integer used for a switch,
     *which chooses which song or sound to play**/
    void playSound(final int num) throws IOException, UnsupportedAudioFileException {

        Clip click = null;
        try {
            click = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            assert click != null;
            click.open(AudioSystem.getAudioInputStream(new File("src/Resources/SFX/menuClick.wav")));
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        Clip mouse = null;
        try {
            mouse = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            assert mouse != null;
            mouse.open(AudioSystem.getAudioInputStream(new File("src/Resources/SFX/mouseover.wav")));
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        Clip startBeep = null;
        try {
            startBeep = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            assert startBeep != null;
            startBeep.open(AudioSystem.getAudioInputStream(new File("src/Resources/SFX/startGame.wav")));
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        Clip block = null;
        try {
            block = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            assert block != null;
            block.open(AudioSystem.getAudioInputStream(new File("src/Resources/SFX/blockLand.wav")));
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        Clip line = null;
        try {
            line = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        try {
            assert line != null;
            line.open(AudioSystem.getAudioInputStream(new File("src/Resources/SFX/lineClear.wav")));
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        //Main Menu Status
        switch (num) {
            case 1:
                //Main Menu/Medium Mode Theme
                mediaPlayer = new MediaPlayer(menu);
                mediaPlayer.play();
                break;
            case 2:
                //Easy Mode Theme
                mediaPlayer = new MediaPlayer(easy);
                mediaPlayer.play();
                break;
            case 3:
                //Hard Mode Theme
                mediaPlayer = new MediaPlayer(hard);
                mediaPlayer.play();
                break;
            case 4:
                //Menu Button Click
                try {
                    click.start();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
                break;
            case 5:
                //Start Button Beep
                try {
                    startBeep.start();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
            case 6:
                //Menu Hover Sound
                try {
                    mouse.start();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
                break;
            case 7:
                //Block Landing Click
                try {
                    block.start();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
            case 8:
                //Line Clear Beep
                try {
                    line.start();
                } catch (Exception e) {
                    e.printStackTrace(System.out);
                }
            default:
                break;
        }
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();
    }

    /****/
    void stopSound() {
        mediaPlayer.stop();
    }

    /**@return Returns boolean value informing whether
     * the media player is currently muted or not**/
    boolean getMuteStatus() {
        return mediaPlayer.isMute();
    }

    /****/
    void muteSound() {
        if (!mediaPlayer.isMute()) {
            mediaPlayer.setMute(true);
        } else if (mediaPlayer.isMute()) {
            mediaPlayer.setMute(false);
        }
    }


}
