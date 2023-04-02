import rules.exception.MoveException;
import backend.model.Coordinate;
import backend.model.Player;
import rules.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import backend.repository.RepositoryFactory;
import rules.interfaces.ICoordinate;

import static org.junit.Assert.assertEquals;

public class WalkTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(repositoryMapGame.get("cais"));
    }

    @Test
    public void andarPlayerParaLeste() {
        int positionX = player.getLocation().getX();
        player.walk(Direction.LESTE.getLabel());
        assertEquals(positionX + 10, player.getLocation().getX());
    }

    @Test
    public void andarPlayerParaOeste() {
        int positionX = player.getLocation().getX();
        player.walk(Direction.OESTE.getLabel());
        assertEquals(positionX - 10, player.getLocation().getX());
    }

    @Test
    public void andarPlayerParaSul() {
        int positionY = player.getLocation().getY();
        player.walk(Direction.SUL.getLabel());
        assertEquals(positionY + 10, player.getLocation().getY());
    }

    @Test
    public void andarPlayerParaNorte() {
        int positionY = player.getLocation().getY();
        player.walk(Direction.NORTE.getLabel());
        assertEquals(positionY - 10, player.getLocation().getY());
    }

    @Test(expected = MoveException.class)
    public void naoAndarPlayerParaNort() {
        int positionY = player.getLocation().getY();
        player.walk("nort");
        assertEquals(positionY - 10, player.getLocation().getY());
    }

    @Test
    public void naoAndarPlayer() {
        player.setCurrentMap(RepositoryFactory.getRepositoryMapGame().get("cais"));
        player.setLocation(ICoordinate.getInstance(10, 10));
        int positionY = player.getLocation().getY();
        player.walk(Direction.SUL.getLabel());
        assertEquals(positionY, player.getLocation().getY());
    }

}
