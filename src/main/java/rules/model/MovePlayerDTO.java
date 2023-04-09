package rules.model;

import backend.model.builder.item.Item;
import rules.interfaces.ICoordinate;

import javax.swing.*;
import java.util.List;

public class MovePlayerDTO implements IMovePlayerDTO {

    private final ImageIcon iconMap;
    private final ImageIcon iconPlayer;
    private final ICoordinate coordinatePlayer;
    private final String songMap;
    private final List<Item> itens;
    private final int indexItens;

    public MovePlayerDTO(ImageIcon iconMap, ImageIcon iconPlayer, ICoordinate coordinatePlayer, String songMap, List<Item> itens, int indexItens) {
        this.iconMap = iconMap;
        this.iconPlayer = iconPlayer;
        this.coordinatePlayer = coordinatePlayer;
        this.songMap = songMap;
        this.itens = itens;
        this.indexItens = indexItens;
    }

    @Override
    public ImageIcon getIconMap() {
        return this.iconMap;
    }

    @Override
    public ImageIcon getIconPlayer() {
        return this.iconPlayer;
    }

    @Override
    public ICoordinate getCoordinatePlayer() {
        return this.coordinatePlayer;
    }

    @Override
    public String getSongMap() {
        return this.songMap;
    }

    @Override
    public List<Item> getItens() {
        return this.itens;
    }

    @Override
    public int getIndexItens() {
        return this.indexItens;
    }
}
