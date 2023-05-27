import rules.exception.InventoryException;
import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.builder.item.ItemEquipableBuilder;
import backend.model.builder.item.ItemUsableBuilder;
import rules.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import backend.repository.RepositoryFactory;
import rules.interfaces.ICoordinate;

import static org.junit.Assert.*;

public class TakeItemTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        var createMapGame = RepositoryFactory.getRepositoryMapGame();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem()
                .getAll().stream()
                .filter(Item::isInvisible)
                .filter(item -> !item.getName().equals("chave"))
                .toList()) {
            player.getInventory().setItemInvisible(item);
        }
    }

    @Test
    public void testarPegarItem() {
        int size = player.getInventory().getItemVisible().size();
        player.getInventory().addItem(ItemEquipableBuilder.builder().equipped(false).name("mochila878").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650,220)).image(null).removable(true).visible(true).build());
        player.takeItem(ItemUsableBuilder.builder().localUse("praia").name("pa8478").description("ferramenta usada para cavar").weight(0)
                .coordinate(ICoordinate.getInstance(200,280)).image(null).removable(true).visible(true).build());
        assertEquals(size+2,player.getInventory().getItemVisible().size());
    }

    @Test
    public void validarCapacidadeMaximaDoInventory() {
        player.getInventory().addItem(ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650,220)).image(null).removable(true).visible(true).build());
        assertTrue(player.takeItem(ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .coordinate(ICoordinate.getInstance(200,280)).image(null).removable(true).visible(true).build()));
    }

    @Test(expected = InventoryException.class)
    public void invalidarCapacidadeMaximaDoInventory() {
        Item item = ItemUsableBuilder.builder().localUse("praia").name("12345").description("ferramenta usada para cavar").weight(30)
                .coordinate(ICoordinate.getInstance(200,280)).image(null).removable(true).visible(true).build();
        player.takeItem(item);
    }
}
