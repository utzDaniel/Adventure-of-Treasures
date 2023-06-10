package backend.service.model.builder;

import backend.service.model.Area;
import backend.service.model.Door;
import backend.repository.factory.RepositoryFactory;
import backend.exception.MoveException;
import backend.controller.interfaces.ICoordinate;
import backend.service.component.AddItemMapGame;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public abstract class MapGame {
    private String name;
    private String song;
    private ImageIcon icon;
    private Area area;
    private Map<ICoordinate, Door> doors;
    private Map<ICoordinate, Item> itens;

    protected MapGame() {
        this.doors = new HashMap<>();
        this.itens = new HashMap<>();
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public ImageIcon getIcon() {
        return this.icon;
    }

    protected void setIcon(ImageIcon imagemIcon) {
        this.icon = imagemIcon;
    }

    public boolean checkLimits(ICoordinate coordinate) {
        return this.area.isBlock(coordinate);
    }

    protected void setLimits(int[][] limits) {
        this.area = new Area(limits);
    }

    public Optional<Door> getDoor(ICoordinate coordinate) {
        return this.doors.values().stream()
                .filter(o -> o.isDoor(coordinate))
                .findFirst();
    }

    public Optional<Door> getDoor(String map) {
        return this.doors.values().stream()
                .filter(o -> o.isMap(map))
                .findFirst();
    }

    protected void setDoors(Map<ICoordinate, Door> doors) {
        this.doors = doors;
    }

    public MapGame getMapDoor(Door door) {
        return RepositoryFactory.getRepositoryMapGame().get(door.getMapGame());
    }

    //TODO resolver isso depois
    public boolean activate(String nameItem) {
        boolean activate = false;
        try {
            if (nameItem.equals("tocha")) {
                MapGame templo = RepositoryFactory.getRepositoryMapGame().get("templo");
                Door openDoor = templo.getDoor(ICoordinate.getInstance(90, 240)).get();
                openDoor.setOpen(!openDoor.isOpen());
                activate = true;
            } else if (nameItem.equals("mapa")) {
                MapGame praia = RepositoryFactory.getRepositoryMapGame().get("praia");
                praia.setIcon(new ImageIcon("src/main/resources/map/praiaM.png"));
            } else if (nameItem.equals("chave")) {
                MapGame praia = RepositoryFactory.getRepositoryMapGame().get("praia");
                praia.setIcon(new ImageIcon("src/main/resources/map/praia.png"));
            } else if (nameItem.equals("escada")) {
                MapGame templo = RepositoryFactory.getRepositoryMapGame().get("templo");
                templo.setIcon(new ImageIcon("src/main/resources/map/temploF.png"));
            }
        } catch (Exception e) {
            throw new MoveException("Direção invalida!");
        }
        return activate;
    }

    public Item getItem(ICoordinate coordinate) {
        return this.itens.get(coordinate);
    }

    public void removeItem(Item item) {
        this.itens.remove(item.getLocation());
        this.area.unlock(item.getLocation());
    }

    public void addItem(Item item, ICoordinate coordinate) {
        if (new AddItemMapGame(this, item, coordinate).run()) {
            this.itens.put(item.getLocation(), item);
            this.area.block(item.getLocation());
        }
    }

    protected void setItens(Map<ICoordinate, Item> itens) {
        this.itens = itens;
        this.itens.keySet()
                .forEach(c -> this.area.block(c));
    }

    public List<Item> getItemVisible() {
        return this.itens.values().stream()
                .filter(Item::isVisible)
                .collect(Collectors.toList());
    }

    public List<Item> getItemInvisible() {
        return this.itens.values().stream()
                .filter(Item::isInvisible)
                .collect(Collectors.toList());
    }

    public String getSong() {
        return this.song;
    }

    protected void setSong(String filename) {
        this.song = filename;
    }

    @Override
    public String toString() {
        return "MapGame{" +
                "name='" + this.name +
                ", image=" + this.icon +
                ", area=" + this.area +
                ", doors=" + this.doors +
                ", itens=" + this.itens +
                ", song=" + this.song +
                '}';
    }
}
