import exception.ButtonException;
import model.builder.item.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import view.ButtonAction;

import javax.swing.*;
import java.awt.*;
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
        itens.add(ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .point(new Point(200,280)).image(null).removable(true).visible(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .point(new Point(650,220)).image(null).removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(3).name("madeira").description("cabo de madeira velho").weight(0)
                .point(new Point(410,200)).image(null).removable(true).visible(true).build());
        itens.add(ItemMissionBuilder.builder().mapGame("barco").name("tesouro").description("tesouro lendário dos templários").weight(0)
                .point(new Point(620,240)).image(null).removable(false).visible(true).build());
    }

    @Test
    @DisplayName("Verificar a quantidade de botões criados")
    public void createAllButtonValid(){
        assertEquals(button.length,buttonAction.getButtonActions().length);
    }

    @Test
    @DisplayName("Verificar se os nomes dos botões foi criado corretamente")
    public void validAllNameButton(){
        String name;
        for (int i = 0; i < button.length; i++) {
            name = buttonAction.getButtonActions()[i].getText().toLowerCase();
            assertEquals(name, button[i]);
        }
    }

    @Test
    @DisplayName("Verificar se os nomes dos comandos dos botões foi criado corretamente")
    public void validAllActionCommandButton(){
        String name;
        for (int i = 0; i < button.length; i++) {
            name = buttonAction.getButtonActions()[i].getActionCommand();
            assertEquals(name, button[i]);
        }
    }

    @Test
    @DisplayName("Verificar se os botões foi criado inicialmente como invisivel")
    public void validAllInvisibleButton(){
        buttonAction.invisible();
        for (JButton  button : buttonAction.getButtonActions()) {
            assertFalse(button.isVisible());
        }
    }

    @Test
    @DisplayName("Verificar se ao cliclar o botão com o comando usar, os botões cancelar e confirmar estará visivel")
    public void validVisibleCancelAndConfirm(){
        buttonAction.visibleCancelAndConfirm("usar");
        for (int i = 4; i < buttonAction.getButtonActions().length; i++) {
            assertTrue(buttonAction.getButtonActions()[i].isVisible());
        }
    }

    @Test
    @DisplayName("Verificar se ao cliclar o botão com o comando combinar, o botão confirmar estará invisivel")
    public void invalidVisibleCancelAndConfirm(){
        buttonAction.visibleCancelAndConfirm("combinar");
        assertFalse(buttonAction.getButtonActions()[5].isVisible());
    }

    @Test
    @DisplayName("Verificar se ao cliclar o botão com o comando combinar, o botão confirmar estará visivel")
    public void validVisibleConfirmCombine(){
        buttonAction.visibleConfirmCombine("combinar");
        assertTrue(buttonAction.getButtonActions()[5].isVisible());
    }

    @Test
    @DisplayName("Verificar se o item foi atribuido corretamente")
    public void validGetItem(){
        buttonAction.setUseItem(itens.get(0));
        assertNotNull(buttonAction.getUseItem());
    }

    @Test
    @DisplayName("Verificar se ao atribuir um item do tipo Usable, os botões ficará visivel corretamente")
    public void validEnabledItemUsable(){
        Boolean [] enables = {true, false, false, true};
        buttonAction.setUseItem(itens.get(0));
        for (int i = 0; i < 4; i++) {
            assertEquals(enables[i],buttonAction.getButtonActions()[i].isEnabled());
        }
    }

    @Test
    @DisplayName("Verificar se ao atribuir um item do tipo Equipable, os botões ficará visivel corretamente")
    public void validEnabledItemEquipable(){
        Boolean [] enables = {false, true, false, true};
        buttonAction.setUseItem(itens.get(1));
        for (int i = 0; i < 4; i++) {
            assertEquals(enables[i],buttonAction.getButtonActions()[i].isEnabled());
        }
    }

    @Test
    @DisplayName("Verificar se ao atribuir um item do tipo Combinable, os botões ficará visivel corretamente")
    public void validEnabledItemCombinable(){
        Boolean [] enables = {false, false, true, true};
        buttonAction.setUseItem(itens.get(2));
        for (int i = 0; i < 4; i++) {
            assertEquals(enables[i],buttonAction.getButtonActions()[i].isEnabled());
        }
    }

    @Test
    @DisplayName("Verificar se ao atribuir um item do tipo NotRemove, os botões ficará visivel corretamente")
    public void validEnabledItemNotRemove(){
        buttonAction.setUseItem(itens.get(3));
        for (int i = 0; i < 4; i++) {
            assertFalse(buttonAction.getButtonActions()[i].isEnabled());
        }
    }

    @Test(expected = ButtonException.class)
    @DisplayName("Verificar ao tentar criar um botão invalido, irá lançar ButtonException 'Nome do botão não encontrado'")
    public void createButtonInvalid(){
        ButtonAction button = new ButtonAction();
        button.create("button");
    }
}
