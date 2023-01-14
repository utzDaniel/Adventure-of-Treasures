import model.Item;
import model.ItemEquipable;
import model.Player;
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
    Player player = new Player();

    @Before
    public void create() {
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
        interfaceGame = new InterfaceGame(player.getCurrentMap().getImagemIcon());
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
        assertEquals(settingsPlayer.ImageInitial().toString(), icon.toString());
    }

    @Test
    public void validIconJLabelMapGame() {
        var icon = ((JLabel) container.getComponent(1)).getIcon();
        assertEquals(player.getCurrentMap().getImagemIcon(), icon);
    }

    @Test
    public void validWidthJLabelMapGame() {
        SettingsMapGame settingsMapGame = new SettingsMapGame();
        var width = container.getComponent(1).getWidth();
        assertEquals(settingsMapGame.labelWidth(), width);
    }

    @Test
    public void validHeightJLabelMapGame() {
        SettingsMapGame settingsMapGame = new SettingsMapGame();
        var height = container.getComponent(1).getHeight();
        assertEquals(settingsMapGame.labelHeight(), height);
    }

    @Test
    public void validSetItensJLabel() {
        ArrayList<Item> itens = new ArrayList<>();
        itens.add(new ItemEquipable("mochila1", "utilizada para carregar mais coisas", 0, 650, 220, null));
        itens.add(new ItemEquipable("mochila2", "utilizada para carregar mais coisas", 0, 650, 220, null));
        int size = container.getComponentCount();
        interfaceGame.setItensJLabel(itens, 2);
        assertEquals(size + 2, container.getComponentCount());
    }

    @Test
    public void validClearItensJLabel() {
        ArrayList<Item> itens = new ArrayList<>();
        itens.add(new ItemEquipable("mochila1", "utilizada para carregar mais coisas", 0, 650, 220, null));
        itens.add(new ItemEquipable("mochila2", "utilizada para carregar mais coisas", 0, 650, 220, null));
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
