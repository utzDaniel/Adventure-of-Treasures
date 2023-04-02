package frontend.model;

import frontend.settings.SettingsSong;

import java.util.Objects;

public class Song extends MusicPlayer {

    @Override
    public void play(String song) {
        if(nextPlay(song) && SettingsSong.isOnPlayer()){
            startPlay();
        }
    }

    private boolean nextPlay(String newFilename) {
        if(Objects.nonNull(newFilename)){
            if(Objects.isNull(super.getFilename()) || !super.getFilename().equals(newFilename)){
                super.setFilename(newFilename);
                return true;
            }
        }
        return false;
    }

    @Override
    protected void startPlay() {
        super.closePlay();
        super.startPlayer();
        try {
            super.start();
            super.loop();
        } catch (Exception ex) {
            ex.getStackTrace();
        }
    }

    @Override
    public void onPlayer() {
        if(SettingsSong.isOnPlayer()){
            SettingsSong.setOnPlayer();
            super.closePlay();
        }else{
            SettingsSong.setOnPlayer();
            startPlay();
        }
    }
}

