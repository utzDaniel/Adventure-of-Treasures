import backend.service.exception.MoveException;
import backend.service.component.move.MovePlayerScenery;
import backend.service.model.Player;
import frontend.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import backend.repository.factory.RepositoryFactory;
import backend.service.interfaces.ICoordinate;

import static org.junit.Assert.assertEquals;

public class MovePlayerTest {

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
        int positionX = player.getLocation().x();
        new MovePlayerScenery(this.player, Direction.LESTE.getLabel()).run();
        assertEquals(positionX + 10, player.getLocation().x());
    }

    @Test
    public void andarPlayerParaOeste() {
        int positionX = player.getLocation().x();
        new MovePlayerScenery(this.player, Direction.OESTE.getLabel()).run();
        assertEquals(positionX - 10, player.getLocation().x());
    }

    @Test
    public void andarPlayerParaSul() {
        int positionY = player.getLocation().y();
        new MovePlayerScenery(this.player, Direction.SUL.getLabel()).run();
        assertEquals(positionY + 10, player.getLocation().y());
    }

    @Test
    public void andarPlayerParaNorte() {
        int positionY = player.getLocation().y();
        new MovePlayerScenery(this.player, Direction.NORTE.getLabel()).run();
        assertEquals(positionY - 10, player.getLocation().y());
    }

    @Test(expected = MoveException.class)
    public void naoAndarPlayerParaNort() {
        int positionY = player.getLocation().y();
        new MovePlayerScenery(this.player, "nort").run();
        assertEquals(positionY - 10, player.getLocation().y());
    }

    @Test
    public void naoAndarPlayer() {
        player.setCurrentMap(RepositoryFactory.getRepositoryMapGame().get("cais"));
        player.setLocation(ICoordinate.getInstance(10, 10));
        int positionY = player.getLocation().y();
        new MovePlayerScenery(this.player, Direction.SUL.getLabel()).run();
        assertEquals(positionY, player.getLocation().y());
    }

}
