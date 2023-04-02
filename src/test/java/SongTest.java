import frontend.model.Song;
import org.junit.Test;
import frontend.settings.SettingsSong;

import static org.junit.Assert.*;

public class SongTest {

    private final Song song = new Song();
    private final String filename = "src/main/resources/audio/scenery/";
    private final String [] filenames =
            {"cais","farol","praia","vila", "templo"};


    @Test
    public void validAllFilenames(){
        for (String filename : this.filenames) {
            this.song.play(this.filename + filename + ".wav");
            assertTrue(this.song.getFilename().contains(filename));
        }
    }

    @Test
    public void validCloseFilename(){
        this.song.play(this.filename + this.filenames[0] + ".wav");
        this.song.closePlay();
        assertTrue(this.song.getFilename().contains(this.filenames[0]));
    }

    @Test
    public void validOffPlay(){
        boolean onOff = SettingsSong.isOnPlayer();
        assertEquals(onOff,SettingsSong.isOnPlayer());
    }

    @Test
    public void validOnPlay(){
        boolean onOff = SettingsSong.isOnPlayer();
        this.song.onPlayer();
        assertEquals(!onOff,SettingsSong.isOnPlayer());
    }

    @Test
    public void validOnPlayOff(){
        boolean onOff = SettingsSong.isOnPlayer();
        this.song.onPlayer();
        this.song.onPlayer();
        assertEquals(onOff,SettingsSong.isOnPlayer());
    }

}
