package frontend.view;

import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.model.component.ComponentFactory;
import rules.interfaces.IItem;
import rules.interfaces.IMovePlayer;

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

    public void updateComponentsMove(IMovePlayer movePlayerVO) {
        components.forEach(
                jLabel -> {
                    if (jLabel.getName().equals("player")) {
                        jLabel.setIcon(new ImageIcon(movePlayerVO.getIconPlayer()));
                        jLabel.setLocation(new Point(movePlayerVO.getCoordinatePlayer().getX(), movePlayerVO.getCoordinatePlayer().getY()));
                    }
                    if (jLabel.getName().equals("mapa")) {
                        jLabel.setIcon(new ImageIcon(movePlayerVO.getIconMap()));
                    }
                }
        );
        if (Objects.nonNull(movePlayerVO.getSongMap())) {
            this.song.play(movePlayerVO.getSongMap());
        }
        if (Objects.nonNull(movePlayerVO.getItens())) {
            clearJLabelItens();
            setItensJLabel(movePlayerVO.getItens(), movePlayerVO.getIndexItens());
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

    public void setItensJLabel(List<IItem> itens, int index) {
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
        return "src/main/resources/audio/effects/" +
                switch (command) {
                    case "pegar" -> "pegar.wav";
                    case "remover" -> "remover.wav";
                    case "finish" -> "finish.wav";
                    default -> "erro.wav";
                };
    }
}
