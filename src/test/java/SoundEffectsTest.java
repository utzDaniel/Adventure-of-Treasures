import model.SoundEffects;
import org.junit.Before;
import org.junit.Test;
import settings.SettingsSoundEffects;

import static org.junit.Assert.*;

public class SoundEffectsTest {

    private final SoundEffects soundEffects = new SoundEffects();
    private final String [] filenamesCommand =
            {"pegar","remover","finish","erro"};
    private final String [][] filenamesItensUse =
            {{"chave","escada","pa","tesouro","0"},
             {"chave","escada","pa","saida","erro"}};
    private final String [][] filenamesItensEquipar =
            {{"mochila","tocha","erro"},
                    {"mochila","tocha","erro"}};
    private final String [][] filenamesItensCombinar =
            {{"papel","livro","madeiras","pregos","martelo","madeira", "faca", "pederneira", "frasco","0"},
             {"mapa","mapa","construir","construir","construir","fogo", "fogo", "fogo", "fogo","erro"}};

    @Before
    public void onPlayer(){
        if(!SettingsSoundEffects.isOnPlayer())
            SettingsSoundEffects.setOnPlayer();
    }

    @Test
    public void validAllFilenamesItensEquipar(){
        for (int i = 0; i < filenamesItensEquipar[0].length; i++) {
            soundEffects.play("equipar",filenamesItensEquipar[0][i]);
            assertTrue(soundEffects.getFilename().contains(filenamesItensEquipar[1][i]));
        }
    }

    @Test
    public void validAllFilenamesItensCombinar(){
        for (int i = 0; i < filenamesItensCombinar[0].length; i++) {
            soundEffects.play("combinar",filenamesItensCombinar[0][i]);
            assertTrue(soundEffects.getFilename().contains(filenamesItensCombinar[1][i]));
        }
    }

    @Test
    public void validAllFilenamesItensUse(){
        for (int i = 0; i < filenamesItensUse[0].length; i++) {
            soundEffects.play("usar",filenamesItensUse[0][i]);
            assertTrue(soundEffects.getFilename().contains(filenamesItensUse[1][i]));
        }
    }

    @Test
    public void invalidFilenameItens(){
        soundEffects.play("filename",filenamesItensUse[0][0]);
        assertTrue(soundEffects.getFilename().contains(filenamesItensUse[1][4]));
    }

    @Test
    public void validAllFilenamesCommand(){
        for (String filename : filenamesCommand) {
            soundEffects.play(filename);
            assertTrue(soundEffects.getFilename().contains(filename));
        }
    }

    @Test
    public void validCloseFilename(){
        soundEffects.play(filenamesCommand[0]);
        soundEffects.closePlay();
        assertTrue(soundEffects.getFilename().contains(filenamesCommand[0]));
    }

    @Test
    public void validOffPlay(){
        boolean onOff = SettingsSoundEffects.isOnPlayer();
        assertEquals(onOff,SettingsSoundEffects.isOnPlayer());
    }

    @Test
    public void validOnPlay(){
        boolean onOff = SettingsSoundEffects.isOnPlayer();
        soundEffects.onPlayer();
        assertEquals(!onOff,SettingsSoundEffects.isOnPlayer());
    }

    @Test
    public void validOnPlayOff(){
        boolean onOff = SettingsSoundEffects.isOnPlayer();
        soundEffects.onPlayer();
        soundEffects.onPlayer();
        assertEquals(onOff,SettingsSoundEffects.isOnPlayer());
    }

}
