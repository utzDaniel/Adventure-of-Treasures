package frontend.service;

import backend.controller.interfaces.IComponentInfo;
import frontend.enums.ComponentsProperties;
import frontend.enums.NameJMenu;
import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.model.component.ComponentFactory;
import backend.controller.interfaces.IActionResponse;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class InterfaceGame {

    private final JFrame frame;
    private final Song song;
    private final SoundEffects soundEffects;
    private final List<JLabel> components;

    public InterfaceGame(IActionResponse action) {
        this.frame = ComponentFactory.getJFrame();
        this.components = ComponentFactory.getJLabel(action.components());
        this.song = new Song();
        this.song.play(action.song());
        this.soundEffects = new SoundEffects();
        settingsFrame();
        if (Objects.nonNull( this.components))  this.components.forEach(jLabel ->  this.frame.getContentPane().add(jLabel));
        this.frame.getContentPane().repaint();
        //history();
    }

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getMapGameJLabel() {
        return this.components.get(1);
    }

    public JLabel getPlayerJLabel() {
        return this.components.get(0);
    }

    public void updateComponentsMove(IActionResponse action) {
        action.components().forEach(info -> {
            if (info.name().equals(ComponentsProperties.PLAYER.name())) {
                getPlayerJLabel().setIcon(new ImageIcon(info.image()));
                getPlayerJLabel().setLocation(info.point());
            }else if (info.name().equals(ComponentsProperties.MAP.name())) {
                getMapGameJLabel().setIcon(new ImageIcon(info.image()));
            }
        });
        var lisItem = action.components().stream()
                .filter(v -> v.name().equals(ComponentsProperties.ITEM.name()))
                .toList();

        if (Objects.nonNull(action.song())) {
            this.song.play(action.song());
        }
        clearJLabelItens();
        getMapGameJLabel().repaint();
        if(lisItem.isEmpty()) return;
        var index = 1;
        setItensJLabel(lisItem, index);

    }

    private void settingsFrame() {
        var visible = true;
        createJMenuBar();
        frame.setVisible(visible);
    }

    private void createJMenuBar() {
        var menuBar = ComponentFactory.getJMenuBar();
        var events = new EventsMenuBar(frame.getContentPane(), song, soundEffects);

        Arrays.stream(NameJMenu.values()).forEach(v -> {
            var name = v.getName();
            var menu = ComponentFactory.getJMenu(name);
            menuBar.add(menu);
            var item = ComponentFactory.getJMenuItem(name);
            item.addActionListener(e -> events.action(name));
            menu.add(item);
        });

        frame.setJMenuBar(menuBar);
    }

    public void clearJLabelItens() {
        Arrays.stream(frame.getContentPane().getComponents())
                .filter(component -> component instanceof JLabel && component.getName().equals(ComponentsProperties.ITEM.name()))
                .forEach(component -> frame.getContentPane().remove(component));
        frame.getContentPane().repaint();
    }

    public void setItensJLabel(List<IComponentInfo> itens, int index) {
        var jLabelList = ComponentFactory.getJLabel(itens);
        jLabelList.forEach(jLabel -> frame.getContentPane().add(jLabel, index));
    }

    public Song getSong() {
        return song;
    }

    public SoundEffects getSoundEffects() {
        return soundEffects;
    }

    public void playEffects(String command, String effect) {
        if (Objects.isNull(effect))
            soundEffects.play(command);
        else
            soundEffects.play(effect);
    }
}
