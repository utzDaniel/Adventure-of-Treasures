package model;

import javax.swing.*;
import java.util.*;

public abstract class MapGame {
    protected final String name;
    protected ImageIcon imagemIcon;
    protected int[][] limits;
    protected final Map<Door, MapGame> exitsDoors;
    private final List<Item> item;

    public MapGame(String name, ImageIcon imagemIcon) {
        this.name = name;
        this.imagemIcon = imagemIcon;
        limits = new int[78][56];
        exitsDoors = new HashMap<>();
        item = new ArrayList<>();
    }

    public ImageIcon getImagemIcon() {
        return imagemIcon;
    }

    public void setImagemIcon(ImageIcon imagemIcon) {
        this.imagemIcon = imagemIcon;
    }

    public String getName() {
        return name;
    }

    public void setLimits(int[][] limits) {
        this.limits = limits;
    }

    public boolean mapGameLimits(int positionX, int positionY) {
        positionX /= 10;
        positionY /= 10;
        return limits[positionY][positionX] == 1;
    }

    public Item lookItem(Player player) {
        int positionX = player.getPositionPlayerX();
        int positionY = player.getPositionPlayerY();
        for (MovePlayer move : MovePlayer.values()) {
            if (move.getDirection().equals(player.getDirection())) {
                positionX += move.getToMoveX();
                positionY += move.getToMoveY();
                return this.getItemMapGame(positionX, positionY);
            }
        }
        return null;
    }

    private Item getItemMapGame(int positionPlayerX, int positionPlayerY) {
        int positionX = positionPlayerX / 10;
        int positionY = positionPlayerY / 10;
        if (limits[positionY][positionX] == 2) {
            for (Item itens : item) {
                if (itens.positionItemX == positionPlayerX && itens.positionItemY == positionPlayerY) {
                    return itens;
                }
            }
        }
        return null;
    }

    public void removeItem(Item itens) {
        int numero = 1;
        for (Item item : this.item) {
            if (!itens.equals(item) && item.getPositionItemX() == itens.getPositionItemX() &&
                    item.getPositionItemY() == itens.getPositionItemY()) {
                numero = 2;
                break;
            }
        }
        limits[itens.getPositionItemY() / 10][itens.getPositionItemX() / 10] = numero;
        this.item.remove(itens);
    }

    public void setExitsDoors(Door door, MapGame neighbor) {
        this.exitsDoors.put(door, neighbor);
    }

    public Door getDoorMap(int positionPlayerX, int positionPlayerY) {
        Set<Door> doors = this.exitsDoors.keySet();
        for (Door exitDoor : doors) {
            if (exitDoor.getPositionInsideX() == positionPlayerX && exitDoor.getPositionInsideY() == positionPlayerY) {
                return exitDoor;
            } else if (exitDoor.getPositionOutsideX() == positionPlayerX && exitDoor.getPositionOutsideY() == positionPlayerY) {
                return exitDoor;
            }
        }
        return null;
    }

    public MapGame getMapDoor(Door door) {
        return this.exitsDoors.get(door);
    }

    public void setItemRemove(Item item, int positionX, int positionY) {
        if (mapGameLimits(positionX + 10, positionY)) {
            item.setPositionItemX(positionX + 10);
            item.setPositionItemY(positionY);
        } else if (mapGameLimits(positionX - 10, positionY)) {
            item.setPositionItemX(positionX - 10);
            item.setPositionItemY(positionY);
        } else if (mapGameLimits(positionX, positionY + 10)) {
            item.setPositionItemX(positionX);
            item.setPositionItemY(positionY + 10);
        } else {
            item.setPositionItemX(positionX);
            item.setPositionItemY(positionY - 10);
        }
        setItem(item);
    }

    public void setItem(Item itens) {
        this.item.add(itens);
        int limitsX = itens.getPositionItemX() / 10; //  int limitsX = itens.positionItemX / 10;
        int limitsY = itens.getPositionItemY() / 10; // int limitsY = itens.positionItemY / 10;
        limits[limitsY][limitsX] = 2;
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
        for (Item itemRomm : this.item) {
            if (!itemRomm.isVisible()) {
                listItensInvisible.add(itemRomm);
            }
        }
        return listItensInvisible;
    }

}
