import model.Player;
import org.junit.Before;
import org.junit.Test;
import settings.SettingsPlayer;
import view.InterfaceGame;
import view.InterfaceInventory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InterfaceInventoryTest {

    private final Player player = new Player();
    private final InterfaceGame interfaceGame = new InterfaceGame(new SettingsPlayer().ImageInitial());
    private InterfaceInventory interfaceInventory;

    @Before
    public void create() {
        interfaceInventory = new InterfaceInventory(interfaceGame, player);
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
