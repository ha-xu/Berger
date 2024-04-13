package model;
import javax.sound.sampled.*;
import java.util.Objects;


public class SoundPlayer {
    private static Clip backgroundClip;
    private static void playSound(String soundName) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Objects.requireNonNull(SoundPlayer.class.getResource("/sounds/" + soundName + ".wav"))));
            clip.start();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void playSound(String soundName, int loop) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(Objects.requireNonNull(SoundPlayer.class.getResource("/sounds/" + soundName + ".wav"))));
            clip.loop(loop);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    //stop sound
    private static void stopSound(Clip clip) {
        try {
            if(clip != null && clip.isRunning()){
                System.out.println("stop sound");
                clip.stop();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void playBackGroundSound() {
        try {
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(AudioSystem.getAudioInputStream(Objects.requireNonNull(SoundPlayer.class.getResource("/sounds/background1.wav"))));
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public static void stopBackGroundSound() {
        stopSound(backgroundClip);
    }

    public static void playWolfEatSound() {
        playSound("bite");
    }

    public static void playSheepEatSound() {
        playSound("sheepeat");
    }

    public static void playGameOverSound() {
        playSound("gameover");
    }

    public static void playWinSound() {
        playSound("win");
    }

    public static void playSheepSound() {
        playSound("sheep");
    }

    public static void playWolfSound() {
        playSound("wolf");
    }

    public static void playPickSound() {
        playSound("pick");
    }

    public static void playBuySound(){
        playSound("buy");
    }



}
