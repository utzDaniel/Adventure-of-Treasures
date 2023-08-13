import backend.service.enums.Move;
import backend.service.component.move.MovePlayerScenery;
import backend.service.model.Player;
import org.junit.Before;
import org.junit.Test;
import backend.service.interfaces.ICoordinate;

import static org.junit.Assert.assertEquals;

public class MovePlayerTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player = Player.getInstance();
        player.setM(Move.SUL);
        player.setCurrentMap(repositoryMapGame.get("cais"));
    }

    @Test
    public void andarPlayerParaLeste() {
        int positionX = player.getCoordinate().x();
        new MovePlayerScenery(this.player, Move.LESTE).run();
        assertEquals(positionX + 10, player.getCoordinate().x());
    }

    @Test
    public void andarPlayerParaOeste() {
        int positionX = player.getCoordinate().x();
        new MovePlayerScenery(this.player, Move.OESTE).run();
        assertEquals(positionX - 10, player.getCoordinate().x());
    }

    @Test
    public void andarPlayerParaSul() {
        int positionY = player.getCoordinate().y();
        new MovePlayerScenery(this.player, Move.SUL).run();
        assertEquals(positionY + 10, player.getCoordinate().y());
    }

    @Test
    public void andarPlayerParaNorte() {
        int positionY = player.getCoordinate().y();
        new MovePlayerScenery(this.player, Move.NORTE).run();
        assertEquals(positionY - 10, player.getCoordinate().y());
    }

    @Test
    public void naoAndarPlayer() {
        player.setCurrentMap(RepositoryFactory.getRepositoryMapGame().get("cais"));
        player.setCoordinate(ICoordinate.getInstance(10, 10));
        int positionY = player.getCoordinate().y();
        new MovePlayerScenery(this.player, Move.SUL).run();
        assertEquals(positionY, player.getCoordinate().y());
    }

}
