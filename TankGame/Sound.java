package TankGame;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
    private AudioInputStream soundStream;
    private String soundFile;
    private Clip clip;


    public Sound(String File) {
        this.soundFile = File;
        try {
            soundStream = AudioSystem.getAudioInputStream(Sound.class.getResource(soundFile));
            clip = AudioSystem.getClip();
            clip.open(soundStream);
        } catch (Exception e) {
            System.out.println(e.getMessage() + "Sound File is Empty...");

        }
    }

    public void start() {
        clip.start();
    }

    public void stop() {
        clip.stop();
    }

}
