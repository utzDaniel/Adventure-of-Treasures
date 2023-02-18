import exception.InventoryException;
import model.Player;
import model.builder.item.Item;
import model.builder.item.ItemUsableBuilder;
import model.builder.map.MapGame;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryMapGame;
import service.Take;

import java.awt.*;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TakeTest {

    private Take take;
    private Player player;
    private MapGame mapGame;
    private Item item;

    @Before
    public void inicialize() {
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        item = ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .point(new Point(300,480)).image(null).removable(true).visible(true).build();
        mapGame = player.getCurrentMap();
        mapGame.addItem(item);
        take = new Take();
    }

    @Test
    public void testarItemValidoAFrente() {
        player.setLocation(new Point(item.getLocation().x,(item.getLocation().y-10)));
        player.setCurrentMap(mapGame);
        assertTrue(take.run());
    }

    @Test (expected = InventoryException.class)
    public void testarItemValidoAFrenteSemCapacitadadeNoInventario() {
        Item item = ItemUsableBuilder.builder().localUse("praia").name("pa1").description("ferramenta usada para cavar").weight(30)
                .point(new Point(300,470)).image(null).removable(true).visible(true).build();
        player.setLocation(new Point(item.getLocation().x,(item.getLocation().y-10)));
        player.setDirection("oeste");
        mapGame.addItem(item);
        player.setCurrentMap(mapGame);
        take.run();
    }

    @Test
    public void testarItemInvalidoAFrente() {
        player.setLocation(new Point(290,player.getLocation().y ));
        player.setCurrentMap(mapGame);
        assertFalse(take.run());
    }
}
