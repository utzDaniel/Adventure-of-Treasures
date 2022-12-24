package view;

import model.Item;
import model.Song;
import model.SoundEffects;
import settings.SettingsItem;
import settings.SettingsJFrame;
import settings.SettingsMapGame;
import settings.SettingsPlayer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

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
        frame.setLocation(settingsJFrame.getEixoX(), settingsJFrame.getEixoY());
        createJMenuBar();
        frame.setVisible(settingsJFrame.isVisible());
        frame.setLayout(settingsJFrame.getLayout());
    }

    private void createJMenuBar() {
        MenuBar menuBar = new MenuBar(frame, song, soundEffects);
        frame.setJMenuBar(menuBar.getMenubar());
        menuBar.createNavigation("Historia");
        menuBar.createNavigation("Comandos");
        menuBar.createNavigation("Ajuda");
        menuBar.createNavigation("Musica");
        menuBar.createNavigation("Efeitos");
        menuBar.createNavigation("Sair");
    }

    private void createJLabelPlayer() {
        SettingsPlayer settingsPlayer = new SettingsPlayer();
        playerJLabel.setIcon(settingsPlayer.ImageInitial());
        frame.add(playerJLabel);
        playerJLabel.setBounds(settingsPlayer.positionInitialX(), settingsPlayer.positionInitialY(),
                settingsPlayer.labelWidth(), settingsPlayer.labelHeight());

    }

    private void createJLabelMapGame() {
        SettingsMapGame settingsMapGame = new SettingsMapGame();
        frame.add(mapGameJLabel);
        mapGameJLabel.setBounds(settingsMapGame.labelPositionX(), settingsMapGame.labelPositionY(),
                settingsMapGame.labelWidth(), settingsMapGame.labelHeight());
    }

    public void clearJLabelItens() {
        for (JLabel itens : itensJLabel)
            frame.remove(itens);
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
        frame.add(itensJLabel.get(ultimo), index);

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
}
