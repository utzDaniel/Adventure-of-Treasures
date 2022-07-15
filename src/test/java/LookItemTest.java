import exception.MoveException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;

import javax.swing.*;

import static org.junit.Assert.*;

public class LookItemTest {

    private Player player;
    private JLabel jLabel = new JLabel();

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        CreateMapGame createMapGame = new CreateMapGame();
        player = new Player();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
    }

    @Test (expected = MoveException.class)
    public void testarDirectionInvalida(){
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setDirection("oest");
        player.setPositionPlayerX(210,jLabel);
        player.setPositionPlayerY(280,jLabel);
        player.lookItem();
    }

    @Test
    public void testarSePossuiItemNaFrenteDoPlayerNOTNULL(){
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setDirection(Direction.OESTE.getLabel());
        player.setPositionPlayerX(210,jLabel);
        player.setPositionPlayerY(280,jLabel);
        assertNotNull(player.lookItem());
    }

    @Test
    public void testarSePossuiItemNaFrenteDoPlayerNULL(){
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setDirection(Direction.OESTE.getLabel());
        player.setPositionPlayerX(210,jLabel);
        player.setPositionPlayerY(270,jLabel);
        assertNull(player.lookItem());
    }

}
