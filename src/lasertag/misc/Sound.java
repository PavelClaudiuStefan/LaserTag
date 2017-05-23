package lasertag.misc;

import lasertag.server.GUIButtonListener;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    public static synchronized void playSound(final String url) {
        Runnable play = () -> {
            try {
                Clip clip = AudioSystem.getClip();
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(
                        GUIButtonListener.class.getResourceAsStream("/lasertag/sounds/" + url));
                clip.open(inputStream);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        new Thread(play).start();
    }

}
