import exception.ItemEquipableException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import service.Equip;

import java.util.ArrayList;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class EquipTest {
    private final ArrayList<Item> itens = new ArrayList<>();

    @Before
    public void inicial(){
        itens.add(new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0,650,220,null));
        itens.add(new ItemUsable("chave", "utilizada para abir algo", 3, "vila",580,300,null));
        itens.add(new ItemEquipable("toch", "utilizado para iluminar", 5,410,200,null));
    }

    @Test
    public void validarItemEquipable(){
        assertTrue(new Equip(new Player(),itens.get(0)).run());
    }

    @Test (expected = ItemEquipableException.class)
    public void itemEquipableInvalido(){
        assertFalse(new Equip(new Player(),itens.get(2)).run());
    }

    @Test (expected = ItemEquipableException.class)
    public void naoValidarItemEquipable(){
        assertFalse(new Equip(new Player(),itens.get(1)).run());
    }

}
