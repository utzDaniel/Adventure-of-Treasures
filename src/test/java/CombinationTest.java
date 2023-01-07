import exception.ItemCombinableException;
import model.ItemEquipable;
import service.Combination;
import model.Item;
import model.ItemCombinable;
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
        player = new Player();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().addItem(item);
        }

    }

    @Test
    public void ItensValidoParaCombinacao() {
        item.add(new ItemCombinable("martelo", "utilzado para construir algo", 4, 2, 160, 320, null));
        item.add(new ItemCombinable("pregos", "utilzado para construir algo", 3, 2, 460, 400, null));
        item.add(new ItemCombinable("madeiras", "madeira para construir algo", 5, 2, 640, 80, null));
        assertTrue(new Combination(player, item).run());
    }

    @Test
    public void ItensValidoParaCombinacaoMapa() {
        item.add(new ItemCombinable("papel", "papel escrito em lingua antiga", 2, 1, 510, 320, null));
        item.add(new ItemCombinable("livro", "livro antigo usado para decifrar escrita antiga", 1, 1, 490, 390, null));
        assertTrue(new Combination(player, item).run());
    }



    @Test (expected = ItemCombinableException.class)
    public void AllItemAleatorios() {
        item.add(new ItemCombinable("faca", "serve para cortar algo", 3, 3, 420, 130, null));
        item.add(new ItemCombinable("papel", "papel escrito em lingua antiga", 2, 1, 510, 320, null));
        item.add(new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0, 650, 220, null));
        assertFalse(new Combination(player, item).run());
    }

    @Test (expected = ItemCombinableException.class)
    public void AllItemCombinableMasComCombinacaoDiferente() {
        item.add(new ItemCombinable("madeiras", "madeira para construir algo", 5, 2, 640, 80, null));
        item.add(new ItemCombinable("madeira", "cabo de madeira velho", 5, 3, 410, 200, null));
        assertFalse(new Combination(player, item).run());
    }

    @Test (expected = ItemCombinableException.class)
    public void AllItemCombinableComCombinacaoIguaisMasFaltandoAlgumItem() {
        item.add(new ItemCombinable("madeiras", "madeira para construir algo", 5, 2, 640, 80, null));
        item.add(new ItemCombinable("pregos", "utilzado para construir algo", 3, 2, 460, 400, null));
        assertFalse(new Combination(player, item).run());
    }


}
