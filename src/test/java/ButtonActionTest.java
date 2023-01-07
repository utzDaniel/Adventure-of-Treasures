import exception.ButtonException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import view.ButtonAction;

import javax.swing.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class ButtonActionTest {

    private final ButtonAction buttonAction = new ButtonAction();
    private final ArrayList<Item> itens = new ArrayList<>();
    private final String [] button =
            {"usar","equipar","combinar","remover",
                    "cancelar", "confirmar"};

    @Before
    public void create(){
        for (String s : button) {
            buttonAction.create(s);
        }
        itens.add(new ItemUsable("pa", "ferramenta usada para cavar", 3, "praia", 200, 280, null));
        itens.add(new ItemEquipable("mochila", "utilizada para carregar mais coisas", 0,650,220,null));
        itens.add(new ItemCombinable("madeira", "cabo de madeira velho", 5, 3,410,200,null));
        itens.add(new ItemNotRemove("tesouro", "tesouro lendário dos templários", null, 3, 620, 240, null));
    }

    @Test
    public void createAllButtonValid(){
        assertEquals(button.length,buttonAction.getButtonActions().length);
    }

    @Test
    public void validAllNameButton(){
        String name;
        for (int i = 0; i < button.length; i++) {
            name = buttonAction.getButtonActions()[i].getText().toLowerCase();
            assertEquals(name, button[i]);
        }
    }

    @Test
    public void validAllActionCommandButton(){
        String name;
        for (int i = 0; i < button.length; i++) {
            name = buttonAction.getButtonActions()[i].getActionCommand();
            assertEquals(name, button[i]);
        }
    }

    @Test
    public void validAllInvisibleButton(){
        buttonAction.invisible();
        for (JButton  button : buttonAction.getButtonActions()) {
            assertFalse(button.isVisible());
        }
    }

    @Test
    public void validVisibleCancelAndConfirm(){
        buttonAction.visibleCancelAndConfirm("usar");
        for (int i = 4; i < buttonAction.getButtonActions().length; i++) {
            assertTrue(buttonAction.getButtonActions()[i].isVisible());
        }
    }

    @Test
    public void invalidVisibleCancelAndConfirm(){
        buttonAction.visibleCancelAndConfirm("combinar");
        assertFalse(buttonAction.getButtonActions()[5].isVisible());
    }

    @Test
    public void validVisibleConfirmCombine(){
        buttonAction.visibleConfirmCombine("combinar");
        assertTrue(buttonAction.getButtonActions()[5].isVisible());
    }

    @Test
    public void validGetItem(){
        buttonAction.setUseItem(itens.get(0));
        assertNotNull(buttonAction.getUseItem());
    }

    @Test
    public void validEnabledItemUsable(){
        Boolean [] enables = {true, false, false, true};
        buttonAction.setUseItem(itens.get(0));
        for (int i = 0; i < 4; i++) {
            assertEquals(enables[i],buttonAction.getButtonActions()[i].isEnabled());
        }
    }

    @Test
    public void validEnabledItemEquipable(){
        Boolean [] enables = {false, true, false, true};
        buttonAction.setUseItem(itens.get(1));
        for (int i = 0; i < 4; i++) {
            assertEquals(enables[i],buttonAction.getButtonActions()[i].isEnabled());
        }
    }

    @Test
    public void validEnabledItemCombinable(){
        Boolean [] enables = {false, false, true, true};
        buttonAction.setUseItem(itens.get(2));
        for (int i = 0; i < 4; i++) {
            assertEquals(enables[i],buttonAction.getButtonActions()[i].isEnabled());
        }
    }

    @Test
    public void validEnabledItemNotRemove(){
        buttonAction.setUseItem(itens.get(3));
        for (int i = 0; i < 4; i++) {
            assertFalse(buttonAction.getButtonActions()[i].isEnabled());
        }
    }

    @Test(expected = ButtonException.class)
    public void createButtonInvalid(){
        ButtonAction button = new ButtonAction();
        button.create("button");
    }
}
