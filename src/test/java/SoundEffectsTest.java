import model.SoundEffects;
import org.junit.Before;
import org.junit.Test;
import settings.SettingsSoundEffects;

import static org.junit.Assert.*;

public class SoundEffectsTest {

    private final SoundEffects soundEffects = new SoundEffects();
    private final String filename = "src/main/resources/audio/effects/";
    private final String [] filenameItem =
            {"pegar","remover","finish","chave","escada","pa","mochila","tocha","mapa","construir","fogo","erro"};

    @Before
    public void onPlayer(){
        if(!SettingsSoundEffects.isOnPlayer())
            SettingsSoundEffects.setOnPlayer();
    }
    @Test
    public void validAllFilenames(){
        for (String filename : filenameItem) {
            this.soundEffects.play(this.filename + filename + ".wav");
            assertTrue(this.soundEffects.getFilename().contains(filename));
        }
    }

    @Test
    public void invalidFilename(){
        this.soundEffects.play("filename" + this.filenameItem[0] + ".wav");
        assertTrue(this.soundEffects.getFilename().contains(this.filenameItem[11]));
    }

    @Test
    public void validCloseFilename(){
        this.soundEffects.play(this.filename + this.filenameItem[0] + ".wav");
        this.soundEffects.closePlay();
        assertTrue(this.soundEffects.getFilename().contains(this.filenameItem[0]));
    }

    @Test
    public void validOffPlay(){
        boolean onOff = SettingsSoundEffects.isOnPlayer();
        assertEquals(onOff,SettingsSoundEffects.isOnPlayer());
    }

    @Test
    public void validOnPlay(){
        boolean onOff = SettingsSoundEffects.isOnPlayer();
        this.soundEffects.onPlayer();
        assertEquals(!onOff,SettingsSoundEffects.isOnPlayer());
    }

    @Test
    public void validOnPlayOff(){
        boolean onOff = SettingsSoundEffects.isOnPlayer();
        this.soundEffects.onPlayer();
        this.soundEffects.onPlayer();
        assertEquals(onOff,SettingsSoundEffects.isOnPlayer());
    }

}
