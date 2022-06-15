package model;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private int positionPlayerX;
    private int positionPlayerY;
    private String direction;
    private final List<MovePlayer> movePlayer;
    private MapGame currentMapGame;
    private final List<Item> item;
    private final Inventory inventory;

    public Inventory getInventory() {
        return inventory;
    }

    public Player() {
        SettingsPlayer settingsPlayer = new SettingsPlayer();
        this.positionPlayerX = settingsPlayer.positionInitialX();
        this.positionPlayerY = settingsPlayer.positionInitialY();
        movePlayer = new ArrayList<>(settingsPlayer.createMovePlayer());
        this.currentMapGame = null;
        item = new ArrayList<>();
        this.inventory = new Inventory();
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


    public void setItem(Item itens) {
        this.item.add(itens);
    }

    public boolean validMaxCapacity(Item itens) {
        if (itens.getWeight() + inventory.getCapacity() <= inventory.getMaxCapacity()) {
            setItem(itens);
            inventory.setCapacity(itens.getWeight());
            currentMapGame.removeItem(itens);
            return true;
        } else {
            return false;
        }
    }

    public Item getItemInventory(String nameItem) {
        for (Item itemInventory : item) {
            if (itemInventory.getName().equals(nameItem)) {
                return itemInventory;
            }
        }
        return null;
    }

    public void removeItem(Item itens) {
        int weight = itens.getWeight();
        item.remove(itens);
        inventory.setCapacity(-weight);
    }

    public boolean removeItemInventory(Item item) {
        if (!(item instanceof ItemNotRemove)) {
            removeItem(item);
            currentMapGame.setItemRemove(item, positionPlayerX, positionPlayerY);
        }
        return !(item instanceof ItemNotRemove);
    }

    public void removeItensCombine(int combine) {
        for (int i = 0; i < item.size(); i++) {
            if (item.get(i) instanceof ItemCombinable) {
                if (((ItemCombinable) item.get(i)).getCombine() == combine) {
                    removeItem(item.get(i));
                    i = -1; //reset
                }
            }
        }
    }

    public List<Item> getItemVisible() {
        List<Item> listItensVisible = new ArrayList<>();
        for (Item item : this.item) {
            if (item.isVisible()) {
                listItensVisible.add(item);
            }
        }
        return listItensVisible;
    }

    public List<Item> getItemInvisible() {
        List<Item> listItensInvisible = new ArrayList<>();
        for (Item item : this.item) {
            if (!item.isVisible()) {
                listItensInvisible.add(item);
            }
        }
        return listItensInvisible;
    }

    public void updadeInventory(Item item) {
        inventory.setCapacity(item.getWeight());
    }
}
