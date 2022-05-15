import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
        keyboard();
    }

    private void initialPlayer(){
        CreateMapGame createMapGame = new CreateMapGame();
        player.setCurrentMap(createMapGame.getInitialScenery());
        for(Item item :createMapGame.getItemInvisiblePlayer() ){
            player.setItem(item);
        }
    }

    private void keyboard() {
        interfaceGame.getFrame().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == 38) {
                    if (player.getPositionPlayerY() > 0) {
                        player.movePositionPlayer("norte", interfaceGame.getPlayerJLabel());
                    } else {
                        nextScenery("norte");
                    }
                } else if (e.getKeyCode() == 40) {
                    if (player.getPositionPlayerY() < interfaceGame.getMapGameJLabel().getHeight() - 50) {
                        player.movePositionPlayer("sul", interfaceGame.getPlayerJLabel());
                    } else {
                        nextScenery("sul");
                    }
                } else if (e.getKeyCode() == 37) {
                    if (player.getPositionPlayerX() > 0) {
                        player.movePositionPlayer("oeste", interfaceGame.getPlayerJLabel());
                    } else {
                        nextScenery("oeste");
                    }
                } else if (e.getKeyCode() == 39) {
                    if (player.getPositionPlayerX() < interfaceGame.getMapGameJLabel().getWidth() - 30) {
                        player.movePositionPlayer("leste", interfaceGame.getPlayerJLabel());
                    } else {
                        nextScenery("leste");
                    }
                } else if (e.getKeyCode() == 97) {
                    nextDoor();
                } else if (e.getKeyCode() == 98) {
                    take();
                } else if (e.getKeyCode() == 99) {
                    if(!player.isInventoty()){
                        new InterfaceInventory(interfaceGame, player, soundEffects);
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {


            }
        });
    }

    private void nextDoor() {
        Door door = player.getCurrentMap().getDoorMap(player.getPositionPlayerX(), player.getPositionPlayerY());
        if (door != null && door.isOpen()) {
            MapGame mapGame = player.getCurrentMap().getMapDoor(door);
            if (door.getPositionInsideX() == player.getPositionPlayerX() && door.getPositionInsideY() == player.getPositionPlayerY()) {
                player.setPositionPlayerX(door.getPositionOutsideX(), interfaceGame.getPlayerJLabel());
                player.setPositionPlayerY(door.getPositionOutsideY(), interfaceGame.getPlayerJLabel());
            } else {
                player.setPositionPlayerX(door.getPositionInsideX(), interfaceGame.getPlayerJLabel());
                player.setPositionPlayerY(door.getPositionInsideY(), interfaceGame.getPlayerJLabel());
            }
            player.setCurrentMap(mapGame);
            interfaceGame.getMapGameJLabel().setIcon(mapGame.getImagemIcon());
        }else if(player.getPositionPlayerX() == 710 && player.getPositionPlayerY() == 280){
            finish();
        }
        updateItensMapGame();
    }

    private void nextScenery(String direction) {
        Scenery nextScenery = ((Scenery) player.getCurrentMap()).getExit(direction);
        if (nextScenery != null) {
            player.setCurrentMap(nextScenery);
            newPosition(direction);
            interfaceGame.getMapGameJLabel().setIcon(nextScenery.getImagemIcon());
            song.play(player.getCurrentMap().getName());
        }
        updateItensMapGame();
    }

    private void newPosition(String direction) {
        switch (direction) {
            case "norte" -> player.setPositionPlayerY(interfaceGame.getMapGameJLabel().getHeight() - 70, interfaceGame.getPlayerJLabel());
            case "sul" -> player.setPositionPlayerY(10, interfaceGame.getPlayerJLabel());
            case "oeste" -> player.setPositionPlayerX(interfaceGame.getMapGameJLabel().getWidth() - 50, interfaceGame.getPlayerJLabel());
            case "leste" -> player.setPositionPlayerX(10, interfaceGame.getPlayerJLabel());
        }
    }

    private void updateItensMapGame() {
        interfaceGame.clearItensJLabel();
        for (Item itens : player.getCurrentMap().getItemVisible()) {
            interfaceGame.setItensJLabel(itens,1);
        }
        interfaceGame.getMapGameJLabel().repaint();
    }

    private void take() {
        Item item = player.getItemMapGame();
        if (item != null) {
            if(player.validMaxCapacity(item)){
                soundEffects.play("pegar");
                updateItensMapGame();
            }
            else {
                soundEffects.play("erro");
            }
        }

    }

    public void finish(){
        song.closePlay();
        soundEffects.play("finish");
        System. exit(0);
    }

    public static void main(String[] args) {
        new Game();
    }
}

