import backend.model.Player;
import backend.model.builder.item.Item;
import frontend.model.component.JLabelFactory;
import org.junit.Before;
import org.junit.Test;
import backend.repository.RepositoryFactory;
import frontend.view.InterfaceGame;
import frontend.view.InterfaceInventory;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InterfaceInventoryTest {

    private Player player;
    private InterfaceGame interfaceGame;
    private InterfaceInventory interfaceInventory;

    @Before
    public void create() {
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
        var listJLabel = new ArrayList<JLabel>();
        listJLabel.add(JLabelFactory.getInstance(player));
        listJLabel.add(JLabelFactory.getInstance(player.getCurrentMap()));
        interfaceGame = new InterfaceGame(listJLabel);
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
