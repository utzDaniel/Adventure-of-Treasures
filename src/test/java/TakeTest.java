import rules.exception.InventoryException;
import backend.model.Coordinate;
import backend.model.Player;
import backend.model.builder.item.Item;
import backend.model.builder.item.ItemUsableBuilder;
import backend.model.builder.map.MapGame;
import rules.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import backend.repository.RepositoryFactory;
import rules.interfaces.ICoordinate;
import rules.service.Take;

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
        mapGame.addItem(item, player.getLocation());
        take = new Take();
    }

    @Test
    public void testarItemValidoAFrente() {
        player.setLocation(ICoordinate.getInstance(item.getLocation().getX(), (item.getLocation().getY() - 10)));
        player.setCurrentMap(mapGame);
        assertTrue(take.run());
    }

    @Test(expected = InventoryException.class)
    public void testarItemValidoAFrenteSemCapacitadadeNoInventario() {
        Item item = ItemUsableBuilder.builder().localUse("praia").name("pa1487").description("ferramenta usada para cavar").weight(30)
                .coordinate(ICoordinate.getInstance(320, 320)).image(null).removable(true).visible(true).build();
        player.setLocation(ICoordinate.getInstance(320, 320));
        player.setDirection("oeste");
        mapGame.addItem(item, player.getLocation());
        player.setCurrentMap(mapGame);
        take.run();
    }

    @Test
    public void testarItemInvalidoAFrente() {
        player.setLocation(ICoordinate.getInstance(290, player.getLocation().getY()));
        player.setCurrentMap(mapGame);
        assertFalse(take.run());
    }
}
