import exception.ItemCombinableException;
import model.builder.item.*;
import service.Combination;
import model.Player;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;

import java.util.ArrayList;

import static junit.framework.TestCase.*;
import static org.junit.Assert.assertTrue;

public class CombinationTest {

    private Player player;
    private final ArrayList<Item> item = new ArrayList<>();

    @Before
    public void criarCombination() {
        player = Player.getInstance();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }

    }

    @Test
    public void ItensValidoParaCombinacao() {
        item.add(ItemCombinableBuilder.builder().combine(2).name("martelo").description("utilzado para construir algo").weight(0)
                .positionX(160).positionY(320).image(null).removable(true).visible(true).build());
        item.add(ItemCombinableBuilder.builder().combine(2).name("pregos").description("utilzado para construir algo").weight(0)
                .positionX(460).positionY(400).image(null).removable(true).visible(true).build());
        item.add(ItemCombinableBuilder.builder().combine(2).name("madeiras").description("madeira para construir algo").weight(0)
                .positionX(640).positionY(80).image(null).removable(true).visible(true).build());
        assertTrue(new Combination(item).run());
    }

    @Test
    public void ItensValidoParaCombinacaoMapa() {
        item.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .positionX(510).positionY(320).image(null).removable(true).visible(true).build());
        item.add(ItemCombinableBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(0)
                .positionX(490).positionY(390).image(null).removable(true).visible(true).build());
        assertTrue(new Combination(item).run());
    }



    @Test (expected = ItemCombinableException.class)
    public void AllItemAleatorios() {
        item.add(ItemCombinableBuilder.builder().combine(3).name("faca").description("serve para cortar algo").weight(0)
                .positionX(420).positionY(130).image(null).removable(true).visible(true).build());
        item.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .positionX(510).positionY(320).image(null).removable(true).visible(true).build());
        item.add(ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .positionX(650).positionY(220).image(null).removable(true).visible(true).build());
        assertFalse(new Combination(item).run());
    }

    @Test (expected = ItemCombinableException.class)
    public void AllItemCombinableMasComCombinacaoDiferente() {
        item.add(ItemCombinableBuilder.builder().combine(2).name("madeiras").description("madeira para construir algo").weight(0)
                .positionX(640).positionY(80).image(null).removable(true).visible(true).build());
        item.add(ItemCombinableBuilder.builder().combine(3).name("madeira").description("cabo de madeira velho").weight(0)
                .positionX(410).positionY(200).image(null).removable(true).visible(true).build());
        assertFalse(new Combination(item).run());
    }

    @Test (expected = ItemCombinableException.class)
    public void AllItemCombinableComCombinacaoIguaisMasFaltandoAlgumItem() {
        item.add(ItemCombinableBuilder.builder().combine(2).name("madeiras").description("madeira para construir algo").weight(0)
                .positionX(640).positionY(80).image(null).removable(true).visible(true).build());
        item.add(ItemCombinableBuilder.builder().combine(3).name("pregos").description("utilzado para construir algo").weight(0)
                .positionX(460).positionY(400).image(null).removable(true).visible(true).build());
        assertFalse(new Combination(item).run());
    }

}
