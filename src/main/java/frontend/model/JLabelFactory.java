package frontend.model;

import frontend.settings.SettingsMapGame;
import frontend.settings.SettingsPlayer;

import javax.swing.*;

public final class JLabelFactory {

    public static JLabel getPlayer(){
        var jLabel = new JLabel();
        SettingsPlayer settingsPlayer = new SettingsPlayer();
        jLabel.setIcon(settingsPlayer.getIcon());
        jLabel.setName(settingsPlayer.getName());
        jLabel.setBounds(settingsPlayer.getRectangle());
        return jLabel;
    }

    public static JLabel getMapGame(){
        var jLabel = new JLabel();
        SettingsMapGame settingsMapGame = new SettingsMapGame();
        jLabel.setIcon(settingsMapGame.getIcon());
        jLabel.setName(settingsMapGame.getName());
        jLabel.setBounds(settingsMapGame.getRectangle());
        return jLabel;
    }

}
