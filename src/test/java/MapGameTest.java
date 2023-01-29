import model.Coordinate;
import model.builder.item.Item;
import model.Player;
import model.builder.map.Scenery;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryMapGame;

import static org.junit.Assert.*;

public class MapGameTest {

    private Player player;

    @Before
    public void crearCombination() {
        player = Player.getInstance();
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
    }

    @Test
    public void testarSeOPlayerPodeMoverTRUE(){
        int positionX = 300;
        int positionY = 470;
        Coordinate coordinate = new Coordinate(positionX, positionY);
        assertTrue(player.getCurrentMap().checkLimits(coordinate));
    }

    @Test
    public void testarSeOPlayerPodeMoverFALSE(){
        int positionX = 0;
        int positionY = 0;
        Coordinate coordinate = new Coordinate(positionX, positionY);
        assertFalse(player.getCurrentMap().checkLimits(coordinate));
    }

    @Test
    public void mostrarOsItensVisiveisNoMapaSIZETRUE(){
        Scenery nextScenery = (Scenery) RepositoryMapGame.getInstance().getMapGame("farol");
        player.setCurrentMap(nextScenery);
        assertEquals(1, player.getCurrentMap().getItemVisible().size());
    }

    @Test
    public void mostrarOsItensVisiveisNoMapaSIZEFALSE(){
        Scenery nextScenery = (Scenery) RepositoryMapGame.getInstance().getMapGame("farol");
        player.setCurrentMap(nextScenery);
        assertNotEquals(player.getCurrentMap().getItemVisible().size(), 0);
    }

    @Test
    public void pegarOsItensInVisiveisNoMapaSIZETRUE(){
        Scenery nextScenery = (Scenery) RepositoryMapGame.getInstance().getMapGame("praia");
        player.setCurrentMap(nextScenery);
        assertEquals(player.getCurrentMap().getItemInvisible().size(), 1);
    }

    @Test
    public void pegarOsItensInVisiveisNoMapaSIZEFALSE(){
        Scenery nextScenery = (Scenery) RepositoryMapGame.getInstance().getMapGame("praia");
        player.setCurrentMap(nextScenery);
        assertNotEquals(player.getCurrentMap().getItemInvisible().size(), 0);
    }

}
