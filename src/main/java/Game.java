import model.*;
import repository.CreateMapGame;
import view.InterfaceGame;
import view.Keyboard;

public class Game {

    private final Player player;
    private final InterfaceGame interfaceGame;
    private final Song song;
    private final SoundEffects soundEffects;

    public Game() {
        player = new Player();
        initialPlayer();
        interfaceGame = new InterfaceGame(player.getCurrentMap().getImagemIcon());
        song = interfaceGame.getSong();
        soundEffects = interfaceGame.getSoundEffects();
        song.play(player.getCurrentMap().getName());
        new Keyboard(interfaceGame, player, song, soundEffects).run();
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
}

