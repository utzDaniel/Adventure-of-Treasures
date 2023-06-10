import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.service.model.builder.ItemCombinableBuilder;
import backend.service.model.builder.ItemEquipableBuilder;
import backend.service.model.builder.ItemUsableBuilder;
import backend.enums.Direction;
import org.junit.Before;
import org.junit.Test;
import backend.repository.factory.RepositoryFactory;
import backend.controller.interfaces.ICoordinate;

import static org.junit.Assert.*;

public class PlayerTest {

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
    }

    @Test
    public void testarPegarItem() {
        int size = player.getInventory().getItemVisible().size();
        player.getInventory().addItem(ItemEquipableBuilder.builder().equipped(false).name("asqe").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650, 220)).image(null).removable(true).visible(true).build());

        player.takeItem(ItemUsableBuilder.builder().localUse("praia").name("aq1a4e").description("ferramenta usada para cavar").weight(0)
                .coordinate(ICoordinate.getInstance(200, 280)).image(null).removable(true).visible(true).build());

        assertEquals(player.getInventory().getItemVisible().size(), size + 2);
    }

    @Test
    public void validarCapacidadeMaximaDoInventory() {
        assertTrue(player.takeItem(ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .coordinate(ICoordinate.getInstance(200, 280)).image(null).removable(true).visible(true).build()));
    }


    @Test
    public void buscarPeloNomeDoItem() {
        player.getInventory().addItem(ItemEquipableBuilder.builder().equipped(false).name("mochila22").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650, 220)).image(null).removable(true).visible(true).build());
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
                .coordinate(ICoordinate.getInstance(650, 220)).image(null).removable(true).visible(true).build());
        assertEquals(size + 1, player.getInventory().getItemVisible().size());
    }

    @Test
    public void buscarListaDeItensNãoVisivelNoInventario() {
        assertEquals(4, player.getInventory().getItemInvisible().size());
    }

    @Test
    public void atualizarOTamanhoDoInventarioAoAdicionarItemNovo() {
        Item item = ItemCombinableBuilder.builder().combine(3).name("faca").description("serve para cortar algo").weight(1)
                .coordinate(ICoordinate.getInstance(420, 130)).image(null).removable(true).visible(true).build();
        int tamanhoAnterior = player.getInventory().getCapacity();
        player.getInventory().updadeCapacity(item.getWeight());
        assertNotEquals(tamanhoAnterior, player.getInventory().getCapacity());
    }

}
