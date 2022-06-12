import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UnequipTest {
    private final ArrayList<Item> itens = new ArrayList<>();
    private final Unequip unequip = new Unequip(new Player());
    @Before
    public void inicial(){
        itens.add(new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0,650,220,null));
        itens.add(new ItemUsable("chave", "utilizada para abir algo", 3, "vila",580,300,null));
        itens.add(new ItemEquipable("tocha", "utilizado para iluminar", 5,410,200,null));
    }

    @Test
    public void validarItemEquipable(){
        assertTrue(unequip.validItemEquipable(itens.get(0)));
    }

    @Test
    public void naoValidarItemEquipable(){
        assertFalse(unequip.validItemEquipable(itens.get(1)));
    }
}
