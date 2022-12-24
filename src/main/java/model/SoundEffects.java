package model;

import repository.ListSoundEffects;
import settings.SettingsSoundEffects;

public class SoundEffects extends MusicPlayer {

    @Override
    public void play(String command) {
        if(SettingsSoundEffects.isOnPlayer()){
            ListSoundEffects filename = new ListSoundEffects();
            setFilename(filename.commandSoundEffects(command));
            startPlay();
        }
    }

    public void play(String command, String nameItem) {
        if(SettingsSoundEffects.isOnPlayer()) {
            ListSoundEffects filename = new ListSoundEffects();
            setFilename(filename.listSoundEffects(command, nameItem));
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
        }
    }

    @Override
    public void onPlayer() {
        if(SettingsSoundEffects.isOnPlayer()){
            SettingsSoundEffects.setOnPlayer();
            super.closePlay();
        }else{
            SettingsSoundEffects.setOnPlayer();
            startPlay();
        }
    }
}

