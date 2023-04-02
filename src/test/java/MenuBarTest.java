import frontend.exception.EventException;
import frontend.model.Song;
import frontend.model.SoundEffects;
import org.junit.Before;
import org.junit.Test;
import frontend.view.EventsMenuBar;
import frontend.view.MenuBar;

import javax.swing.*;

import static org.junit.Assert.*;

public class MenuBarTest {

    private MenuBar menuBar;
    private final JFrame frame = new JFrame();
    private final Song song = new Song();
    private final SoundEffects soundEffects = new SoundEffects();
    private final String [] navigation =
            {"Historia","Comandos","Ajuda","Musica",
                    "Efeitos", "Sair"};

    @Before
    public void create(){
        menuBar = new MenuBar(frame.getContentPane(), song, soundEffects);
        for (String s : navigation) {
            menuBar.createNavigation(s);
        }
    }

    @Test
    public void createAllNavigationValid(){
        assertEquals(navigation.length,
                menuBar.getMenubar().getMenuCount());
    }

    @Test
    public void validAllNameElements(){
        String name;
        for (int i = 0; i < navigation.length; i++) {
            name = ((JMenuItem) menuBar.getMenubar().getSubElements()[i]).getText();
            assertEquals(name, navigation[i]);
        }
    }

    @Test
    public void validAllEvent(){
        int size;
        for (int i = 0; i < navigation.length; i++) {
            size = ((JMenuItem) menuBar.getMenubar().getSubElements()[i])
                    .getMouseListeners().length;
            assertEquals(1, size);
        }
    }

    @Test (expected = EventException.class)
    public void invalidAllEvent(){
        EventsMenuBar eventsFrame = new EventsMenuBar(frame.getContentPane(), song, soundEffects);
        eventsFrame.event("test");
    }
}
