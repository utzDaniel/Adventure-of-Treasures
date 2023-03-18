import exception.MoveException;
import model.Coordinate;
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
        player.setCurrentMap(createMapGame.get("cais"));
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
        player.setCurrentMap( RepositoryMapGame.getInstance().get("cais"));
        player.setLocation(new Coordinate(10, 10));
        int positionY = player.getLocation().getY();
        player.walk(Direction.SUL.getLabel());
        assertEquals(positionY, player.getLocation().getY());
    }

}
