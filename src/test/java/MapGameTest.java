import model.Item;
import model.Player;
import model.Scenery;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class MapGameTest {

    private Player player;
    private JLabel jLabel = new JLabel();
    private final ArrayList<Item> item = new ArrayList<>();

    @Before
    public void crearCombination() {
        player = new Player();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().addItem(item);
        }
    }

    @Test
    public void testarSeOPlayerPodeMoverTRUE(){
        int positionX = 300;
        int positionY = 470;
        assertTrue(player.getCurrentMap().mapGameLimits(positionX,positionY));
    }

    @Test
    public void testarSeOPlayerPodeMoverFALSE(){
        int positionX = 0;
        int positionY = 0;
        assertFalse(player.getCurrentMap().mapGameLimits(positionX,positionY));
    }

    @Test
    public void pegarOsItensVisiveisNoMapaSIZETRUE(){
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        assertEquals(player.getCurrentMap().getItemVisible().size(), 1);
    }

    @Test
    public void pegarOsItensVisiveisNoMapaSIZEFALSE(){
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        assertNotEquals(player.getCurrentMap().getItemVisible().size(), 0);
    }

    @Test
    public void pegarOsItensInVisiveisNoMapaSIZETRUE(){
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("leste");
        player.setCurrentMap(nextScenery);
        assertEquals(player.getCurrentMap().getItemInvisible().size(), 1);
    }

    @Test
    public void pegarOsItensInVisiveisNoMapaSIZEFALSE(){
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("leste");
        player.setCurrentMap(nextScenery);
        assertNotEquals(player.getCurrentMap().getItemInvisible().size(), 0);
    }


    @Test
    public void testarSePossuiItemNaFrenteDoPlayerNOTNULL(){
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setPositionPlayerX(200,jLabel);
        player.setPositionPlayerY(280,jLabel);
        assertNotNull(player.getCurrentMap().getItemMapGame(player.getPositionPlayerX(), player.getPositionPlayerY()));
    }

    @Test
    public void testarSePossuiItemNaFrenteDoPlayerNULL(){
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit("oeste");
        player.setCurrentMap(nextScenery);
        player.setPositionPlayerX(200,jLabel);
        player.setPositionPlayerY(270,jLabel);
        assertNull(player.getCurrentMap().getItemMapGame(player.getPositionPlayerX(), player.getPositionPlayerY()));
    }

}
