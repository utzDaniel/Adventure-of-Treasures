import exception.InventoryException;
import model.Coordinate;
import model.Player;
import model.builder.item.Item;
import model.builder.item.ItemUsableBuilder;
import model.builder.map.MapGame;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryFactory;
import repository.RepositoryItem;
import repository.RepositoryMapGame;
import service.Take;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TakeTest {

    private Take take;
    private Player player;
    private MapGame mapGame;
    private Item item;

    @Before
    public void inicialize() {
        RepositoryMapGame createMapGame = RepositoryFactory.getRepositoryMapGame();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem().getItemInvisible()) {
            player.getInventory().setItemInvisible(item);
        }
        item = ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .coordinate(new Coordinate(340, 340)).image(null).removable(true).visible(true).build();
        mapGame = player.getCurrentMap();
        mapGame.addItem(item, player.getLocation());
        take = new Take();
    }

    @Test
    public void testarItemValidoAFrente() {
        player.setLocation(new Coordinate(item.getLocation().getX(), (item.getLocation().getY() - 10)));
        player.setCurrentMap(mapGame);
        assertTrue(take.run());
    }

    @Test(expected = InventoryException.class)
    public void testarItemValidoAFrenteSemCapacitadadeNoInventario() {
        Item item = ItemUsableBuilder.builder().localUse("praia").name("pa1487").description("ferramenta usada para cavar").weight(30)
                .coordinate(new Coordinate(320, 320)).image(null).removable(true).visible(true).build();
        player.setLocation(new Coordinate(320, 320));
        player.setDirection("oeste");
        mapGame.addItem(item, player.getLocation());
        player.setCurrentMap(mapGame);
        take.run();
        System.out.println(player.getLocation());
        System.out.println(mapGame.getItem(new Coordinate(310,320)));
    }

    @Test
    public void testarItemInvalidoAFrente() {
        player.setLocation(new Coordinate(290, player.getLocation().getY()));
        player.setCurrentMap(mapGame);
        assertFalse(take.run());
    }
}
