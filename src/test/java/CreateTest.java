import exception.CreateImageException;
import org.junit.Test;
import repository.CreateImageMapGame;
import repository.CreateImagePlayer;

public class CreateTest {

    @Test(expected = CreateImageException.class)
    public void createImageMapGame() {
        CreateImageMapGame createImageMapGame = new CreateImageMapGame();
        createImageMapGame.selectImage("aaaa");
    }

    @Test(expected = CreateImageException.class)
    public void createImagePlayer() {
        CreateImagePlayer createImagePlayer = new CreateImagePlayer();
        createImagePlayer.selectImage("aaaa");
    }

}
