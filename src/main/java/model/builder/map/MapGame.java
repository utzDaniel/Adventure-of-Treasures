package model.builder.map;

import exception.MoveException;
import model.Coordinate;
import model.Door;
import model.builder.item.Item;
import repository.RepositoryMapGame;
import service.AddItemMapGame;
import settings.SettingsMapGame;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class MapGame {
    private String name;
    private ImageIcon image;
    private int[][] limits;
    private Map<Coordinate, Door> doors;
    //Key mapa, Values List<Area> representar inicial X e final X,
    // inicial Y e final Y. Clase Area com uma Map espeficica
    private Map<Coordinate, Item> itens;
    private static final JLabel jLabel = new JLabel();

    protected MapGame() {
        this.limits = new int[56][78];
        this.doors = new HashMap<>();
        this.itens = new HashMap<>();
        settingsMapGame();
    }

    public static JLabel getJLabel(){
        if(Objects.isNull(jLabel.getName())){
            settingsMapGame();
        }
        return jLabel;
    }

    private static void settingsMapGame() {
        SettingsMapGame settingsMapGame = new SettingsMapGame();
        jLabel.setIcon(settingsMapGame.getIcon());
        jLabel.setName(settingsMapGame.getName());
        jLabel.setBounds(settingsMapGame.getRectangle());
    }

    public String getName() {
        return this.name;
    }

    protected void setName(String name) {
        this.name = name;
    }

    public ImageIcon getImage() {
        return this.image;
    }

    protected void setImage(ImageIcon imagemIcon) {
        this.image = imagemIcon;
    }

    public boolean checkLimits(Coordinate coordinate) {
        return this.limits[coordinate.getAxisY()][coordinate.getAxisX()] == 1;
    }

    protected void setLimits(int[][] limits) {
        this.limits = limits;
    }

    public Optional<Door> getDoor(int positionPlayerX, int positionPlayerY) {
        return this.doors.values().stream()
                .filter(door -> door.getCoordinate().getAxisX()*10 == positionPlayerX && door.getCoordinate().getAxisY()*10 == positionPlayerY)
                .findFirst();
    }

    public Optional<Door> getDoor(String map) {
        return this.doors.values().stream()
                .filter(door -> door.getMapGame().equals(map))
                .findFirst();
    }

    protected void setDoors(Map<Coordinate, Door> doors) {
        this.doors = doors;
    }

    public MapGame getMapDoor(Door door) {
        return RepositoryMapGame.getInstance().getMapGame(door.getMapGame());
    }

    //TODO resolver isso depois
    public boolean activate(String nameItem) {
        boolean activate = false;
        try {
            if (nameItem.equals("tocha")) {
                MapGame templo = RepositoryMapGame.getInstance().getMapGame("templo");
                Door openDoor = templo.getDoor(90, 240).get();
                openDoor.setOpen(!openDoor.isOpen());
                activate = true;
            } else if (nameItem.equals("mapa")) {
                MapGame praia = RepositoryMapGame.getInstance().getMapGame("praia");
                praia.setImage(new ImageIcon("src/main/resources/map/praiaM.png"));
            }else if (nameItem.equals("chave")){
                MapGame praia = RepositoryMapGame.getInstance().getMapGame("praia");
                praia.setImage(new ImageIcon("src/main/resources/map/praia.png"));
            }else if (nameItem.equals("escada")){
                MapGame templo = RepositoryMapGame.getInstance().getMapGame("templo");
                templo.setImage(new ImageIcon("src/main/resources/map/temploF.png"));
            }
        } catch (Exception e) {
            throw new MoveException("Direção invalida!");
        }
        return activate;
    }

    public Item getItem(Coordinate coordinate) {
        return this.itens.get(coordinate);
    }

    public void removeItem(Item item) {
        Coordinate coordinate = new Coordinate(item.getLocation().x, item.getLocation().y);
        this.itens.remove(coordinate);
        this.limits[coordinate.getAxisY()][coordinate.getAxisX()] = 1;
    }

    public void addItem(Item item) {
        if (new AddItemMapGame(item).run()) {
            Coordinate coordinate = new Coordinate(item.getLocation().x, item.getLocation().y);
            this.itens.put(coordinate, item);
            this.limits[coordinate.getAxisY()][coordinate.getAxisX()] = 2;
        }
    }

    protected void setItens(Map<Coordinate, Item> itens) {
        this.itens = itens;
        this.itens.keySet()
                .forEach(coordinate -> this.limits[coordinate.getAxisY()][coordinate.getAxisX()] = 2);
    }

    public List<Item> getItemVisible() {
        return this.itens.values().stream()
                .filter(Item::isVisible)
                .collect(Collectors.toList());
    }

    public List<Item> getItemInvisible() {
        return this.itens.values().stream()
                .filter(item1 -> !item1.isVisible())
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "MapGame{" +
                "name='" + name + '\'' +
                ", image=" + image +
                ", limits=" + limits() +
                ", doors=" + doors +
                ", itens=" + itens +
                '}';
    }

    private String limits() {
        StringBuilder str = new StringBuilder();
        for (int[] limit : limits) {
            for (int i : limit) {
                str.append(i);
            }
        }
        return str.toString();
    }
}
