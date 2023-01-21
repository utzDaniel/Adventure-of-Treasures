package view;

import model.builder.item.Item;
import model.Player;
import model.Song;
import model.SoundEffects;
import settings.SettingsItem;
import settings.SettingsJFrame;
import settings.SettingsMapGame;
import settings.SettingsPlayer;

import javax.swing.*;
import java.awt.*;
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
        mapGameJLabel = new JLabel();
        song = new Song();
        soundEffects = new SoundEffects();
        settingsFrame();
        createJLabelPlayer();
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

    private void createJLabelPlayer() {
        SettingsPlayer settingsPlayer = new SettingsPlayer();
        playerJLabel.setIcon(settingsPlayer.getIcon());
        playerJLabel.setName(settingsPlayer.getName());
        frame.getContentPane().add(playerJLabel);
        playerJLabel.setBounds(settingsPlayer.getRectangle());

    }

    private void createJLabelMapGame() {
        SettingsMapGame settingsMapGame = new SettingsMapGame();
        mapGameJLabel.setIcon(settingsMapGame.getIcon());
        mapGameJLabel.setName(settingsMapGame.getName());
        frame.getContentPane().add(mapGameJLabel);
        mapGameJLabel.setBounds(settingsMapGame.getRectangle());
    }

    public void clearJLabelItens() {
        Arrays.stream(frame.getContentPane().getComponents())
                .filter(component -> component instanceof JLabel && component.getName().equals("item"))
                .forEach(component -> frame.getContentPane().remove(component));
    }

    public void setItensJLabel(List<Item> itens, int index) {
        SettingsItem settingsItem = new SettingsItem();
        itens.forEach(item -> {
            JLabel label = new JLabel(item.getImage());
            label.setName("item");
            frame.getContentPane().add(label, index);
            label.setBounds(settingsItem.labelPositionX(item.getPositionX()),
                    settingsItem.labelPositionY(item.getPositionY()),
                    settingsItem.labelWidth(), settingsItem.labelHeight());
        });
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
