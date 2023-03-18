import controller.Keyboard;
import model.*;
import model.builder.item.*;
import repository.RepositoryItem;
import repository.RepositoryMapGame;
import view.InterfaceGame;

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
        song.play(player.getCurrentMap().getSong());
        new Keyboard(interfaceGame, song, soundEffects).run();
    }

    private void initialPlayer() {
        RepositoryMapGame createMapGame = RepositoryMapGame.getInstance();
        player.setCurrentMap(createMapGame.get("cais"));
        for (Item item : RepositoryItem.getInstance().getItemInvisible()) {
            player.getInventory().setItemInvisible(item);
        }
    }

    public static void main(String[] args) {
        new Game();
    }

}


