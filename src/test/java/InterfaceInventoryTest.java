import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.repository.factory.RepositoryFactory;
import frontend.model.component.ComponentFactory;
import frontend.service.InterfaceGame;
import frontend.service.InterfaceInventory;
import org.junit.Before;
import org.junit.Test;

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
        listJLabel.add(ComponentFactory.getJLabel(this.player.getIcon().toString(), this.player.getLocation())));
        //listJLabel.add(ComponentFactory.getJLabel(player.getCurrentMap()));
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
