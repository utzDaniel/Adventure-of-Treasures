import backend.model.Coordinate;
import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.builder.map.Scenery;
import org.junit.Before;
import org.junit.Test;
import backend.repository.RepositoryFactory;
import rules.interfaces.ICoordinate;

import static org.junit.Assert.*;

public class MapGameTest {

    private Player player;

    @Before
    public void crearCombination() {
        player = Player.getInstance();
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player.setCurrentMap(repositoryMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem()
                .getAll().stream()
                .filter(Item::isInvisible)
                .filter(item -> !item.getName().equals("chave"))
                .toList()) {
            player.getInventory().setItemInvisible(item);
        }
    }

    @Test
    public void testarSeOPlayerPodeMoverTRUE(){
        ICoordinate coordinate = ICoordinate.getInstance(300, 470);
        assertTrue(player.getCurrentMap().checkLimits(coordinate));
    }

    @Test
    public void testarSeOPlayerPodeMoverFALSE(){
        ICoordinate coordinate = ICoordinate.getInstance(0, 0);
        assertFalse(player.getCurrentMap().checkLimits(coordinate));
    }

    @Test
    public void mostrarOsItensVisiveisNoMapaSIZETRUE(){
        Scenery nextScenery = (Scenery) RepositoryFactory.getRepositoryMapGame().get("farol");
        player.setCurrentMap(nextScenery);
        assertEquals(1, player.getCurrentMap().getItemVisible().size());
    }

    @Test
    public void mostrarOsItensVisiveisNoMapaSIZEFALSE(){
        Scenery nextScenery = (Scenery) RepositoryFactory.getRepositoryMapGame().get("farol");
        player.setCurrentMap(nextScenery);
        assertNotEquals(player.getCurrentMap().getItemVisible().size(), 0);
    }

    @Test
    public void pegarOsItensInVisiveisNoMapaSIZETRUE(){
        Scenery nextScenery = (Scenery) RepositoryFactory.getRepositoryMapGame().get("praia");
        player.setCurrentMap(nextScenery);
        assertEquals(player.getCurrentMap().getItemInvisible().size(), 1);
    }

    @Test
    public void pegarOsItensInVisiveisNoMapaSIZEFALSE(){
        Scenery nextScenery = (Scenery) RepositoryFactory.getRepositoryMapGame().get("praia");
        player.setCurrentMap(nextScenery);
        assertNotEquals(player.getCurrentMap().getItemInvisible().size(), 0);
    }

}
