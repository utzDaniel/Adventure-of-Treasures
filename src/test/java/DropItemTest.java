import exception.InventoryException;
import model.*;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;

import static org.junit.Assert.*;

public class DropItemTest {

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
        player.getInventory().addItem(new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0,
                650, 220, null));
    }

    @Test
    public void removerItemPorItemDentroDoInventario() {
        Item item = new ItemUsable("pa", "ferramenta usada para cavar", 0, "praia",
                200, 280, null);
        player.takeItem(item);
        assertTrue(player.dropItem(item));
    }

    @Test (expected = InventoryException.class)
    public void naoRemoverItemNotRemovePorItemDentroDoInventario() {
        Item item = new ItemNotRemove("tesouro", "tesouro lendário dos templários", null, 0, 620, 240, null);
        player.takeItem(item);
        player.dropItem(item);
    }
}
