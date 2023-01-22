import exception.CreateImageException;
import org.junit.Test;
import repository.CreateImageMapGame;

public class CreateTest {

    @Test(expected = CreateImageException.class)
    public void createImageMapGame() {
        CreateImageMapGame createImageMapGame = new CreateImageMapGame();
        createImageMapGame.selectImage("aaaa");
    }

}
