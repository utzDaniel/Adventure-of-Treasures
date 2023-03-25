import exception.InventoryException;
import model.Coordinate;
import model.Player;
import model.builder.item.Item;
import model.builder.item.ItemEquipableBuilder;
import model.builder.item.ItemMissionBuilder;
import model.builder.item.ItemUsableBuilder;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryFactory;
import repository.RepositoryItem;
import repository.RepositoryMapGame;

import static org.junit.Assert.*;

public class DropItemTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        RepositoryMapGame createMapGame = RepositoryFactory.getRepositoryMapGame();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem().getItemInvisible()) {
            player.getInventory().setItemInvisible(item);
        }
        player.getInventory().addItem(ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(new Coordinate(650,220)).image(null).removable(true).visible(true).build());
    }

    @Test
    public void removerItemPorItemDentroDoInventario() {
        Item item = ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .coordinate(new Coordinate(200,280)).image(null).removable(true).visible(true).build();
        player.takeItem(item);
        assertTrue(player.dropItem(item));
    }

    @Test(expected = InventoryException.class)
    public void naoRemoverItemNotRemovePorItemDentroDoInventario() {
        Item item = ItemMissionBuilder.builder().mapGame("barco").name("tesouro").description("tesouro lendário dos templários").weight(0)
                .coordinate(new Coordinate(620,240)).image(null).removable(false).build();
        player.takeItem(item);
        player.dropItem(item);
    }
}
