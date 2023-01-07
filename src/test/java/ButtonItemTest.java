import exception.ButtonException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import view.ButtonAction;
import view.ButtonItem;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ButtonItemTest {

    private final ButtonItem buttonItem = new ButtonItem();
    private final ArrayList<Item> itens = new ArrayList<>();
    private final String [] button =
            {"pa","mochila","madeira","tesouro"};
    @Before
    public void create(){
        itens.add(new ItemUsable("pa", "ferramenta usada para cavar", 3, "praia", 200, 280, null));
        itens.add(new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0,650,220,null));
        itens.add(new ItemCombinable("madeira", "cabo de madeira velho", 5, 3,410,200,null));
        itens.add(new ItemNotRemove("tesouro", "tesouro lendário dos templários", null, 3, 620, 240, null));
        for (Item item : itens) {
            buttonItem.create(item);
        }
    }

    @Test
    public void createAllButtonValid(){
        assertEquals(itens.size(),buttonItem.getButtonItens().length);
    }

    @Test
    public void validAllNameButton(){
        String name;
        for (int i = 0; i < button.length; i++) {
            name = buttonItem.getButtonItens()[i].getName();
            assertEquals(name, button[i]);
        }
    }

    @Test
    public void validAllActionCommandButton(){
        String name;
        for (int i = 0; i < button.length; i++) {
            name = buttonItem.getButtonItens()[i].getActionCommand();
            assertEquals(name, button[i]);
        }
    }

    @Test
    public void validRemoveAllButton(){
        buttonItem.remove(new JLabel());
        assertEquals(0,buttonItem.getButtonItens().length);
    }

    @Test
    public void validGetLast(){
        assertEquals(itens.get(itens.size()-1).getName(),
                buttonItem.getLast().getName());
    }

    @Test
    public void invalidGetLast(){
         ButtonItem button = new ButtonItem();
         assertNull(button.getLast());
    }

    @Test
    public void validUpdate(){
        for (Item item : itens) {
            buttonItem.create(item);
        }
        assertEquals(itens.size()*2,
                buttonItem.getButtonItens().length);
    }

}
