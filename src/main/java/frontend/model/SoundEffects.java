package frontend.model;

import frontend.settings.SettingsSoundEffects;

public final class SoundEffects extends MusicPlayer {

    @Override
    public void play(String effect) {
        if (SettingsSoundEffects.isOnPlayer()) {
            setFilename(effect);
            startPlay();
        }
    }

    @Override
    protected void startPlay() {
        super.closePlay();
        super.startPlayer();
        try {
            super.start();
            Thread.sleep(super.returnDuration() / 100);
        } catch (Exception ex) {
            ex.getStackTrace();
            this.play("src/main/resources/audio/effects/erro.wav");
        }
    }

    @Override
    public void onPlayer() {
        if (SettingsSoundEffects.isOnPlayer()) {
            SettingsSoundEffects.setOnPlayer();
            super.closePlay();
        } else {
            SettingsSoundEffects.setOnPlayer();
            startPlay();
        }
    }
}

