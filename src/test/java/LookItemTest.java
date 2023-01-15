import exception.MoveException;
import model.*;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;

import javax.swing.*;

import static org.junit.Assert.*;

public class LookItemTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        CreateMapGame createMapGame = new CreateMapGame();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
    }

    @Test (expected = MoveException.class)
    public void testarDirectionInvalida(){
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setDirection("oest");
        player.setPositionPlayerX(210);
        player.setPositionPlayerY(280);
        player.lookItem();
    }

    @Test
    public void testarSePossuiItemNaFrenteDoPlayerNOTNULL(){
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setDirection(Direction.OESTE.getLabel());
        player.setPositionPlayerX(210);
        player.setPositionPlayerY(280);
        assertNotNull(player.lookItem());
    }

    @Test
    public void testarSePossuiItemNaFrenteDoPlayerNULL(){
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setDirection(Direction.OESTE.getLabel());
        player.setPositionPlayerX(210);
        player.setPositionPlayerY(270);
        assertNull(player.lookItem());
    }

}
