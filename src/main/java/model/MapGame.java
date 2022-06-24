package model;

import javax.swing.*;
import java.util.*;

public abstract class MapGame {

    protected final String name;
    protected ImageIcon imagemIcon;
    protected int[][] limits;

    private final int STEP = MovePlayer.STEP;
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

    public void setExitsDoors(Door door, MapGame neighbor) {
        this.exitsDoors.put(door, neighbor);
    }

    public MapGame getMapDoor(Door door) {
        return this.exitsDoors.get(door);
    }

    public boolean mapGameLimits(int positionX, int positionY) {
        positionX /= STEP;
        positionY /= STEP;
        return limits[positionY][positionX] == 1;
    }

    public void removeItem(Item item) {
        this.item.remove(item);
        limits[item.getPositionItemY() / STEP][item.getPositionItemX() / STEP] = 1;
    }

    public Item getItemMapGame(int positionPlayerX, int positionPlayerY) {
        int positionX = positionPlayerX / STEP;
        int positionY = positionPlayerY / STEP;
        if (limits[positionY][positionX] == 2) {
            for (Item itens : item) {
                if (itens.positionItemX == positionPlayerX && itens.positionItemY == positionPlayerY) {
                    return itens;
                }
            }
        }
        return null;
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

    public void addItem(Item item, int positionX, int positionY) {
        if (mapGameLimits(positionX + STEP, positionY)) {
            item.setPositionItemX(positionX + STEP);
            item.setPositionItemY(positionY);
        } else if (mapGameLimits(positionX - STEP, positionY)) {
            item.setPositionItemX(positionX - STEP);
            item.setPositionItemY(positionY);
        } else if (mapGameLimits(positionX, positionY + STEP)) {
            item.setPositionItemX(positionX);
            item.setPositionItemY(positionY + STEP);
        } else {
            item.setPositionItemX(positionX);
            item.setPositionItemY(positionY - STEP);
        }
        setItem(item);
    }

    public void setItem(Item itens) {
        this.item.add(itens);
        int limitsX = itens.getPositionItemX() / STEP;
        int limitsY = itens.getPositionItemY() / STEP;
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
