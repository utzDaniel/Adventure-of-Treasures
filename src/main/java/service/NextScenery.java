package service;

import model.*;
import view.InterfaceGame;

public class NextScenery {

    private final Player player;
    private final InterfaceGame interfaceGame;
    private final Song song;

    public NextScenery(Player player, InterfaceGame interfaceGame, Song song) {
        this.player = player;
        this.interfaceGame = interfaceGame;
        this.song = song;
    }

    public void run(String direction){
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit(direction);
        if (nextScenery != null) {
            player.setCurrentMap(nextScenery);
            newPosition(direction);
            interfaceGame.getMapGameJLabel().setIcon(nextScenery.getImagemIcon());
            song.play(player.getCurrentMap().getName());
        }
        updateItensMapGame();
    }

    private void updateItensMapGame() {
        interfaceGame.clearItensJLabel();
        for (Item itens : player.getCurrentMap().getItemVisible()) {
            interfaceGame.setItensJLabel(itens,1);
        }
        interfaceGame.getMapGameJLabel().repaint();
    }

    private void newPosition(String direction) {
        switch (direction) {
            case "norte" -> player.setPositionPlayerY(interfaceGame.getMapGameJLabel().getHeight() - 70, interfaceGame.getPlayerJLabel());
            case "sul" -> player.setPositionPlayerY(10, interfaceGame.getPlayerJLabel());
            case "oeste" -> player.setPositionPlayerX(interfaceGame.getMapGameJLabel().getWidth() - 50, interfaceGame.getPlayerJLabel());
            case "leste" -> player.setPositionPlayerX(10, interfaceGame.getPlayerJLabel());
        }
    }
}
