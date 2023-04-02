package frontend.view;

import frontend.model.Song;
import frontend.model.SoundEffects;

import javax.swing.*;
import java.awt.*;

public class MenuBar {

    private final JMenuBar menubar;
    private final EventsMenuBar eventsFrame;

    public MenuBar(Container contentPane, Song song, SoundEffects soundEffects) {
        this.menubar = new JMenuBar();
        this.eventsFrame = new EventsMenuBar(contentPane, song, soundEffects);
    }

    public void createNavigation(String name) {
        JMenu menu = new JMenu(name);
        menubar.add(menu);
        createButton(menu, name);
    }

    private void createButton(JMenu menu, String name) {
        String nameItem = validName(name);
        JMenuItem item = new JMenuItem(nameItem);
        item.addActionListener(e -> createEvent(name));
        menu.add(item);
    }

    private String validName(String name) {
        if (name.equals("Musica") || name.equals("Efeitos"))
            return "On/Off";
        else
            return name;
    }

    private void createEvent(String name) {
        eventsFrame.event(name);
    }

    public JMenuBar getMenubar() {
        return menubar;
    }
}
