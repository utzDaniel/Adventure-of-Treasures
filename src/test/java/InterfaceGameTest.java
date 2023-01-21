import model.builder.item.Item;
import model.builder.item.ItemEquipable;
import model.Player;
import model.builder.item.ItemEquipableBuilder;
import org.junit.Before;
import org.junit.Test;
import repository.CreateMapGame;
import settings.SettingsJFrame;
import settings.SettingsMapGame;
import settings.SettingsPlayer;
import view.InterfaceGame;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class InterfaceGameTest {

    private InterfaceGame interfaceGame;
    private JFrame frame;
    private Container container;
    Player player = Player.getInstance();

    @Before
    public void create() {
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        interfaceGame = new InterfaceGame();
        frame = interfaceGame.getFrame();
        container = frame.getContentPane();
    }

    @Test
    public void validTitleFrame() {
        SettingsJFrame settingsJFrame = new SettingsJFrame();
        assertEquals(settingsJFrame.getTitulo(), frame.getTitle());
    }

    @Test
    public void validWidthFrame() {
        SettingsJFrame settingsJFrame = new SettingsJFrame();
        assertEquals(settingsJFrame.getWidth(), frame.getWidth());
    }

    @Test
    public void validHeightFrame() {
        SettingsJFrame settingsJFrame = new SettingsJFrame();
        assertEquals(settingsJFrame.getHeight(), frame.getHeight());
    }

    @Test
    public void validSizeComponentJMenuBar() {
        JMenuBar menuBar = frame.getJMenuBar();
        assertEquals(6, menuBar.getComponentCount());
    }

    @Test
    public void validIconJLabelPlayer() {
        SettingsPlayer settingsPlayer = new SettingsPlayer();
        var icon = ((JLabel) container.getComponent(0)).getIcon();
        assertEquals(settingsPlayer.getIcon().toString(), icon.toString());
    }

    @Test
    public void validIconJLabelMapGame() {
        var icon = ((JLabel) container.getComponent(1)).getIcon();
        assertEquals(player.getCurrentMap().getImagemIcon().toString(), icon.toString());
    }

    @Test
    public void validWidthJLabelMapGame() {
        SettingsMapGame settingsMapGame = new SettingsMapGame();
        var width = container.getComponent(1).getWidth();
        assertEquals(settingsMapGame.getRectangle().width, width);
    }

    @Test
    public void validHeightJLabelMapGame() {
        SettingsMapGame settingsMapGame = new SettingsMapGame();
        var height = container.getComponent(1).getHeight();
        assertEquals(settingsMapGame.getRectangle().height, height);
    }

    @Test
    public void validSetItensJLabel() {
        var itens = new ArrayList<Item>();
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("mochila1").description("utilizada para carregar mais coisas").weight(0)
                .positionX(650).positionY(220).image(null).removable(true).visible(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("mochila2").description("utilizada para carregar mais coisas").weight(0)
                .positionX(650).positionY(220).image(null).removable(true).visible(true).build());
        int size = container.getComponentCount();
        interfaceGame.setItensJLabel(itens, 2);
        assertEquals(size + 2, container.getComponentCount());
    }

    @Test
    public void validClearItensJLabel() {
        var itens = new ArrayList<Item>();
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("mochila1").description("utilizada para carregar mais coisas").weight(0)
                .positionX(650).positionY(220).image(null).removable(true).visible(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("mochila2").description("utilizada para carregar mais coisas").weight(0)
                .positionX(650).positionY(220).image(null).removable(true).visible(true).build());
        int size = container.getComponentCount();
        interfaceGame.setItensJLabel(itens, 2);
        interfaceGame.clearJLabelItens();
        assertEquals(size, container.getComponentCount());
    }

    @Test
    public void validSong() {
        assertNotNull(interfaceGame.getSong());
    }

    @Test
    public void validSoundEffects() {
        assertNotNull(interfaceGame.getSoundEffects());
    }

    @Test
    public void validSoundEffectsPlayCommand() {
        String command = "pegar";
        interfaceGame.playEffects(command, null);
        assertTrue(interfaceGame.getSoundEffects().getFilename().contains(command));
    }

    @Test
    public void validSoundEffectsPlayItem() {
        String command = "usar";
        String item = "chave";
        interfaceGame.playEffects(command, item);
        assertTrue(interfaceGame.getSoundEffects().getFilename().contains(item));
    }

}
