package view;

import model.builder.item.Item;
import model.Player;
import model.Song;
import model.SoundEffects;
import model.builder.map.MapGame;
import settings.SettingsJFrame;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class InterfaceGame {

    private final JFrame frame;
    private final Song song;
    private final SoundEffects soundEffects;

    public InterfaceGame() {
        frame = new JFrame();
        song = new Song();
        soundEffects = new SoundEffects();
        settingsFrame();
        frame.getContentPane().add(Player.getInstance().getJLabel());
        frame.getContentPane().add(MapGame.getJLabel());
        //history();
    }

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getMapGameJLabel() {
        return MapGame.getJLabel();
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
        itens.forEach(item ->
            frame.getContentPane().add(item.getJLabel(), index));
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
