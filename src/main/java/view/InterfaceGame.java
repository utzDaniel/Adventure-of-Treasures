package view;

import model.Item;
import model.Player;
import model.Song;
import model.SoundEffects;
import settings.SettingsItem;
import settings.SettingsJFrame;
import settings.SettingsMapGame;
import settings.SettingsPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class InterfaceGame {

    private final JFrame frame;
    private final JLabel mapGameJLabel;
    private final JLabel playerJLabel;
    private final Song song;
    private final SoundEffects soundEffects;

    public InterfaceGame(Player player) {
        frame = new JFrame();
        this.playerJLabel = player.getJLabel();
        mapGameJLabel = new JLabel(player.getCurrentMap().getImagemIcon());
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
        var navigations = List.of("Historia", "Comandos", "Ajuda", "Musica", "Efeitos", "Sair");
        navigations.forEach(menuBar::createNavigation);
    }

    private void createJLabelPlayer() {
        SettingsPlayer settingsPlayer = new SettingsPlayer();
        playerJLabel.setIcon(settingsPlayer.ImageInitial());
        playerJLabel.setName("player");
        frame.getContentPane().add(playerJLabel);
        playerJLabel.setBounds(settingsPlayer.positionInitialX(), settingsPlayer.positionInitialY(),
                settingsPlayer.labelWidth(), settingsPlayer.labelHeight());

    }

    private void createJLabelMapGame() {
        SettingsMapGame settingsMapGame = new SettingsMapGame();
        mapGameJLabel.setName("mapa");
        frame.getContentPane().add(mapGameJLabel);
        mapGameJLabel.setBounds(settingsMapGame.labelPositionX(), settingsMapGame.labelPositionY(),
                settingsMapGame.labelWidth(), settingsMapGame.labelHeight());
    }

    public void clearJLabelItens() {
        for(Component component : frame.getContentPane().getComponents()){
            if(component instanceof JLabel && component.getName().equals("item")){
                frame.getContentPane().remove(component);
            }
        }
    }

    public void setItensJLabel(List<Item> itens, int index) {
        SettingsItem settingsItem = new SettingsItem();
        for (Item item : itens) {
            JLabel label = new JLabel(item.getImagemIcon());
            label.setName("item");
            frame.getContentPane().add(label, index);
            label.setBounds(settingsItem.labelPositionX(item.getPositionItemX()),
                    settingsItem.labelPositionY(item.getPositionItemY()),
                    settingsItem.labelWidth(), settingsItem.labelHeight());
        }
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
