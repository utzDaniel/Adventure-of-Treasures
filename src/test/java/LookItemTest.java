import backend.service.model.Player;
import org.junit.Before;
import org.junit.Test;

public class LookItemTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
//        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
//        player = Player.getInstance();
//        player.setDirection(Direction.SUL.getLabel());
//        player.setCurrentMap(repositoryMapGame.get("cais"));
    }

    @Test//(expected = MoveException.class)
    public void testarDirectionInvalida() {
//        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
//        player.setCurrentMap(nextScenery);
//        player.setLocation(ICoordinate.getInstance(210, 280));
//        player.setDirection("oest");
//        new LookItem(this.player).run();
    }

    @Test
    public void testarSePossuiItemNaFrenteDoPlayerNOTNULL() {
//        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
//        player.setCurrentMap(nextScenery);
//        player.setDirection(Direction.OESTE.getLabel());
//        player.setLocation(ICoordinate.getInstance(210, 280));
//        assertNotNull(new LookItem(this.player).run());
    }

    @Test
    public void testarSePossuiItemNaFrenteDoPlayerNULL() {
//        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
//        player.setCurrentMap(nextScenery);
//        player.setDirection(Direction.OESTE.getLabel());
//        player.setLocation(ICoordinate.getInstance(210, 270));
//        assertNull(new LookItem(this.player).run());
    }

}
