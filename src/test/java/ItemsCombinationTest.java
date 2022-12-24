import model.ItemCombinable;
import model.enums.ItemsCombination;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class ItemsCombinationTest {
    private final List<ItemCombinable> itens = new ArrayList<>();

    @Before
    public void crearCombination(){
        itens.add(new ItemCombinable("madeira", "cabo de madeira velho", 5, 3,410,200,null));
        itens.add(new ItemCombinable("madeiras", "madeira para construir algo", 5, 2,640,80,null));
        itens.add(new ItemCombinable("faca", "serve para cortar algo", 3, 3,420,130,null));
        itens.add(new ItemCombinable("martelo", "utilzado para construir algo", 4, 2,160,320,null));
        itens.add(new ItemCombinable("papel", "papel escrito em lingua antiga", 2, 1,510,320,null));
        itens.add(new ItemCombinable("pregos", "utilzado para construir algo", 3, 2,460,400,null));
    }
    //1 - 1
    //2 - 3
    //3 - 2
    @Test
    public void retornarAQuantidadeDeCombinacaoDoItem(){
        assertEquals(3, ItemsCombination.getAmountCombination(2));//2 e 3
    }

    @Test
    public void retornarNegativoAQuantidadeDeCombinacaoDoItem(){
        assertEquals(-1, ItemsCombination.getAmountCombination(4));//2 e 3
    }
}
