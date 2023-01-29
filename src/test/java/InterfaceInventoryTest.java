import model.Player;
import model.builder.item.Item;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryMapGame;
import view.InterfaceGame;
import view.InterfaceInventory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InterfaceInventoryTest {

    private Player player;
    private InterfaceGame interfaceGame;
    private InterfaceInventory interfaceInventory;

    @Before
    public void create() {
        player = Player.getInstance();
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        interfaceGame = new InterfaceGame();
        interfaceInventory = new InterfaceInventory(interfaceGame);
    }

    @Test
    public void validInventoryOpen() {
        interfaceInventory.open();
        assertTrue(player.getInventory().openInventory());
    }

    @Test
    public void validInventoryClose() {
        interfaceInventory.open();
        interfaceInventory.quit();
        assertFalse(player.getInventory().openInventory());
    }

}
