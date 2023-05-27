import rules.exception.InventoryException;
import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.builder.item.ItemEquipableBuilder;
import backend.model.builder.item.ItemMissionBuilder;
import backend.model.builder.item.ItemUsableBuilder;
import rules.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import backend.repository.RepositoryFactory;
import rules.interfaces.ICoordinate;

import static org.junit.Assert.*;

public class DropItemTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(repositoryMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem()
                .getAll().stream()
                .filter(Item::isInvisible)
                .filter(item -> !item.getName().equals("chave"))
                .toList()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650,220)).image(null).removable(true).visible(true).build());
    }

    @Test
    public void removerItemPorItemDentroDoInventario() {
        Item item = ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .coordinate(ICoordinate.getInstance(200,280)).image(null).removable(true).visible(true).build();
        player.takeItem(item);
        assertTrue(player.dropItem(item));
    }

    @Test(expected = InventoryException.class)
    public void naoRemoverItemNotRemovePorItemDentroDoInventario() {
        Item item = ItemMissionBuilder.builder().mapGame("barco").name("tesouro").description("tesouro lendário dos templários").weight(0)
                .coordinate(ICoordinate.getInstance(620,240)).image(null).removable(false).build();
        player.takeItem(item);
        player.dropItem(item);
    }
}
