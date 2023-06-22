import org.junit.Before;
import org.junit.Test;
import frontend.service.PanelInventory;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class PanelInventoryTest {

    private PanelInventory panelInventory;

    @Before
    public void create() {
        panelInventory = new PanelInventory();
        panelInventory.create();
    }

    @Test
    public void createAllComponents() {
        assertEquals(4, panelInventory.getPanel().getComponents().length);
    }

    @Test
    public void validNameButton() {
        assertEquals("sair", panelInventory.getButton().getName());
    }

    @Test
    public void validIconLabelSideEast() {
        var icon = new ImageIcon("src/main/resources/inventario/icons.png");
        var label = (JLabel)((JPanel) panelInventory.getPanel().getComponents()[1]).getComponents()[0];
        assertEquals(icon.toString(), label.getIcon().toString());
    }

    @Test
    public void validIconNorthPanel() {
        var label = (JLabel)((JPanel) panelInventory.getPanel().getComponents()[0]).getComponents()[0];
        var icon = new ImageIcon("src/main/resources/inventario/top.png");
        assertEquals(icon.toString(), label.getIcon().toString());
    }

    @Test
    public void validIconWestPanel() {
        var label = (JLabel)((JPanel) panelInventory.getPanel().getComponents()[3]).getComponents()[0];
        var icon = new ImageIcon("src/main/resources/inventario/player.png");
        assertEquals(icon.toString(), label.getIcon().toString());
    }

}
