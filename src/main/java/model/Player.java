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

    private String getDirection() {
        return this.direction;
    }

    private void setDirection(String direction) {
        this.direction = direction;
    }

    public void walk(String direction, JLabel playerJLabel) {
        int positionX = positionPlayerX;
        int positionY = positionPlayerY;
        for (MovePlayer player : movePlayer) {
            if (this.direction.equals(direction)) {
                if (player.isPositionX()) {
                    this.positionPlayerX += player.getStep();
                } else {
                    this.positionPlayerY += player.getStep();
                }
                if (!this.currentMapGame.mapGameLimits(this.positionPlayerX, this.positionPlayerY)) {
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
        int positionX = this.positionPlayerX;
        int positionY = this.positionPlayerY;
        for (MovePlayer player : this.movePlayer) {
            if (player.getDirection().equals(getDirection())) {
                if (player.isPositionX()) {
                    positionX += player.getStep();
                } else {
                    positionY += player.getStep();
                }
                return this.currentMapGame.getItemMapGame(positionX, positionY);
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

    public boolean takeItem(Item item) {
        if (!this.inventory.setItem(item)) return false;
        this.currentMapGame.removeItem(item);
        return true;
    }

    public boolean dropItem(Item item) {
        if (!this.inventory.removeItem(item)) return false;
        this.currentMapGame.setItemRemove(item, positionPlayerX, positionPlayerY);
        return true;
    }
}
