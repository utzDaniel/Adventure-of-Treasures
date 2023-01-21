import exception.InventoryException;
import model.*;
import model.builder.item.Item;
import model.builder.item.ItemUsable;
import model.builder.item.ItemUsableBuilder;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;
import service.Take;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TakeTest {

    private Take take;
    private Player player;
    private Item item;

    @Before
    public void inicialize() {
        CreateMapGame createMapGame = new CreateMapGame();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        item = ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .positionX(300).positionY(480).image(null).removable(true).visible(true).build();
        player.getCurrentMap().setItem(item);
        take = new Take();
    }

    @Test
    public void testarItemValidoAFrente() {
        player.setPositionPlayerX(item.getPositionX());
        player.setPositionPlayerY(item.getPositionY()-10);
        assertTrue(take.run());
    }

    @Test (expected = InventoryException.class)
    public void testarItemValidoAFrenteSemCapacitadadeNoInventario() {
        Item item = ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(30)
                .positionX(290).positionY(470).image(null).removable(true).visible(true).build();
        player.setPositionPlayerX(item.getPositionX());
        player.setPositionPlayerY(item.getPositionY()-10);
        player.getCurrentMap().setItem(item);
        take.run();
    }

    @Test
    public void testarItemInvalidoAFrente() {
        player.setPositionPlayerX(290);
        assertFalse(take.run());
    }
}
