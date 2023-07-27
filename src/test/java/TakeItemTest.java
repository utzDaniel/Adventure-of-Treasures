import backend.service.exception.InventoryException;
import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.service.model.builder.ItemEquipableBuilder;
import backend.service.model.builder.ItemUsableBuilder;
import frontend.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import backend.repository.factory.RepositoryFactory;
import backend.service.interfaces.ICoordinate;

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
        var item1 = ItemEquipableBuilder.builder().equipped(false).name("mochila878").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650,220)).image(null).removable(true).visible(true).build();
        new AddItemInventory(player.getInventory(), item1).run();
        var item = ItemUsableBuilder.builder().localUse("praia").name("pa8478").description("ferramenta usada para cavar").weight(0)
                .coordinate(ICoordinate.getInstance(200,280)).image(null).removable(true).visible(true).build();
        new TakeItem(this.player, item).run();

        assertEquals(size+2,player.getInventory().getItemVisible().size());
    }

    @Test
    public void validarCapacidadeMaximaDoInventory() {
        var item1 = ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650,220)).image(null).removable(true).visible(true).build();
        new AddItemInventory(player.getInventory(), item1).run();
        var item =ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .coordinate(ICoordinate.getInstance(200,280)).image(null).removable(true).visible(true).build();
        assertTrue(new TakeItem(this.player, item).run());
    }

    @Test(expected = InventoryException.class)
    public void invalidarCapacidadeMaximaDoInventory() {
        Item item = ItemUsableBuilder.builder().localUse("praia").name("12345").description("ferramenta usada para cavar").weight(30)
                .coordinate(ICoordinate.getInstance(200,280)).image(null).removable(true).visible(true).build();
        new TakeItem(this.player, item).run();
    }
}
