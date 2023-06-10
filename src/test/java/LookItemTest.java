import backend.exception.MoveException;
import backend.service.model.Player;
import backend.service.model.builder.Scenery;
import backend.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import backend.repository.factory.RepositoryFactory;
import backend.controller.interfaces.ICoordinate;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LookItemTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(repositoryMapGame.get("cais"));
    }

    @Test(expected = MoveException.class)
    public void testarDirectionInvalida() {
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setLocation(ICoordinate.getInstance(210, 280));
        player.setDirection("oest");
        player.lookItem();
    }

    @Test
    public void testarSePossuiItemNaFrenteDoPlayerNOTNULL() {
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setDirection(Direction.OESTE.getLabel());
        player.setLocation(ICoordinate.getInstance(210, 280));
        assertNotNull(player.lookItem());
    }

    @Test
    public void testarSePossuiItemNaFrenteDoPlayerNULL() {
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setDirection(Direction.OESTE.getLabel());
        player.setLocation(ICoordinate.getInstance(210, 270));
        assertNull(player.lookItem());
    }

}
