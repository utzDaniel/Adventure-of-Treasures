package model;

import exception.MoveException;
import service.AddItemMapGame;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class MapGame {

    protected static Scenery mapInicial;
    protected final String name;
    protected ImageIcon imagemIcon;
    protected int[][] limits;
    protected final Map<Door, MapGame> exitsDoors;//Key mapa, Values List<Area> representar inicial X e final X,
                                                  // inicial Y e final Y. Clase Area com uma Map espeficica
    protected final Map<Coordinate, Item> item;

    public MapGame(String name, ImageIcon imagemIcon) {
        this.name = name;
        this.imagemIcon = imagemIcon;
        this.limits = new int[78][56];
        this.exitsDoors = new HashMap<>();
        this.item = new HashMap<>();
    }

    public ImageIcon getImagemIcon() {
        return this.imagemIcon;
    }

    public void setImagemIcon(ImageIcon imagemIcon) {
        this.imagemIcon = imagemIcon;
    }

    public String getName() {
        return this.name;
    }

    public void setLimits(int[][] limits) {
        this.limits = limits;
    }

    public boolean checkLimits(Coordinate coordinate) {
        return limits[coordinate.getAxisY()][coordinate.getAxisX()] == 1;
    }

    public void setExitsDoors(Door door, MapGame neighbor) {
        this.exitsDoors.put(door, neighbor);
    }

    public MapGame getMapDoor(Door door) {
        return this.exitsDoors.get(door);
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

    public boolean activate(String nameItem) {
        boolean activate = false;
        try {
            if(nameItem.equals("tocha")){
                MapGame village = MapGame.mapInicial.getExit("norte").getExit("norte");
                Door templeDoor = village.getDoorMap(380,530);
                MapGame templo = village.getMapDoor(templeDoor);
                Door openDoor = templo.getDoorMap(90, 240);
                openDoor.setOpen(!openDoor.isOpen());
                activate = true;
            }
        }catch (Exception e){
            throw new MoveException("Direção invalida!");
        }
        return activate;
    }

    public Item getItem(Coordinate coordinate) {
        return this.item.get(coordinate);
    }

    public void removeItem(Item item) {
        Coordinate coordinate = new Coordinate(item.getPositionItemX(), item.getPositionItemY());
        this.item.remove(coordinate);
        this.limits[coordinate.getAxisY()][coordinate.getAxisX()] = 1;
    }

    public void addItem(Item item) {
        new AddItemMapGame(item).run();
    }

    public void setItem(Item item) {
        Coordinate coordinate = new Coordinate(item.getPositionItemX(), item.getPositionItemY());
        this.item.put(coordinate, item);
        this.limits[coordinate.getAxisY()][coordinate.getAxisX()] = 2;
    }

    public List<Item> getItemVisible() {
        List<Item> itens = new ArrayList<>(this.item.values());
        return itens.stream()
                .filter(Item::isVisible)
                .collect(Collectors.toList());
    }

    public List<Item> getItemInvisible() {
        List<Item> itens = new ArrayList<>(this.item.values());
        return itens.stream()
                .filter(item1 -> !item1.isVisible())
                .collect(Collectors.toList());
    }
}
