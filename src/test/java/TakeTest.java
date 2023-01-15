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
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        item = new ItemUsable("pa", "ferramenta usada para cavar", 0, "praia",
                300, 480, null);
        player.getCurrentMap().setItem(item);
        take = new Take();
    }

    @Test
    public void testarItemValidoAFrente() {
        player.setPositionPlayerX(item.getPositionItemX());
        player.setPositionPlayerY(item.getPositionItemY()-10);
        assertTrue(take.run());
    }

    @Test (expected = InventoryException.class)
    public void testarItemValidoAFrenteSemCapacitadadeNoInventario() {
        Item item = new ItemUsable("tata", "ferramenta usada para cavar", 30, "praia",
                290, 470, null);
        player.setPositionPlayerX(item.getPositionItemX());
        player.setPositionPlayerY(item.getPositionItemY()-10);
        player.getCurrentMap().setItem(item);
        take.run();
    }

    @Test
    public void testarItemInvalidoAFrente() {
        player.setPositionPlayerX(290);
        assertFalse(take.run());
    }
}
