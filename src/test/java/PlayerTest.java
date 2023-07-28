import backend.service.enums.Move;
import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.service.model.builder.ItemCombinableBuilder;
import backend.service.model.builder.ItemEquipableBuilder;
import backend.service.model.builder.ItemUsableBuilder;
import org.junit.Before;
import org.junit.Test;
import backend.repository.factory.RepositoryFactory;
import backend.service.interfaces.ICoordinate;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player player;

    @Before
    public void iniciacaoDoPlayerParaTeste() {
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
    }

    @Test
    public void testarPegarItem() {
        int size = player.getInventory().getItemVisible().size();
        var item1 = ItemEquipableBuilder.builder().equipped(false).name("asqe").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650, 220)).image(null).removable(true).visible(true).build();

        new AddItemInventory(player.getInventory(), item1).run();
        var item = ItemUsableBuilder.builder().localUse("praia").name("aq1a4e").description("ferramenta usada para cavar").weight(0)
                .coordinate(ICoordinate.getInstance(200, 280)).image(null).removable(true).visible(true).build();
        new TakeItem(this.player, item).run();
        assertEquals(player.getInventory().getItemVisible().size(), size + 2);
    }

    @Test
    public void validarCapacidadeMaximaDoInventory() {
        var item = ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
        .coordinate(ICoordinate.getInstance(200, 280)).image(null).removable(true).visible(true).build();
        assertTrue(new TakeItem(this.player, item).run());
    }


    @Test
    public void buscarPeloNomeDoItem() {
        var item = ItemEquipableBuilder.builder().equipped(false).name("mochila22").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650, 220)).image(null).removable(true).visible(true).build();
        new AddItemInventory(player.getInventory(), item).run();
        assertNotNull(player.getInventory().getItem("mochila22"));
    }

    @Test
    public void nullBuscarPeloNomeDoItem() {
        assertNull(player.getInventory().getItem("adas"));
    }

    @Test
    public void buscarListaDeItensVisivelNoInventario() {
        int size = player.getInventory().getItemVisible().size();
        var item = ItemEquipableBuilder.builder().equipped(false).name("m1154h").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650, 220)).image(null).removable(true).visible(true).build();
        new AddItemInventory(player.getInventory(), item).run();
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
