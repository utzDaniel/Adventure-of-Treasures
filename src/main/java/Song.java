public class Song extends MusicPlayer {

    @Override
    public void play(String nameMapGame) {
        ListSong filename = new ListSong();
        if(nextPlay(filename.listSong(nameMapGame)) && SettingsSong.isOnPlayer()){
            startPlay();
        }
    }

    private boolean nextPlay(String newFilename) {
        if(newFilename != null){
            if(super.getFilename() == null || !super.getFilename().equals(newFilename)){
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
    void onPlayer() {
        if(SettingsSong.isOnPlayer()){
            SettingsSong.setOnPlayer();
            super.closePlay();
        }else{
            SettingsSong.setOnPlayer();
            startPlay();
        }
    }
}

