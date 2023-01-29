import model.builder.item.Item;
import model.builder.item.ItemCombinableBuilder;
import model.enums.ItemsCombination;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemsCombinationTest {
    private final List<Item> itens = new ArrayList<>();

    @Before
    public void createCombination(){
        itens.add(ItemCombinableBuilder.builder().combine(3).name("madeira").description("cabo de madeira velho").weight(0)
                .positionX(410).positionY(200).image(null).removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(2).name("madeiras").description("madeira para construir algo").weight(0)
                .positionX(640).positionY(80).image(null).removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(3).name("faca").description("serve para cortar algo").weight(0)
                .positionX(420).positionY(130).image(null).removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(2).name("martelo").description("utilzado para construir algo").weight(0)
                .positionX(160).positionY(320).image(null).removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(1).name("papel").description("papel escrito em lingua antiga").weight(0)
                .positionX(510).positionY(320).image(null).removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(3).name("pregos").description("utilzado para construir algo").weight(0)
                .positionX(460).positionY(400).image(null).removable(true).visible(true).build());
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
