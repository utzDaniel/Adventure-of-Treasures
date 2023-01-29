import exception.CreateImageException;
import org.junit.Before;
import org.junit.Test;
import repository.CreateImageInventory;
import view.PanelInventory;

import javax.swing.*;

import static org.junit.Assert.assertEquals;

public class PanelInventoryTest {

    private final JLabel labelSideEast = new JLabel();
    private PanelInventory panelInventory;
    private CreateImageInventory imageInventory;

    @Before
    public void create() {
        panelInventory = new PanelInventory(labelSideEast);
        imageInventory = new CreateImageInventory();
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
        var icon = imageInventory.selectImage("icons");
        assertEquals(icon.toString(), labelSideEast.getIcon().toString());
    }

    @Test
    public void validIconNorthPanel() {
        var label = (JLabel) panelInventory.getPanel().getComponents()[0];
        var icon = imageInventory.selectImage("top");
        assertEquals(icon.toString(), label.getIcon().toString());
    }

    @Test
    public void validIconWestPanel() {
        var label = (JLabel) panelInventory.getPanel().getComponents()[2];
        var icon = imageInventory.selectImage("player");
        assertEquals(icon.toString(), label.getIcon().toString());
    }

    @Test (expected = CreateImageException.class)
    public void invalidIconInventory() {
        imageInventory.selectImage("test");
    }
}
