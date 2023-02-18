import exception.MoveException;
import model.Player;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryMapGame;

import java.awt.*;

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
        int positionX = player.getLocation().x;
        player.walk(Direction.LESTE.getLabel());
        assertEquals(positionX+10, player.getLocation().x);
    }
    @Test
    public void andarPlayerParaOeste() {
        int positionX = player.getLocation().x;
        player.walk(Direction.OESTE.getLabel());
        assertEquals(positionX-10, player.getLocation().x);
    }
    @Test
    public void andarPlayerParaSul() {
        int positionY = player.getLocation().y;
        player.walk(Direction.SUL.getLabel());
        assertEquals(positionY+10, player.getLocation().y );
    }
    @Test
    public void andarPlayerParaNorte() {
        int positionY = player.getLocation().y;
        player.walk(Direction.NORTE.getLabel());
        assertEquals(positionY-10, player.getLocation().y );
    }

    @Test (expected = MoveException.class)
    public void naoAndarPlayerParaNort() {
        int positionY = player.getLocation().y;
        player.walk("nort");
        assertEquals(positionY-10, player.getLocation().y );
    }

    @Test
    public void naoAndarPlayer() {
        player.setLocation(new Point(550,530));
        int positionY = player.getLocation().y ;
        player.walk(Direction.SUL.getLabel());
        assertEquals(positionY, player.getLocation().y );
    }

}
