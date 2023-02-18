import model.*;
import model.builder.item.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import view.ButtonItem;
import view.Colors;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ButtonItemTest {

    private final ButtonItem buttonItem = new ButtonItem();
    private final ArrayList<Item> itens = new ArrayList<>();
    private final String[] button =
            {"pa", "mochila", "madeira", "tesouro"};

    @Before
    public void create() {
        itens.add(ItemUsableBuilder.builder().localUse("praia").name("pa").description("ferramenta usada para cavar").weight(0)
                .point(new Point(200,280)).image(null).removable(true).visible(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("mochila").description("utilizada para carregar mais coisas").weight(0)
                .point(new Point(650,220)).image(null).removable(true).visible(true).build());
        itens.add(ItemCombinableBuilder.builder().combine(3).name("madeira").description("cabo de madeira velho").weight(0)
                .point(new Point(410,200)).image(null).removable(true).visible(true).build());
        itens.add(ItemMissionBuilder.builder().mapGame("barco").name("tesouro").description("tesouro lendário dos templários").weight(0)
                .point(new Point(620,240)).image(null).removable(false).visible(true).build());
        for (Item item : itens) {
            buttonItem.create(item);
        }
    }

    @Test
    @DisplayName("Verificar a quantidade de botões criados")
    public void createAllButtonValid() {
        assertEquals(itens.size(), buttonItem.getButtonItens().length);
    }

    @Test
    @DisplayName("Verificar se os nomes dos botões foi criado corretamente")
    public void validAllNameButton() {
        String name;
        for (int i = 0; i < button.length; i++) {
            name = buttonItem.getButtonItens()[i].getName();
            assertEquals(name, button[i]);
        }
    }

    @Test
    @DisplayName("Verificar se os nomes dos comandos dos botões foi criado corretamente")
    public void validAllActionCommandButton() {
        String name;
        for (int i = 0; i < button.length; i++) {
            name = buttonItem.getButtonItens()[i].getActionCommand();
            assertEquals(name, button[i]);
        }
    }

    @Test
    @DisplayName("Verificar se todos os 4 botões foram removido do JLabel")
    public void validRemoveAllButton() {
        buttonItem.remove(new JLabel());
        assertEquals(0, buttonItem.getButtonItens().length);
    }

    @Test
    @DisplayName("Verificar se o ultimo botão foi atribuido corretamente")
    public void validGetLast() {
        assertEquals(itens.get(itens.size() - 1).getName(),
                buttonItem.getLast().getName());
    }

    @Test
    @DisplayName("Verificar se o ultimo botão incia como null")
    public void invalidGetLast() {
        ButtonItem button = new ButtonItem();
        assertNull(button.getLast());
    }

    @Test
    @DisplayName("Verificar se ao criar mais 4 botões, a quantidade vai pra 8 quantidade")
    public void validUpdate() {
        for (Item item : itens) {
            buttonItem.create(item);
        }
        assertEquals(itens.size() * 2,
                buttonItem.getButtonItens().length);
    }

    @Test
    @DisplayName("Verificar se desabilitara os butons itens que não são Combinable e mander habilitado os Combinable")
    public void validEnableIButtonItens() {
        Player player = Player.getInstance();
        var enableIButton = new ArrayList<Boolean>();
        itens.forEach(item -> {
            enableIButton.add(item instanceof ItemCombinable);
            player.getInventory().addItem(item);
        });
        buttonItem.enableIButtonItensNotCombinable();
        for (int i = 0; i < itens.size(); i++) {
            assertEquals(enableIButton.get(i),
                    buttonItem.getButtonItens()[i].isEnabled());
        }
    }

    @Test
    @DisplayName("Verificar se ao clicar no botão ele ficaram com o Background verde")
    public void validSelectButtonItem() {
        buttonItem.selectButtonItem(itens.get(0));
        var color = buttonItem.getButtonItens()[0].getBackground();
        assertEquals(Colors.GREEN, color);
    }
}
