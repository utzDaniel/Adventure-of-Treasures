package backend.model.dto;

import rules.interfaces.ICoordinate;
import rules.interfaces.IItem;
import rules.interfaces.ITake;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TakeDTO implements ITake {

    private String iconMap;
    private String iconPlayer;
    private CoordinateDTO coordinatePlayer;
    private String effects;
    private List<ItemDTO> itens;

    public TakeDTO() {
    }

    public TakeDTO(String iconMap, String iconPlayer, ICoordinate coordinatePlayer, String effects, List<IItem> itens) {
        this.iconMap = iconMap;
        this.iconPlayer = iconPlayer;
        this.coordinatePlayer = new CoordinateDTO(coordinatePlayer.getX(), coordinatePlayer.getY());
        this.effects = effects;
        if (Objects.nonNull(itens))
            this.itens = new ArrayList<>(itens.stream()
                    .map(item -> new ItemDTO(item.getIcon(), item.getCoordinate()))
                    .toList());
    }

    @Override
    public String getIconMap() {
        return this.iconMap;
    }

    @Override
    public String getIconPlayer() {
        return this.iconPlayer;
    }

    @Override
    public ICoordinate getCoordinatePlayer() {
        return this.coordinatePlayer;
    }

    @Override
    public String getEffects() {
        return this.effects;
    }

    @Override
    public List<IItem> getItens() {
        if (Objects.isNull(itens)) return null;
        return new ArrayList<>(this.itens.stream()
                .map(item -> new ItemDTO(item.getIcon(), item.getCoordinate()))
                .toList());
    }

}
