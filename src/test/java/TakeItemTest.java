import exception.InventoryException;
import model.*;
import model.enums.Direction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;

import java.util.Objects;

import static org.junit.Assert.*;

public class TakeItemTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        CreateMapGame createMapGame = new CreateMapGame();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
    }

    @Test
    public void testarPegarItem() {
        int size = player.getInventory().getItemVisible().size();
        player.getInventory().addItem(new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0,
                650, 220, null));
        player.takeItem(new ItemUsable("pa", "ferramenta usada para cavar", 0, "praia",
                200, 280, null));
        assertEquals(player.getInventory().getItemVisible().size(), size+2);
    }

    @Test
    public void validarCapacidadeMaximaDoInventory() {
        player.getInventory().addItem(new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0,
                650, 220, null));
        assertTrue(player.takeItem(new ItemUsable("pa", "ferramenta usada para cavar", 0,
                "praia", 200, 280, null)));
    }

    @Test(expected = InventoryException.class)
    public void invalidarCapacidadeMaximaDoInventory() {
        Item item = new ItemUsable("12345", "ferramenta usada para cavar", 30, "praia",
                200, 280, null);
        player.takeItem(item);
    }
}
