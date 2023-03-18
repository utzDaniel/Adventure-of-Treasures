import exception.MoveException;
import model.Coordinate;
import model.Player;
import model.builder.map.Scenery;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryMapGame;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class LookItemTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.get("cais"));
    }

    @Test(expected = MoveException.class)
    public void testarDirectionInvalida() {
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setLocation(new Coordinate(210, 280));
        player.setDirection("oest");
        player.lookItem();
    }

    @Test
    public void testarSePossuiItemNaFrenteDoPlayerNOTNULL() {
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setDirection(Direction.OESTE.getLabel());
        player.setLocation(new Coordinate(210, 280));
        assertNotNull(player.lookItem());
    }

    @Test
    public void testarSePossuiItemNaFrenteDoPlayerNULL() {
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setDirection(Direction.OESTE.getLabel());
        player.setLocation(new Coordinate(210, 270));
        assertNull(player.lookItem());
    }

}
