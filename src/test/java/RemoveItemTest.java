import backend.service.exception.InventoryException;
import backend.service.component.drop.AddItemMapGame;
import backend.service.component.drop.ServiceDropItem;
import backend.service.component.take.TakeItem;
import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.service.model.builder.ItemEquipableBuilder;
import backend.service.model.builder.ItemMissionBuilder;
import backend.service.model.builder.ItemUsableBuilder;
import frontend.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import backend.repository.factory.RepositoryFactory;
import backend.service.interfaces.ICoordinate;

import static org.junit.Assert.assertTrue;

public class RemoveItemTest {

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
        var item = ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650, 220)).image(null).removable(true).visible(true).build();
        new AddItemInventory(player.getInventory(), item).run();
    }

    @Test
    public void removerItemPorItemDentroDoInventario() {
        Item item = ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .coordinate(ICoordinate.getInstance(200, 280)).image(null).removable(true).visible(true).build();
        new TakeItem(this.player, item).run();
        assertTrue(new ServiceDropItem().run(player,item.getName()));
    }

    @Test(expected = InventoryException.class)
    public void naoRemoverItemNotRemovePorItemDentroDoInventario() {
        Item item = ItemMissionBuilder.builder().mapGame("barco").name("tesouro").description("tesouro lendário dos templários").weight(0)
                .coordinate(ICoordinate.getInstance(620, 240)).image(null).removable(false).build();

        var mapGame = this.player.getCurrentMap();
        var coordinate = this.player.getLocation();
        if (new AddItemMapGame(mapGame, item, coordinate).run()) {
            mapGame.addItem(item);
        }
        new TakeItem(this.player, item).run();
        new ServiceDropItem().run(player,item.getName());
    }

}
