package backend.model.builder.map;

import backend.model.Area;
import backend.model.Door;
import backend.model.builder.item.Item;
import backend.repository.RepositoryFactory;
import frontend.settings.SettingsMapGame;
import rules.exception.MoveException;
import rules.interfaces.ICoordinate;
import rules.service.AddItemMapGame;

import javax.swing.*;
import java.util.*;
import java.util.stream.Collectors;

public abstract class MapGame {
    private String name;
    private String song;
    private ImageIcon image;
    private Area area;
    private Map<ICoordinate, Door> doors;
    private Map<ICoordinate, Item> itens;
    private static final JLabel jLabel = new JLabel();

    protected MapGame() {
        this.doors = new HashMap<>();
        this.itens = new HashMap<>();
        settingsMapGame();
    }

    //TODO remover o JLabel, é resposabilidade da view
    public static JLabel getJLabel() {
        if (Objects.isNull(jLabel.getName())) {
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
                praia.setImage(new ImageIcon("src/main/resources/map/praiaM.png"));
            } else if (nameItem.equals("chave")) {
                MapGame praia = RepositoryFactory.getRepositoryMapGame().get("praia");
                praia.setImage(new ImageIcon("src/main/resources/map/praia.png"));
            } else if (nameItem.equals("escada")) {
                MapGame templo = RepositoryFactory.getRepositoryMapGame().get("templo");
                templo.setImage(new ImageIcon("src/main/resources/map/temploF.png"));
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
                ", image=" + this.image +
                ", area=" + this.area +
                ", doors=" + this.doors +
                ", itens=" + this.itens +
                ", song=" + this.song +
                '}';
    }
}
