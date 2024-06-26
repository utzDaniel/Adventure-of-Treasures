package frontend.model;

import java.io.File;
import java.util.Objects;
import javax.sound.sampled.*;

public abstract class MusicPlayer {

    protected String filename;
    protected Clip clip;

    protected abstract void play(String requirement);

    protected abstract void startPlay();

    public abstract void onPlayer();

    public String getFilename() { return this.filename; }

    protected void setFilename(String filename) {
        this.filename = filename;
    }

    protected void startPlayer() {
        try {
            File file = new File(this.filename);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            this.clip = AudioSystem.getClip();
            this.clip.open(audioStream);
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    protected void start() {
        this.clip.setFramePosition(0);
        this.clip.start();
    }

//    protected long pause() {
//        long pause = this.clip.getMicrosecondPosition();
//        this.clip.stop();
//        return pause;
//    }
//
//    protected void play(long pause) {
//        this.clip.setMicrosecondPosition(pause);
//        this.clip.start();
//    }

    protected void stop() {
        this.clip.stop();
    }

    public void closePlay() {
        if (Objects.nonNull(this.clip)) {
            this.clip.close();
        }
    }

    protected void loop() {
        this.clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    protected long returnDuration() {
        return this.clip.getFrameLength();
    }
}
