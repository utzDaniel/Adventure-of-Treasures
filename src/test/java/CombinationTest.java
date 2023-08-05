import backend.service.interfaces.ICoordinate;
import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.service.model.builder.ItemBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class CombinationTest {

    private final ArrayList<Item> item = new ArrayList<>();

    @Before
    public void criarCombination() {
        Player player = Player.getInstance();
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
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
    public void ItensValidoParaCombinacao() {
        item.add(ItemBuilder.builder().combine(2).name("martelo").description("utilzado para construir algo").weight(0)
                .coordinate(ICoordinate.getInstance(160, 320)).image(null).removable(true).visible(true).build());
        item.add(ItemBuilder.builder().combine(2).name("pregos").description("utilzado para construir algo").weight(0)
                .coordinate(ICoordinate.getInstance(460, 400)).image(null).removable(true).visible(true).build());
        item.add(ItemBuilder.builder().combine(2).name("madeiras").description("madeira para construir algo").weight(0)
                .coordinate(ICoordinate.getInstance(640, 80)).image(null).removable(true).visible(true).build());
        var iCombinableList = item.stream()
                .map(item1 ->  item1).toList();
        //assertTrue(new Combination(iCombinableList, Player.getInstance().getInventory()).run());
    }

    @Test
    public void ItensValidoParaCombinacaoMapa() {
        item.add(ItemBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .coordinate(ICoordinate.getInstance(510, 320)).image(null).removable(true).visible(true).build());
        item.add(ItemBuilder.builder().combine(1).name("livro").description("livro antigo usado para decifrar escrita antiga").weight(0)
                .coordinate(ICoordinate.getInstance(490, 390)).image(null).removable(true).visible(true).build());
        var iCombinableList = item.stream()
                .map(item1 ->  item1).toList();
       // assertTrue(new Combination(iCombinableList, Player.getInstance().getInventory()).run());
    }

    @Test//ItemCombinableException.class
    public void AllItemCombinableMasComCombinacaoDiferente() {
        item.add(ItemBuilder.builder().combine(2).name("madeiras").description("madeira para construir algo").weight(0)
                .coordinate(ICoordinate.getInstance(640, 80)).image(null).removable(true).visible(true).build());
        item.add(ItemBuilder.builder().combine(3).name("madeira").description("cabo de madeira velho").weight(0)
                .coordinate(ICoordinate.getInstance(410, 200)).image(null).removable(true).visible(true).build());
        var iCombinableList = item.stream()
                .map(item1 ->  item1).toList();
        //assertFalse(new Combination(iCombinableList, Player.getInstance().getInventory()).run());
    }

    @Test//ItemCombinableException.class
    public void AllItemCombinableComCombinacaoIguaisMasFaltandoAlgumItem() {
        item.add(ItemBuilder.builder().combine(2).name("madeiras").description("madeira para construir algo").weight(0)
                .coordinate(ICoordinate.getInstance(640, 80)).image(null).removable(true).visible(true).build());
        item.add(ItemBuilder.builder().combine(2).name("pregos").description("utilzado para construir algo").weight(0)
                .coordinate(ICoordinate.getInstance(640, 400)).image(null).removable(true).visible(true).build());
        var iCombinableList = item.stream()
                .map(item1 -> item1).toList();
        //assertFalse(new Combination(iCombinableList, Player.getInstance().getInventory()).run());
    }

}
