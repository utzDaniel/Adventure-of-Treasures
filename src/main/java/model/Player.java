package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public final class Player {
    private int positionPlayerX;
    private int positionPlayerY;
    private String direction;
    private final List<MovePlayer> movePlayer;
    private MapGame currentMapGame;
    private final Inventory inventory;

    public Player() {
        SettingsPlayer settingsPlayer = new SettingsPlayer();
        this.positionPlayerX = settingsPlayer.positionInitialX();
        this.positionPlayerY = settingsPlayer.positionInitialY();
        movePlayer = new ArrayList<>(settingsPlayer.createMovePlayer());
        this.currentMapGame = null;
        this.inventory = new Inventory();
    }

    public Inventory getInventory() {
        return inventory;
    }

    public int getPositionPlayerX() {
        return positionPlayerX;
    }

    public void setPositionPlayerX(int positionPlayerX, JLabel playerJLabel) {
        this.positionPlayerX = positionPlayerX;
        setLocation(playerJLabel);
    }

    public int getPositionPlayerY() {
        return positionPlayerY;
    }

    public void setPositionPlayerY(int positionPlayerY, JLabel playerJLabel) {
        this.positionPlayerY = positionPlayerY;
        setLocation(playerJLabel);
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void movePositionPlayer(String direction, JLabel playerJLabel) {
        int positionX = positionPlayerX;
        int positionY = positionPlayerY;
        for (MovePlayer player : movePlayer) {
            if (player.getDirection().equals(direction)) {
                if (player.isPositionX()) {
                    this.positionPlayerX += player.getStep();
                } else {
                    this.positionPlayerY += player.getStep();
                }
                if (!currentMapGame.mapGameLimits(positionPlayerX, positionPlayerY)) {
                    this.positionPlayerX = positionX;
                    this.positionPlayerY = positionY;
                } else {
                    setLocation(playerJLabel);
                }
                setDirection(direction);
                setIcon(player.getImageIcon(), playerJLabel);
            }
        }
    }

    public Item getItemMapGame() {
        int positionX = positionPlayerX;
        int positionY = positionPlayerY;
        for (MovePlayer player : movePlayer) {
            if (player.getDirection().equals(getDirection())) {
                if (player.isPositionX()) {
                    positionX += player.getStep();
                } else {
                    positionY += player.getStep();
                }
                return currentMapGame.getItemMapGame(positionX, positionY);
            }
        }
        return null;
    }

    private void setIcon(ImageIcon imageIcon, JLabel playerJLabel) {
        playerJLabel.setIcon(imageIcon);
    }

    private void setLocation(JLabel playerJLabel) {
        playerJLabel.setLocation(this.positionPlayerX, this.positionPlayerY);
    }

    public void setCurrentMap(MapGame currentScenery) {
        this.currentMapGame = currentScenery;
    }

    public MapGame getCurrentMap() {
        return this.currentMapGame;
    }


    //private pois tem que realizar uma verificação
    public void takeItem(Item item) {
        this.inventory.setItem(item);
    }

    //alteração baixo acoplamento
    public void setItem(Item item) {
        this.inventory.getItem().add(item);
    }

    public boolean validMaxCapacity(Item item) {
        if (!(inventory.validMaxCapacity(item))) return false;
        currentMapGame.removeItem(item);
        return true;
    }

    public Item getItemInventory(String nameItem) {
        return inventory.getItemInventory(nameItem);
    }

    public void removeItem(Item item) {
        inventory.removeItem(item);
    }

    public boolean removeItemInventory(Item item) {
        if(!(inventory.removeItemInventory(item))) return false;
        currentMapGame.setItemRemove(item, positionPlayerX, positionPlayerY);
        return true;
    }

    public List<Item> getItemVisible() {
        return inventory.getItemVisible();
    }

    public List<Item> getItemInvisible() {
        return inventory.getItemInvisible();
    }

}
