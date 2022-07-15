import exception.InventoryException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;

import javax.swing.*;

import static org.junit.Assert.*;

public class TakeItemTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        CreateMapGame createMapGame = new CreateMapGame();
        player = new Player();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0,
                650, 220, null));

    }

    @Test
    public void testarPegarItem() {
        player.takeItem(new ItemUsable("pa", "ferramenta usada para cavar", 3, "praia",
                200, 280, null));
        assertEquals(player.getInventory().getItemVisible().size(), 2);
    }

    @Test
    public void validarCapacidadeMaximaDoInventory() {
        assertTrue(player.takeItem(new ItemUsable("pa", "ferramenta usada para cavar", 3,
                "praia", 200, 280, null)));
    }

    @Test(expected = InventoryException.class)
    public void invalidarCapacidadeMaximaDoInventory() {
        Item item = new ItemUsable("pa", "ferramenta usada para cavar", 3, "praia",
                200, 280, null);
        player.takeItem(item);
        player.takeItem(item);
        player.takeItem(item);
        assertFalse(player.takeItem(item));
    }

}
