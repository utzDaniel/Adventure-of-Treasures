package frontend.service;

import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.model.component.ComponentFactory;
import backend.controller.interfaces.IItemDTO;
import backend.controller.interfaces.IMoveResponse;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public final class InterfaceGame {

    private final JFrame frame;
    private final Song song;
    private final SoundEffects soundEffects;
    private final List<JLabel> components;

    public InterfaceGame(List<JLabel> components) {
        this.components = components;
        frame = ComponentFactory.getJFrame();
        song = new Song();
        soundEffects = new SoundEffects();
        settingsFrame();
        if (Objects.nonNull(components)) components.forEach(jLabel -> frame.getContentPane().add(jLabel));
        frame.getContentPane().repaint();
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

    public void updateComponentsMove(IMoveResponse movePlayerVO) {
        components.forEach(
                jLabel -> {
                    if (jLabel.getName().equals("player")) {
                        jLabel.setIcon(new ImageIcon(movePlayerVO.iconPlayer()));
                        jLabel.setLocation(new Point(movePlayerVO.coordinatePlayer().x(), movePlayerVO.coordinatePlayer().y()));
                    }
                    if (jLabel.getName().equals("mapa")) {
                        jLabel.setIcon(new ImageIcon(movePlayerVO.iconMap()));
                    }
                }
        );
        if (Objects.nonNull(movePlayerVO.songMap())) {
            this.song.play(movePlayerVO.songMap());
        }
        if (Objects.nonNull(movePlayerVO.itens())) {
            clearJLabelItens();
            setItensJLabel(movePlayerVO.itens(), movePlayerVO.indexItens());
            getMapGameJLabel().repaint();
        }
    }

    private void settingsFrame() {
        var visible = true;
        createJMenuBar();
        frame.setVisible(visible);
    }

    private void createJMenuBar() {
        var menuBar = ComponentFactory.getJMenuBar();
        var events = new EventsMenuBar(frame.getContentPane(), song, soundEffects);
        List.of("Historia", "Comandos", "Ajuda", "Musica", "Efeitos", "Sair")
                .forEach(name -> {
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
                .filter(component -> component instanceof JLabel && component.getName().equals("item"))
                .forEach(component -> frame.getContentPane().remove(component));
        frame.getContentPane().repaint();
    }

    public void setItensJLabel(List<IItemDTO> itens, int index) {
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
            soundEffects.play(commandEffects(command));
        else
            soundEffects.play(effect);
    }

    private String commandEffects(String command) {
        return String.format("src/main/resources/audio/effects/%s.wav",
                switch (command) {
                    case "pegar", "remover ", "finish" -> command;
                    default -> "erro";
                });
    }
}
