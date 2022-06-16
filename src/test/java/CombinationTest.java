import model.Combination;
import model.Item;
import model.ItemCombinable;
import model.Player;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;

import java.util.ArrayList;

import static junit.framework.TestCase.*;

public class CombinationTest {

    private Combination combination;
    private final ArrayList<ItemCombinable> itemValido = new ArrayList<>();
    private final ArrayList<ItemCombinable> itemInvalido = new ArrayList<>();

    @Before
    public void crearCombination(){
        Player player = new Player();
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for(Item item :createMapGame.getItemInvisiblePlayer() ){
            player.takeItem(item);
        }
        combination = new Combination(player);
        itemInvalido.add(new ItemCombinable("madeira", "cabo de madeira velho", 5, 3,410,200,null));
        itemValido.add(new ItemCombinable("madeiras", "madeira para construir algo", 5, 2,640,80,null));
        itemInvalido.add(new ItemCombinable("faca", "serve para cortar algo", 3, 3,420,130,null));
        itemValido.add(new ItemCombinable("martelo", "utilzado para construir algo", 4, 2,160,320,null));
        itemInvalido.add(new ItemCombinable("papel", "papel escrito em lingua antiga", 2, 1,510,320,null));
        itemValido.add(new ItemCombinable("pregos", "utilzado para construir algo", 3, 2,460,400,null));
    }

    @Test
    public void itensValidos(){
        assertTrue(combination.validItemCombinable(new ArrayList<>(itemValido)));
    }

    @Test
    public void itensInvalidos(){
        assertTrue(combination.validItemCombinable(new ArrayList<>(itemInvalido)));
    }

    @Test
    public void validarSeTodosOsItensSaoCombinadosEntreEles(){
        assertTrue(combination.validCombination(new ArrayList<>(itemValido)));
    }

    @Test
    public void NAOvalidarSeTodosOsItensSaoCombinadosEntreEles(){
        assertFalse(combination.validCombination(new ArrayList<>(itemInvalido)));
    }

    @Test
    public void retornarOItemDeCombinacao(){
        assertNotNull(combination.retornarItemDeCombinacao(new ArrayList<>(itemValido)));
    }

    @Test
    public void realizarACombinacaoEntreOsItens(){
        assertTrue(combination.atualizarIventario(new ArrayList<>(itemValido)));
    }
}
