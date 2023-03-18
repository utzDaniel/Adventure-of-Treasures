import org.junit.Before;
import org.junit.Test;
import view.PanelInventory;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class PanelInventoryTest {

    private final JLabel labelSideEast = new JLabel();
    private PanelInventory panelInventory;

    @Before
    public void create() {
        panelInventory = new PanelInventory(labelSideEast);
        panelInventory.create();
    }

    @Test
    public void createAllComponents() {
        assertEquals(4, panelInventory.getPanel().getComponents().length);
    }

    @Test
    public void validNameButton() {
        assertEquals("SAIR", panelInventory.getButton().getText());
    }

    @Test
    public void validIconLabelSideEast() {
        var icon = new ImageIcon("src/main/resources/inventario/icons.png");
        assertEquals(icon.toString(), labelSideEast.getIcon().toString());
    }

    @Test
    public void validIconNorthPanel() {
        var label = (JLabel) panelInventory.getPanel().getComponents()[0];
        var icon = new ImageIcon("src/main/resources/inventario/top.png");
        assertEquals(icon.toString(), label.getIcon().toString());
    }

    @Test
    public void validIconWestPanel() {
        var label = (JLabel) panelInventory.getPanel().getComponents()[2];
        var icon = new ImageIcon("src/main/resources/inventario/player.png");
        assertEquals(icon.toString(), label.getIcon().toString());
    }

}
