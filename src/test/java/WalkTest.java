import exception.MoveException;
import model.Player;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryMapGame;

import static org.junit.Assert.assertEquals;

public class WalkTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
    }

    @Test
    public void andarPlayerParaLeste() {
        int positionX = player.getPositionPlayerX();
        player.walk(Direction.LESTE.getLabel());
        assertEquals(positionX+10, player.getPositionPlayerX());
    }
    @Test
    public void andarPlayerParaOeste() {
        int positionX = player.getPositionPlayerX();
        player.walk(Direction.OESTE.getLabel());
        assertEquals(positionX-10, player.getPositionPlayerX());
    }
    @Test
    public void andarPlayerParaSul() {
        int positionY = player.getPositionPlayerY();
        player.walk(Direction.SUL.getLabel());
        assertEquals(positionY+10, player.getPositionPlayerY());
    }
    @Test
    public void andarPlayerParaNorte() {
        int positionY = player.getPositionPlayerY();
        player.walk(Direction.NORTE.getLabel());
        assertEquals(positionY-10, player.getPositionPlayerY());
    }

    @Test (expected = MoveException.class)
    public void naoAndarPlayerParaNort() {
        int positionY = player.getPositionPlayerY();
        player.walk("nort");
        assertEquals(positionY-10, player.getPositionPlayerY());
    }

    @Test
    public void naoAndarPlayer() {
        player.setPositionPlayerX(230);
        player.setPositionPlayerY(530);
        int positionY = player.getPositionPlayerY();
        player.walk(Direction.SUL.getLabel());
        assertEquals(positionY, player.getPositionPlayerY());
    }

}
