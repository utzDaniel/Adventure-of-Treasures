package view;

import model.Item;
import model.SettingsPlayer;
import model.Song;
import model.SoundEffects;

import javax.swing.*;
import java.awt.*;
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
        frame = new JFrame("Adventure of Treasures - Version 1.0");
        playerJLabel = new JLabel();
        mapGameJLabel = new JLabel(imageMapGame);
        itensJLabel = new ArrayList<>();
        song = new Song();
        soundEffects = new SoundEffects();
        settingsFrame();
        addPlayerJlabel();
        addMapGameJLabel();
        history();
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
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(816, 660);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation(d.width / 2 - frame.getWidth() / 2, d.height / 2 - frame.getHeight() / 2);
        makeMenuBar(frame);
        frame.setVisible(true);
        frame.setLayout(null);
    }

    private void makeMenuBar(JFrame frame) {
        JMenuBar menubar = new JMenuBar();
        frame.setJMenuBar(menubar);

        JMenu menu;
        JMenuItem item;

        menu = new JMenu("Historia");
        menubar.add(menu);
        item = new JMenuItem("Historia");
        item.addActionListener(e -> history());
        menu.add(item);

        menu = new JMenu("Musica");
        menubar.add(menu);
        item = new JMenuItem("On/Off");
        item.addActionListener(e -> music());
        menu.add(item);

        menu = new JMenu("Efeitos");
        menubar.add(menu);
        item = new JMenuItem("On/Off");
        item.addActionListener(e -> effects());
        menu.add(item);

        menu = new JMenu("Comandos");
        menubar.add(menu);
        item = new JMenuItem("Comandos");
        item.addActionListener(e -> command());
        menu.add(item);

        menu = new JMenu("Ajuda");
        menubar.add(menu);
        item = new JMenuItem("Ajuda");
        item.addActionListener(e -> help());
        menu.add(item);

        menu = new JMenu("Sair");
        menubar.add(menu);
        item = new JMenuItem("Sair");
        item.addActionListener(e -> quit());
        menu.add(item);

    }

    private void addPlayerJlabel() {
        SettingsPlayer settingsPlayer = new SettingsPlayer();
        playerJLabel.setIcon(settingsPlayer.ImageInitial());
        frame.add(playerJLabel);
        playerJLabel.setBounds(settingsPlayer.positionInitialX(), settingsPlayer.positionInitialY(), 32, 47);

    }

    private void addMapGameJLabel() {
        frame.add(mapGameJLabel);
        mapGameJLabel.setBounds(0, 0, 800, 600);
    }

    public void clearItensJLabel(){
        for (JLabel itens: itensJLabel){
            frame.remove(itens);
        }
        itensJLabel.clear();
    }

    public void setItensJLabel(Item item, int index){
        itensJLabel.add(new JLabel(item.getImagemIcon()));
        if(itensJLabel.size()>0){
            frame.add(itensJLabel.get(itensJLabel.size()-1),index);
            itensJLabel.get(itensJLabel.size()-1).setBounds(item.getPositionItemX()-30, item.getPositionItemY()-10, 100, 100);
        }
    }

    private void history() {
        JOptionPane.showMessageDialog(frame,
                """
                        Bem-vindo ao Adventure of Treasures!
                                                
                        Você é um historiador, que adora se aventurar a procura de tesouros antigos, sua nova busca
                        são os antigos tesouros templários. 1 semana atrás, após muitas pesquisas surgiu uma pista
                        sobre o paradeiro do tesouro, ela aponta para a Vila Fantasma localizada na Ilha Voraste,
                        um antigo lugar esquecido após o fim da época das Cruzadas.
                                                
                        Neste momento você chegou á ilha e tem como único objetivo encontrar o tesouro.
                        """,
                "Historia",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public Song getSong() {
        return song;
    }

    private void music() {
        song.onPlayer();
    }

    public SoundEffects getSoundEffects() {
        return soundEffects;
    }

    private void effects() { soundEffects.onPlayer(); }

    private void command() {
        JOptionPane.showMessageDialog(frame,
                """
                        ↑ - Mover para o Norte
                        ← - Mover para o Oeste
                        → - Mover para o Leste
                        ↓ - Mover para o Sul
                        1 - Abrir porta e Interagir com o Capitao do barco
                        2 - Pegar item
                        3 - Abrir inventario
                        ""","Comandos",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void help() {
        JOptionPane.showMessageDialog(frame,
                """
                        Você está na Ilha Voraste
                        Seu objetivo é encontrar o tesouro templário,\s
                        depois entrar no barco e interagir com o Capitao do Navio""",
                "Ajuda",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private void quit() {
        System.exit(0);
    }
}
