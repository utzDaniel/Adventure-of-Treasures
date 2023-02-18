package view;

import model.builder.item.Item;
import model.Player;
import model.Song;
import model.SoundEffects;
import model.builder.map.MapGame;
import settings.SettingsItem;
import settings.SettingsJFrame;
import settings.SettingsMapGame;

import javax.swing.*;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class InterfaceGame {

    private final JFrame frame;
    private final JLabel mapGameJLabel;
    private final JLabel playerJLabel;
    private final Song song;
    private final SoundEffects soundEffects;

    public InterfaceGame() {
        frame = new JFrame();
        this.playerJLabel =  Player.getInstance().getJLabel();
        mapGameJLabel = MapGame.getJLabel();
        song = new Song();
        soundEffects = new SoundEffects();
        settingsFrame();
        setJLabelPlayer();
        createJLabelMapGame();
        //history();
    }

    public JFrame getFrame() {
        return frame;
    }

    public JLabel getMapGameJLabel() {
        return mapGameJLabel;
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

    private void setJLabelPlayer() {
        frame.getContentPane().add(playerJLabel);
    }

    private void createJLabelMapGame() {
        frame.getContentPane().add(mapGameJLabel);
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

    public void playEffects(String effect, String itemName) {
        if (Objects.isNull(itemName))
            soundEffects.play(effect);
        else
            soundEffects.play(effect, itemName);
    }
}
