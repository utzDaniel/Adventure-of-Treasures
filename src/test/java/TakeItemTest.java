import exception.InventoryException;
import model.*;
import model.builder.item.*;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryMapGame;

import static org.junit.Assert.*;

public class TakeItemTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
    }

    @Test
    public void testarPegarItem() {
        int size = player.getInventory().getItemVisible().size();
        player.getInventory().addItem(ItemEquipableBuilder.builder().equipped(false).name("mochila878").description("utilizada para carregar mais coisas").weight(0)
                .positionX(650).positionY(220).image(null).removable(true).visible(true).build());
        player.takeItem(ItemUsableBuilder.builder().localUse("praia").name("pa8478").description("ferramenta usada para cavar").weight(0)
                .positionX(200).positionY(280).image(null).removable(true).visible(true).build());
        assertEquals(size+2,player.getInventory().getItemVisible().size());
    }

    @Test
    public void validarCapacidadeMaximaDoInventory() {
        player.getInventory().addItem(ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .positionX(650).positionY(220).image(null).removable(true).visible(true).build());
        assertTrue(player.takeItem(ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .positionX(200).positionY(280).image(null).removable(true).visible(true).build()));
    }

    @Test(expected = InventoryException.class)
    public void invalidarCapacidadeMaximaDoInventory() {
        Item item = ItemUsableBuilder.builder().localUse("praia").name("12345").description("ferramenta usada para cavar").weight(30)
                .positionX(200).positionY(280).image(null).removable(true).visible(true).build();
        player.takeItem(item);
    }
}
