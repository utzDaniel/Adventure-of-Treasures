import model.*;
import model.builder.item.*;
import repository.CreateMapGame;
import view.InterfaceGame;
import controller.Keyboard;

import java.util.ArrayList;

public class Game {

    private final Player player;
    private final InterfaceGame interfaceGame;
    private final Song song;
    private final SoundEffects soundEffects;

    public Game() {
        player = Player.getInstance();
        initialPlayer();
        interfaceGame = new InterfaceGame();
        song = interfaceGame.getSong();
        soundEffects = interfaceGame.getSoundEffects();
        song.play(player.getCurrentMap().getName());
        new Keyboard(interfaceGame, song, soundEffects).run();
    }

    private void initialPlayer() {
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for (Item item : createMapGame.getItemInvisiblePlayer()) {
            player.getInventory().setItemInvisible(item);
        }
    }

    public static void main(String[] args) {
        new Game();
    }

    private void aa() {


    }
}


