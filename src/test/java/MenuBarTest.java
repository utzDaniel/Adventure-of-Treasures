import exception.ButtonException;
import model.*;
import org.junit.Before;
import org.junit.Test;
import view.ButtonAction;
import view.MenuBar;
import view.PopupMenuBarMessage;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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
        menuBar = new MenuBar(frame, song, soundEffects);
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
    public void validEventHistoria(){

        System.out.println(((JMenuItem) menuBar.getMenubar().getSubElements()[0])
                        .getPropertyChangeListeners("Musica").length
        );
//.getMouseListeners()[0]
    }

}
