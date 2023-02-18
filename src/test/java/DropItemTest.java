import exception.InventoryException;
import model.*;
import model.builder.item.*;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryMapGame;

import java.awt.*;

import static org.junit.Assert.*;

public class DropItemTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .point(new Point(650,220)).image(null).removable(true).visible(true).build());
    }

    @Test
    public void removerItemPorItemDentroDoInventario() {
        Item item = ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .point(new Point(200,280)).image(null).removable(true).visible(true).build();
        player.takeItem(item);
        assertTrue(player.dropItem(item));
    }

    @Test(expected = InventoryException.class)
    public void naoRemoverItemNotRemovePorItemDentroDoInventario() {
        Item item = ItemMissionBuilder.builder().mapGame("barco").name("tesouro").description("tesouro lendário dos templários").weight(0)
                .point(new Point(620,240)).image(null).removable(false).build();
        player.takeItem(item);
        player.dropItem(item);
    }
}
