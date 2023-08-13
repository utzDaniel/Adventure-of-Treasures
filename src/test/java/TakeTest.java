import backend.service.component.drop.AddItemMapGame;
import backend.service.component.take.Take;
import backend.service.enums.Move;
import backend.service.interfaces.ICoordinate;
import backend.service.model.Player;
import backend.service.model.Item;
import backend.service.model.MapGame;
import org.junit.Before;
import org.junit.Test;

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
        player.setM(Move.SUL);
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
        var coordinate = this.player.getCoordinate();
        if (new AddItemMapGame(mapGame, item, coordinate).run()) {
            mapGame.addItem(item);
        }

        take = new Take(player);
    }

    @Test
    public void testarItemValidoAFrente() {
        player.setCoordinate(ICoordinate.getInstance(item.getCoordinate().x(), (item.getCoordinate().y() - 10)));
        player.setCurrentMap(mapGame);
        assertTrue(take.run().isSucess());
    }

    @Test //InventoryException.class
    public void testarItemValidoAFrenteSemCapacitadadeNoInventario() {
        Item item = ItemUsableBuilder.builder().localUse("praia").name("pa1487").description("ferramenta usada para cavar").weight(30)
                .coordinate(ICoordinate.getInstance(320, 320)).image(null).removable(true).visible(true).build();
        player.setCoordinate(ICoordinate.getInstance(320, 320));
        player.setM(Move.OESTE);
        var mapGame = this.player.getCurrentMap();
        var coordinate = this.player.getCoordinate();
        if (new AddItemMapGame(mapGame, item, coordinate).run()) {
            mapGame.addItem(item);
        }
        player.setCurrentMap(mapGame);
        take.run();
    }

    @Test
    public void testarItemInvalidoAFrente() {
        player.setCoordinate(ICoordinate.getInstance(290, player.getCoordinate().y()));
        player.setCurrentMap(mapGame);
        assertFalse(take.run().isSucess());
    }
}
