import model.Coordinate;
import model.Player;
import model.builder.item.Item;
import model.builder.item.ItemCombinableBuilder;
import model.builder.item.ItemEquipableBuilder;
import model.builder.item.ItemUsableBuilder;
import model.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryFactory;
import repository.RepositoryItem;
import repository.RepositoryMapGame;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
        RepositoryMapGame createMapGame = RepositoryFactory.getRepositoryMapGame();
        player = Player.getInstance();
        player.setDirection(Direction.SUL.getLabel());
        player.setCurrentMap(createMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem().getItemInvisible()) {
            player.getInventory().setItemInvisible(item);
        }
    }

    @Test
    public void testarPegarItem() {
        int size = player.getInventory().getItemVisible().size();
        player.getInventory().addItem(ItemEquipableBuilder.builder().equipped(false).name("asqe").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(new Coordinate(650, 220)).image(null).removable(true).visible(true).build());

        player.takeItem(ItemUsableBuilder.builder().localUse("praia").name("aq1a4e").description("ferramenta usada para cavar").weight(0)
                .coordinate(new Coordinate(200, 280)).image(null).removable(true).visible(true).build());

        assertEquals(player.getInventory().getItemVisible().size(), size + 2);
    }

    @Test
    public void validarCapacidadeMaximaDoInventory() {
        assertTrue(player.takeItem(ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .coordinate(new Coordinate(200, 280)).image(null).removable(true).visible(true).build()));
    }


    @Test
    public void buscarPeloNomeDoItem() {
        player.getInventory().addItem(ItemEquipableBuilder.builder().equipped(false).name("mochila22").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(new Coordinate(650, 220)).image(null).removable(true).visible(true).build());
        assertNotNull(player.getInventory().getItem("mochila22"));
    }

    @Test
    public void nullBuscarPeloNomeDoItem() {
        assertNull(player.getInventory().getItem("adas"));
    }

    @Test
    public void buscarListaDeItensVisivelNoInventario() {
        int size = player.getInventory().getItemVisible().size();
        player.getInventory().addItem(ItemEquipableBuilder.builder().equipped(false).name("m1154h").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(new Coordinate(650, 220)).image(null).removable(true).visible(true).build());
        assertEquals(size + 1, player.getInventory().getItemVisible().size());
    }

    @Test
    public void buscarListaDeItensNãoVisivelNoInventario() {
        assertEquals(player.getInventory().getItemInvisible().size(), 3);
    }

    @Test
    public void atualizarOTamanhoDoInventarioAoAdicionarItemNovo() {
        Item item = ItemCombinableBuilder.builder().combine(3).name("faca").description("serve para cortar algo").weight(1)
                .coordinate(new Coordinate(420, 130)).image(null).removable(true).visible(true).build();
        int tamanhoAnterior = player.getInventory().getCapacity();
        player.getInventory().updadeCapacity(item.getWeight());
        assertNotEquals(tamanhoAnterior, player.getInventory().getCapacity());
    }

}
