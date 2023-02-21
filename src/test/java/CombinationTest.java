import exception.ItemCombinableException;
import model.Coordinate;
import model.Player;
import model.builder.item.Item;
import model.builder.item.ItemCombinableBuilder;
import model.builder.item.ItemEquipableBuilder;
import org.junit.Before;
import org.junit.Test;
import repository.RepositoryMapGame;
import service.Combination;

import java.util.ArrayList;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class CombinationTest {

    private final ArrayList<Item> item = new ArrayList<>();

    @Before
    public void criarCombination() {
        Player player = Player.getInstance();
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }

    }

    @Test
    public void ItensValidoParaCombinacao() {
        item.add(ItemCombinableBuilder.builder().combine(2).name("martelo").description("utilzado para construir algo").weight(0)
                .coordinate(new Coordinate(160, 320)).image(null).removable(true).visible(true).build());
        item.add(ItemCombinableBuilder.builder().combine(2).name("pregos").description("utilzado para construir algo").weight(0)
                .coordinate(new Coordinate(460, 400)).image(null).removable(true).visible(true).build());
        item.add(ItemCombinableBuilder.builder().combine(2).name("madeiras").description("madeira para construir algo").weight(0)
                .coordinate(new Coordinate(640, 80)).image(null).removable(true).visible(true).build());
        assertTrue(new Combination(item).run());
    }

    @Test
    public void ItensValidoParaCombinacaoMapa() {
        item.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .coordinate(new Coordinate(510, 320)).image(null).removable(true).visible(true).build());
        item.add(ItemCombinableBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(0)
                .coordinate(new Coordinate(490, 390)).image(null).removable(true).visible(true).build());
        assertTrue(new Combination(item).run());
    }


    @Test(expected = ItemCombinableException.class)
    public void AllItemAleatorios() {
        item.add(ItemCombinableBuilder.builder().combine(3).name("faca").description("serve para cortar algo").weight(0)
                .coordinate(new Coordinate(420, 130)).image(null).removable(true).visible(true).build());
        item.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .coordinate(new Coordinate(510, 320)).image(null).removable(true).visible(true).build());
        item.add(ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(new Coordinate(650, 220)).image(null).removable(true).visible(true).build());
        assertFalse(new Combination(item).run());
    }

    @Test(expected = ItemCombinableException.class)
    public void AllItemCombinableMasComCombinacaoDiferente() {
        item.add(ItemCombinableBuilder.builder().combine(2).name("madeiras").description("madeira para construir algo").weight(0)
                .coordinate(new Coordinate(640, 80)).image(null).removable(true).visible(true).build());
        item.add(ItemCombinableBuilder.builder().combine(3).name("madeira").description("cabo de madeira velho").weight(0)
                .coordinate(new Coordinate(410, 200)).image(null).removable(true).visible(true).build());
        assertFalse(new Combination(item).run());
    }

    @Test(expected = ItemCombinableException.class)
    public void AllItemCombinableComCombinacaoIguaisMasFaltandoAlgumItem() {
        item.add(ItemCombinableBuilder.builder().combine(2).name("madeiras").description("madeira para construir algo").weight(0)
                .coordinate(new Coordinate(640, 80)).image(null).removable(true).visible(true).build());
        item.add(ItemCombinableBuilder.builder().combine(2).name("pregos").description("utilzado para construir algo").weight(0)
                .coordinate(new Coordinate(640, 400)).image(null).removable(true).visible(true).build());
        assertFalse(new Combination(item).run());
    }

}
