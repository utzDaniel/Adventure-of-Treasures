package model;

import service.AddItemMapGame;

import javax.swing.*;
import java.util.*;

public abstract class MapGame {

    protected final String name;
    protected ImageIcon imagemIcon;
    protected int[][] limits;
    protected final Map<Door, MapGame> exitsDoors;
    protected final Map<Coordinate, Item> item;

    public MapGame(String name, ImageIcon imagemIcon) {
        this.name = name;
        this.imagemIcon = imagemIcon;
        limits = new int[78][56];
        exitsDoors = new HashMap<>();
        item = new HashMap<>();
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

    public boolean checkLimits(Coordinate coordinate) {
        return limits[coordinate.getEixoY()][coordinate.getEixoX()] == 1;
    }

    public Item getItem(Coordinate coordinate) {
        return this.item.get(coordinate);
    }

    public void removeItem(Item item) {
        Coordinate coordinate = new Coordinate(item.getPositionItemX(), item.getPositionItemY());
        this.item.remove(coordinate);
        this.limits[coordinate.getEixoY()][coordinate.getEixoX()] = 1;
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

    public void addItem(Item item, Player player) {
        new AddItemMapGame(item, player).run();
    }

    public void setItem(Item item) {
        Coordinate coordinate = new Coordinate(item.getPositionItemX(), item.getPositionItemY());
        this.item.put(coordinate, item);
        this.limits[coordinate.getEixoY()][coordinate.getEixoX()] = 2;
    }

    public List<Item> getItemVisible() {
        List<Item> listItensVisible = new ArrayList<>();
        for (Item item : this.item.values()) {
            if (item.isVisible()) {
                listItensVisible.add(item);
            }
        }
        return listItensVisible;
    }

    public List<Item> getItemInvisible() {
        List<Item> listItensInvisible = new ArrayList<>();
        for (Item itemRomm : this.item.values()) {
            if (!itemRomm.isVisible()) {
                listItensInvisible.add(itemRomm);
            }
        }
        return listItensInvisible;
    }

}
