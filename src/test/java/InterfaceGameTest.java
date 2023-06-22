import backend.service.model.Player;
import backend.service.model.builder.Item;
import backend.service.model.builder.ItemEquipableBuilder;
import backend.repository.factory.RepositoryFactory;
import frontend.model.component.ComponentFactory;
import frontend.service.InterfaceGame;
import org.junit.Before;
import org.junit.Test;
import backend.service.interfaces.ICoordinate;

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
        var repositoryMapGame = RepositoryFactory.getRepositoryMapGame();
        player.setCurrentMap(repositoryMapGame.get("cais"));
        for (Item item : RepositoryFactory.getRepositoryItem()
                .getAll().stream()
                .filter(Item::isInvisible)
                .filter(item -> !item.getName().equals("chave"))
                .toList()) {
            player.getInventory().setItemInvisible(item);
        }
        var listJLabel = new ArrayList<JLabel>();
        listJLabel.add(ComponentFactory.getJLabel(player));
        listJLabel.add(ComponentFactory.getJLabel(player.getCurrentMap()));
        interfaceGame = new InterfaceGame(listJLabel);
        frame = interfaceGame.getFrame();
        container = frame.getContentPane();
    }

    @Test
    public void validTitleFrame() {
        assertEquals(ComponentFactory.getJFrame().getTitle(), frame.getTitle());
    }

    @Test
    public void validWidthFrame() {
        assertEquals(ComponentFactory.getJFrame().getWidth(), frame.getWidth());
    }

    @Test
    public void validHeightFrame() {
        assertEquals(ComponentFactory.getJFrame().getHeight(), frame.getHeight());
    }

    @Test
    public void validSizeComponentJMenuBar() {
        JMenuBar menuBar = frame.getJMenuBar();
        assertEquals(6, menuBar.getComponentCount());
    }

    @Test
    public void validIconJLabelPlayer() {
        var icon = ((JLabel) container.getComponent(0)).getIcon();
        assertEquals(player.getIcon().toString(), icon.toString());
    }

    @Test
    public void validIconJLabelMapGame() {
        var icon = ((JLabel) container.getComponent(1)).getIcon();
        assertEquals(player.getCurrentMap().getIcon().toString(), icon.toString());
    }

    @Test
    public void validWidthJLabelMapGame() {
        var jLabel = ComponentFactory.getJLabel(player.getCurrentMap());
        var width = container.getComponent(1).getWidth();
        assertEquals(jLabel.getWidth(), width);
    }

    @Test
    public void validHeightJLabelMapGame() {
        var jLabel = ComponentFactory.getJLabel(player.getCurrentMap());
        var height = container.getComponent(1).getHeight();
        assertEquals(jLabel.getHeight(), height);
    }

    @Test
    public void validSetItensJLabel() {
        var itens = new ArrayList<Item>();
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("mochila1").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650, 220)).image(null).removable(true).visible(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("mochila2").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650, 220)).image(null).removable(true).visible(true).build());
        int size = container.getComponentCount();
        interfaceGame.setItensJLabel(itens, 2);
        assertEquals(size + 2, container.getComponentCount());
    }

    @Test
    public void validClearItensJLabel() {
        var itens = new ArrayList<Item>();
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("mochila1").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650, 220)).image(null).removable(true).visible(true).build());
        itens.add(ItemEquipableBuilder.builder().equipped(false).name("mochila2").description("utilizada para carregar mais coisas").weight(0)
                .coordinate(ICoordinate.getInstance(650, 220)).image(null).removable(true).visible(true).build());
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
        String item = "src/main/resources/audio/effects/chave.wav";
        interfaceGame.playEffects(command, item);
        assertTrue(interfaceGame.getSoundEffects().getFilename().contains(item));
    }

}
