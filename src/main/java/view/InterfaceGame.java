package view;

import model.Item;
import model.Song;
import model.SoundEffects;
import settings.SettingsItem;
import settings.SettingsJFrame;
import settings.SettingsMapGame;
import settings.SettingsPlayer;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class InterfaceGame {

    private final JFrame frame;
    private final JLabel mapGameJLabel;
    private final JLabel playerJLabel;
    private final List<JLabel> itensJLabel;
    private final Song song;
    private final SoundEffects soundEffects;

    public InterfaceGame(ImageIcon imageMapGame) {
        frame = new JFrame();
        playerJLabel = new JLabel();
        mapGameJLabel = new JLabel(imageMapGame);
        itensJLabel = new ArrayList<>();
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

    public JLabel getPlayerJLabel() {
        return playerJLabel;
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
        frame.getContentPane().add(playerJLabel);
        playerJLabel.setBounds(settingsPlayer.positionInitialX(), settingsPlayer.positionInitialY(),
                settingsPlayer.labelWidth(), settingsPlayer.labelHeight());

    }

    private void createJLabelMapGame() {
        SettingsMapGame settingsMapGame = new SettingsMapGame();
        mapGameJLabel.setText("mapa");
        frame.getContentPane().add(mapGameJLabel);
        mapGameJLabel.setBounds(settingsMapGame.labelPositionX(), settingsMapGame.labelPositionY(),
                settingsMapGame.labelWidth(), settingsMapGame.labelHeight());
    }

    public void clearJLabelItens() {
        for (JLabel itens : itensJLabel)
            frame.getContentPane().remove(itens);
        itensJLabel.clear();
    }

    public void setItensJLabel(Item item, int index) {
        SettingsItem settingsItem = new SettingsItem();
        int ultimo = itensJLabel.size();

        itensJLabel.add(
                new JLabel(
                        item.getImagemIcon()
                )
        );
        frame.getContentPane().add(itensJLabel.get(ultimo), index);

        itensJLabel.get(ultimo)
                .setBounds(settingsItem.labelPositionX(item.getPositionItemX()),
                        settingsItem.labelPositionY(item.getPositionItemY()),
                        settingsItem.labelWidth(), settingsItem.labelHeight());

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
