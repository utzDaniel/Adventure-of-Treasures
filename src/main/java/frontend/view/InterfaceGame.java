package frontend.view;

import backend.model.builder.item.Item;
import backend.model.builder.map.MapGame;
import frontend.model.Song;
import frontend.model.SoundEffects;
import frontend.settings.SettingsItem;
import frontend.settings.SettingsJFrame;
import rules.model.IMovePlayerDTO;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class InterfaceGame {

    private final JFrame frame;
    private final Song song;
    private final SoundEffects soundEffects;
    private final List<JLabel> components;

    public InterfaceGame(List<JLabel> components) {
        this.components = components;
        frame = new JFrame();
        song = new Song();
        soundEffects = new SoundEffects();
        settingsFrame();
        if(Objects.nonNull(components)) components.forEach(jLabel -> frame.getContentPane().add(jLabel));
        frame.getContentPane().add(components.get(1));
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

    public void updateComponentsMove(IMovePlayerDTO movePlayerDTO) {
        components.forEach(
                jLabel -> {
                    if (jLabel.getName().equals("player")) {
                        jLabel.setIcon(movePlayerDTO.getIconPlayer());
                        jLabel.setLocation(movePlayerDTO.getCoordinatePlayer().getPoint());
                    }
                    if (jLabel.getName().equals("mapa")) {
                        jLabel.setIcon(movePlayerDTO.getIconMap());
                    }
                }
        );
        if (Objects.nonNull(movePlayerDTO.getSongMap())) {
            this.song.play(movePlayerDTO.getSongMap());
        }
        if (Objects.nonNull(movePlayerDTO.getItens())) {
            clearJLabelItens();
            setItensJLabel(movePlayerDTO.getItens(), movePlayerDTO.getIndexItens());
            getMapGameJLabel().repaint();
        }
    }

    private void settingsFrame() {
        SettingsJFrame settingsJFrame = new SettingsJFrame();
        frame.setTitle(settingsJFrame.getTitulo());
        frame.setDefaultCloseOperation(settingsJFrame.closeOperation());
        frame.setSize(settingsJFrame.getWidth(), settingsJFrame.getHeight());
        frame.setLocationRelativeTo(settingsJFrame.getLocationRelativeTo());
        frame.setLayout(settingsJFrame.getLayout());
        frame.setResizable(settingsJFrame.getResizable());
        frame.getContentPane().setLayout(settingsJFrame.getLayout());
        createJMenuBar();
        frame.setVisible(settingsJFrame.isVisible());
    }

    private void createJMenuBar() {
        MenuBar menuBar = new MenuBar(frame.getContentPane(), song, soundEffects);
        frame.setJMenuBar(menuBar.getMenubar());
        List.of("Historia", "Comandos", "Ajuda", "Musica", "Efeitos", "Sair")
                .forEach(menuBar::createNavigation);
    }

    public void clearJLabelItens() {
        Arrays.stream(frame.getContentPane().getComponents())
                .filter(component -> component instanceof JLabel && component.getName().equals("item"))
                .forEach(component -> frame.getContentPane().remove(component));
        frame.getContentPane().repaint();
    }

    public void setItensJLabel(List<Item> itens, int index) {
        SettingsItem settingsItem = new SettingsItem();
        itens.forEach(item -> {
            JLabel label = new JLabel(item.getIcon());
            label.setName("item");
            frame.getContentPane().add(label, index);
            label.setBounds(item.getLocation().getX()-30,
                    item.getLocation().getY()-10,
                    settingsItem.labelWidth(), settingsItem.labelHeight());
        });
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
