import frontend.exception.EventException;
import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.model.component.JMenuBarFactory;
import frontend.model.component.JMenuFactory;
import frontend.model.component.JMenuItemFactory;
import frontend.view.EventsMenuBar;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class MenuBarTest {

    private JMenuBar menuBar;
    private final JFrame frame = new JFrame();
    private final Song song = new Song();
    private final SoundEffects soundEffects = new SoundEffects();
    private final String[] navigation =
            {"Historia", "Comandos", "Ajuda", "Musica",
                    "Efeitos", "Sair"};

    @Before
    public void create() {
        menuBar = JMenuBarFactory.getInstance();
        var events = new EventsMenuBar(frame.getContentPane(), song, soundEffects);
        List.of("Historia", "Comandos", "Ajuda", "Musica", "Efeitos", "Sair")
                .forEach(name -> {
                    var menu = JMenuFactory.getInstance(name);
                    menuBar.add(menu);
                    var item = JMenuItemFactory.getInstance(name);
                    item.addActionListener(e -> events.action(name));
                    menu.add(item);
                });
    }

    @Test
    public void createAllNavigationValid() {
        assertEquals(navigation.length, menuBar.getMenuCount());
    }

    @Test
    public void validAllNameElements() {
        String name;
        for (int i = 0; i < navigation.length; i++) {
            name = ((JMenuItem) menuBar.getSubElements()[i]).getText();
            assertEquals(name, navigation[i]);
        }
    }

    @Test
    public void validAllEvent() {
        int size;
        for (int i = 0; i < navigation.length; i++) {
            size = ((JMenuItem) menuBar.getSubElements()[i]).getMouseListeners().length;
            assertEquals(1, size);
        }
    }

    @Test(expected = EventException.class)
    public void invalidAllEvent() {
        EventsMenuBar eventsFrame = new EventsMenuBar(frame.getContentPane(), song, soundEffects);
        eventsFrame.action("test");
    }
}
