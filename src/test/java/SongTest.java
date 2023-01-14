import model.Song;
import org.junit.Test;
import settings.SettingsSong;

import static org.junit.Assert.*;

public class SongTest {

    private final Song song = new Song();
    private final String [] filenames =
            {"cais","farol","praia","vila",
                    "templo"};


    @Test
    public void validAllFilenames(){
        for (String filename : filenames) {
            song.play(filename);
            assertTrue(song.getFilename().contains(filename));
        }
    }

    @Test
    public void invalidFilename(){
        song.play("filename");
        assertNull(song.getFilename());
    }


    @Test
    public void validCloseFilename(){
        song.play(filenames[0]);
        song.closePlay();
        assertTrue(song.getFilename().contains(filenames[0]));
    }

    @Test
    public void validOffPlay(){
        boolean onOff = SettingsSong.isOnPlayer();
        assertEquals(onOff,SettingsSong.isOnPlayer());
    }

    @Test
    public void validOnPlay(){
        boolean onOff = SettingsSong.isOnPlayer();
        song.onPlayer();
        assertEquals(!onOff,SettingsSong.isOnPlayer());
    }

    @Test
    public void validOnPlayOff(){
        boolean onOff = SettingsSong.isOnPlayer();
        song.onPlayer();
        song.onPlayer();
        assertEquals(onOff,SettingsSong.isOnPlayer());
    }

}
