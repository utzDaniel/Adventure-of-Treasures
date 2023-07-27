import backend.service.exception.InventoryException;
import backend.service.component.drop.AddItemMapGame;
import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.service.model.builder.ItemUsableBuilder;
import backend.service.model.builder.MapGame;
import frontend.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import backend.repository.factory.RepositoryFactory;
import backend.service.interfaces.ICoordinate;
import backend.service.component.take.Take;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TakeTest {

    private Take take;
    private Player player;
    private MapGame mapGame;
    private Item item;

    @Before
    public void inicialize() {
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
        item = ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .coordinate(ICoordinate.getInstance(340, 340)).image(null).removable(true).visible(true).build();
        mapGame = player.getCurrentMap();
        var mapGame = this.player.getCurrentMap();
        var coordinate = this.player.getLocation();
        if (new AddItemMapGame(mapGame, item, coordinate).run()) {
            mapGame.addItem(item);
        }

        take = new Take(player);
    }

    @Test
    public void testarItemValidoAFrente() {
        player.setLocation(ICoordinate.getInstance(item.getLocation().x(), (item.getLocation().y() - 10)));
        player.setCurrentMap(mapGame);
        assertTrue(take.run().isSucess());
    }

    @Test(expected = InventoryException.class)
    public void testarItemValidoAFrenteSemCapacitadadeNoInventario() {
        Item item = ItemUsableBuilder.builder().localUse("praia").name("pa1487").description("ferramenta usada para cavar").weight(30)
                .coordinate(ICoordinate.getInstance(320, 320)).image(null).removable(true).visible(true).build();
        player.setLocation(ICoordinate.getInstance(320, 320));
        player.setDirection("oeste");
        var mapGame = this.player.getCurrentMap();
        var coordinate = this.player.getLocation();
        if (new AddItemMapGame(mapGame, item, coordinate).run()) {
            mapGame.addItem(item);
        }
        player.setCurrentMap(mapGame);
        take.run();
    }

    @Test
    public void testarItemInvalidoAFrente() {
        player.setLocation(ICoordinate.getInstance(290, player.getLocation().y()));
        player.setCurrentMap(mapGame);
        assertFalse(take.run().isSucess());
    }
}
