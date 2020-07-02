import javax.sound.sampled.*;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class Sound{



    final JFXPanel fxPanel = new JFXPanel();


    //Main Menu/Medium Mode Music
    String menuMusic = ("src/Resources/Music/mainmenu.mp3");
    Media menu = new Media(new File(menuMusic).toURI().toString());

    //Easy Mode Music
    String easyMusic = ("src/Resources/Music/easymode.mp3");
    Media easy = new Media(new File(easyMusic).toURI().toString());

    //Medium Mode Music
//    String medMusic = ("src/Resources/Music/mediummode.mp3");
//    Media medium = new Media(new File(medMusic).toURI().toString());

    //Hard Mode Music
    String hardMusic = ("src/Resources/Music/hardmode.mp3");
    Media hard = new Media(new File(hardMusic).toURI().toString());


    MediaPlayer mediaPlayer;

    //private AudioInputStream audioStream;
    void playSound(int num) throws IOException, UnsupportedAudioFileException {

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

        //Main Menu Status
        switch (num){
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
                try{
                    click.start();
                }catch(Exception e)
                {
                    e.printStackTrace(System.out);
                }
                break;
            case 5:
                try{
                    startBeep.start();
                }catch(Exception e)
                {
                    e.printStackTrace(System.out);
                }
                break;


        }
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                mediaPlayer.seek(Duration.ZERO);
            }
        });
        mediaPlayer.play();
    }


    void stopSound(){
        mediaPlayer.stop();
    }

    boolean getMuteStatus(){
        return mediaPlayer.isMute();
    }

    void volumeSlider(double num){
        double temp = num * 0.01;
        mediaPlayer.setVolume(temp);
    }

    void muteSound(){
        if (!mediaPlayer.isMute()){
            mediaPlayer.setMute(true);
        }
        else if (mediaPlayer.isMute()){
            mediaPlayer.setMute(false);
        }
    }


}
