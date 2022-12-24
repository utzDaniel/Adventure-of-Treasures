import exception.InventoryException;
import model.*;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;
import service.Take;

import javax.swing.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TakeTest {

    private Take take;
    private Player player;
    private Item item;

    @Before
    public void inicialize() {
        CreateMapGame createMapGame = new CreateMapGame();
        player = new Player();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        item = new ItemUsable("pa", "ferramenta usada para cavar", 10, "praia",
                300, 480, null);
        player.getCurrentMap().setItem(item);
        take = new Take(player);
    }

    @Test
    public void testarItemValidoAFrente() {
        assertTrue(take.run());
    }

    @Test (expected = InventoryException.class)
    public void testarItemValidoAFrenteSemCapacitadadeNoInventario() {
        player.takeItem(new ItemUsable("tata", "ferramenta usada para cavar", 10, "praia",
                290, 470, null));
        take.run();
    }

    @Test
    public void testarItemInvalidoAFrente() {
        player.setPositionPlayerX(290, new JLabel());
        assertFalse(take.run());
    }
}
