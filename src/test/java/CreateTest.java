import exception.CreateImageException;
import org.junit.Test;
import repository.CreateImageItem;
import repository.CreateImageMapGame;
import repository.CreateImagePlayer;

import static org.junit.Assert.assertEquals;

public class CreateTest {

    @Test (expected = CreateImageException.class)
    public void createImageItem() {
        CreateImageItem createImageItem = new CreateImageItem();
        createImageItem.selectImage("aaaa");
    }

    @Test (expected = CreateImageException.class)
    public void createImageMapGame() {
        CreateImageMapGame createImageMapGame = new CreateImageMapGame();
        createImageMapGame.selectImage("aaaa");
    }

    @Test (expected = CreateImageException.class)
    public void createImagePlayer() {
        CreateImagePlayer createImagePlayer = new CreateImagePlayer();
        createImagePlayer.selectImage("aaaa");
    }


}
