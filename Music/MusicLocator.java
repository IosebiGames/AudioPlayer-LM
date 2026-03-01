package Music;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import Main.Main;

public class MusicLocator {

    private Main main;
    private Clip clip;
    private long clipTimePosition = 0L;

    public MusicLocator(Main main) {
        this.main = main;
    }
    public void prepare() {
        try {
            clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(main.chooser.getSelectedFile()));
        } catch (Exception e) {
            System.out.println("Failed to play: " + e.getMessage());
        }
    }
    public void start() {
        if (clip != null) {
            clip.start();
        }
    }
    public void restart() {
        if (clip != null) {
            clip.setMicrosecondPosition(0L);
            clip.start();
        }
    }
    public void stop() {
        if (clip != null) {
            clip.stop(); 
       }
    }
    public void pauseMusic() {
        if (clip != null && clip.isRunning()) {
            clipTimePosition = clip.getMicrosecondPosition();
            clip.stop();
        }
    }
    public void saveMoment() {
        pauseMusic();
    }
    public void loadMoment() {
        try {
            if (clip != null) {
                clip.close();
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(main.chooser.getSelectedFile()));
                clip.setMicrosecondPosition(clipTimePosition); 
                clip.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}