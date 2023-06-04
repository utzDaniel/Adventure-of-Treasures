package frontend.model.vo;

import rules.interfaces.ICoordinate;
import rules.interfaces.IItem;
import rules.interfaces.ITake;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TakeVO implements ITake {

    private String iconMap;
    private String iconPlayer;
    private CoordinateVO coordinatePlayer;
    private String effects;
    private List<ItemVO> itens;

    public TakeVO() {
    }

    public TakeVO(String iconMap, String iconPlayer, ICoordinate coordinatePlayer, String effects, List<IItem> itens) {
        this.iconMap = iconMap;
        this.iconPlayer = iconPlayer;
        this.coordinatePlayer = new CoordinateVO(coordinatePlayer.getX(), coordinatePlayer.getY());
        this.effects = effects;
        if (Objects.nonNull(itens))
            this.itens = new ArrayList<>(itens.stream()
                    .map(item -> new ItemVO(item.getIcon(), item.getCoordinate(), item.getName(), item.getDescription(),
                            item.getEffect(), item.getWeight(), item.getSpecialization(), item.isEquipped()))
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
                .map(item -> new ItemVO(item.getIcon(), item.getCoordinate(), item.getName(), item.getDescription(),
                        item.getEffect(),item.getWeight(), item.getSpecialization(), item.isEquipped()))
                .toList());
    }

}
